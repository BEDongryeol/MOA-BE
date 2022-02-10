package com.moa.exception;

import com.moa.constant.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@Getter
@ResponseStatus(code = HttpStatus.OK, reason = "Not Permitted User")
public class DBException extends EntityNotFoundException {
    private final ErrorCode errorCode;

    public DBException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
