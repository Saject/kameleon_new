package com.example.kameleon.utils.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class AbstractValidatorExceptions extends HttpStatusCodeException implements KameleonException {

    private ErrorCode errorCode = null;

    protected AbstractValidatorExceptions(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode.getLocalMessage());
        this.errorCode = errorCode;
    }

    protected AbstractValidatorExceptions(ErrorCode errorCode, Object... args) {
        super(HttpStatus.BAD_REQUEST, String.format(errorCode.getLocalMessage(), args));
        this.errorCode = errorCode;
    }

    protected AbstractValidatorExceptions(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
