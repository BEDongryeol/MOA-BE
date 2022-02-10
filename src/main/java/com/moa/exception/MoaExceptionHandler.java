package com.moa.exception;

import com.moa.finance.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.moa.constant.ErrorCode.USER_NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class MoaExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DBException.class})
    protected ResponseEntity<ErrorResponse> handleDateException(){
        log.error("HandleDateException Caught : {} ", USER_NOT_FOUND);
        return ErrorResponse.toRes(USER_NOT_FOUND);
    }

}
