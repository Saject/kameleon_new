package com.example.kameleon.utils.restutils;

import com.example.kameleon.utils.errors.AbstractValidatorExceptions;
import com.example.kameleon.utils.errors.ErrorCode;

public class RestValidationException extends AbstractValidatorExceptions {

    public RestValidationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RestValidationException(String message) {
        super(message);
    }
}
