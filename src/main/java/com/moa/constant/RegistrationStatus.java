package com.moa.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RegistrationStatus {
    receipt("신청 접수"),
    checking("군 정보 확인"),
    screening("은행 심사"),
    done("개설 완료");

    private final String status;
}
