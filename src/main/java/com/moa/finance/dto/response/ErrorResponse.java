package com.moa.finance.dto.response;

import com.moa.constant.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime time = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public static ResponseEntity<ErrorResponse> toRes(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpstatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpstatus().value())
                        .error(errorCode.getHttpstatus().name())
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build());
    }
}
