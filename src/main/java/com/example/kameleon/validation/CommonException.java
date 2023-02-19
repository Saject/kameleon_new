package com.example.kameleon.validation;

import com.example.kameleon.utils.errors.AbstractValidatorExceptions;
import com.example.kameleon.utils.errors.ErrorCode;

public class CommonException extends AbstractValidatorExceptions {

    public CommonException(ErrorCode errorCode) {
        super(errorCode);
    }
    public CommonException(String message) {
        super(message);
    }

    public CommonException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }

}
