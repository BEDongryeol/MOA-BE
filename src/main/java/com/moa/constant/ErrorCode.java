package com.moa.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 조회할 수 없습니다."),
    PRODUCT_NOT_FOUND(NOT_FOUND, "존재하지 않는 상품입니다."),
    REGISTRATION_NOT_FOUND(NOT_FOUND, "신청 내역을 조회할 수 없습니다."),
    ACCOUNT_NOT_FOUND(NOT_FOUND, "해당 계좌를 조회할 수 없습니다."),

    INVALID_USER(FORBIDDEN,"접근할 수 없습니다."),
    NOT_ENOUGH_BALANCE(FORBIDDEN, "잔액이 부족합니다.");

    private final HttpStatus httpstatus;
    private final String message;
}
