package com.dangdang.check.domain.customer;

import com.dangdang.check.core.customer.CustomerCommandService;
import com.dangdang.check.core.employee.EmployeeFindService;
import com.dangdang.check.core.pet.PetCommandService;
import com.dangdang.check.domain.customer.request.RegisterCustomerWithPets;
import com.dangdang.check.domain.customer.response.CustomerInfo;
import com.dangdang.check.domain.employee.EmployeeEntity;
import com.dangdang.check.domain.pet.PetEntity;
import com.dangdang.check.domain.pet.PetEntityFactory;
import com.dangdang.check.domain.store.BusinessInfoEntity;
import com.dangdang.check.domain.store.StoreEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerApiServiceImpl implements CustomerApiService {

    private final EmployeeFindService employeeFindService;
    private final CustomerCommandService customerCommandService;
    private final CustomerPhoneCommandService customerPhoneCommandService;
    private final PetCommandService petCommandService;

    @Override
    @Transactional
    public CustomerInfo registerCustomerWithPets(RegisterCustomerWithPets command) {
        EmployeeEntity employee = employeeFindService.findByLoginId(command.getLoginId());
        StoreEntity store = employee.getStore();
        validateStoreAndBusinessInfo(store);

        CustomerEntity customer = CustomerEntityFactory.from(command);
        store.addCustomer(customer);

        customerCommandService.save(customer);

        CustomerPhoneEntity customerPhone = CustomerPhoneEntityFactory.from(command);
        customer.addCustomerPhone(customerPhone);
        customerPhoneCommandService.save(customerPhone);

        command.getPets()
                .forEach(petCommand -> {
                    PetEntity pet = PetEntityFactory.from(petCommand);
                    customer.addPet(pet);
                    petCommandService.save(pet);
                });
        return new CustomerInfo(customer);
    }

    private void validateStoreAndBusinessInfo(StoreEntity store) {
        if (store == null || store.isDeleted()) {
            throw new IllegalStateException("삭제되었거나 존재하지 않는 가게입니다.");
        }

        BusinessInfoEntity businessInfo = store.getBusinessInfo();
        if (businessInfo == null || businessInfo.isDeleted()) {
            throw new IllegalStateException("삭제되었거나 존재하지 않는 비즈니스 정보입니다.");
        }
    }
}
