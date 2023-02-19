package com.example.kameleon.utils.errors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public enum ErrorCode {

    PROFILE_NOT_FOUND(1, "profile.not.found"),
    ACCOUNT_NOT_FOUND(2, "account.not.found"),
    INVALID_PASSWORD(3, "invalid.password"),
    QUOTE_NOT_FOUND(4,"qoute.not.found"),
    VOICE_NOT_FOUND(5,"voice.not.found"),
    ALREADY_VOTED(6,"already.voted"),
    VALIDATION_MISSIN_REQ_PARAM(7, "validation.req.missing.param"),
    VALIDATION_MISSING(8, "validation.req.missing"),
    RANDOM_ITEM(9,"random.item.not.found")
    ;

    public Integer code;
    public String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private MessageSource messageSource;

    public ErrorCode setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
        return this;
    }

    public String getLocalMessage() {
        if (this.messageSource == null) return this.message;
        return getLocalMessage(this.messageSource);
    }

    public String getLocalMessage(MessageSource messageSourcein) {
        //TODO Сюда вставить локализацию
        Locale locale = LocaleContextHolder.getLocale();
        return messageSourcein.getMessage(this.message, null, locale);
    }

    public static ErrorCode getError(String message) {
        for (ErrorCode code : ErrorCode.values()) {
            if (code.message.equals(message)) return code;
        }
        throw new IllegalArgumentException();
    }

    public Integer getCodeError() {
        return this.code;
    }
}