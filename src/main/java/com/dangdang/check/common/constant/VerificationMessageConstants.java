package com.dangdang.check.common.constant;

public class VerificationMessageConstants {

    public static final String SUBJECT = "【댕댕 체크】 인증 코드 안내";
    public static final String BODY_TEMPLATE = "안녕하세요,\n" +
            "회원님의 이메일 주소 인증을 위해 아래 인증 코드를 입력해 주세요.\n\n" +
            "인증 코드: %s\n\n" +
            "해당 코드는 5분 동안 유효하며, 타인에게 공유하지 마시기 바랍니다.\n\n" +
            "감사합니다.";
}