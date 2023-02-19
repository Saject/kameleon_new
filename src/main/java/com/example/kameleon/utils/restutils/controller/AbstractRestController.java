package com.example.kameleon.utils.restutils.controller;

import com.example.kameleon.utils.common.Utils;
import com.example.kameleon.utils.errors.ErrorCode;
import com.example.kameleon.utils.restutils.RestResultUtil;
import com.example.kameleon.utils.restutils.RestValidationException;
import org.modelmapper.MappingException;
import org.modelmapper.spi.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
    public abstract class AbstractRestController {

        private static final Logger logger = LoggerFactory.getLogger(AbstractRestController.class);

        @Autowired
        protected MessageSource messageSource;

        public static final Integer DEFAULT_PAGE_SIZE = 10;

        @ExceptionHandler(Exception.class)
        @ResponseBody
        final ResponseEntity<?> handle(final Exception exception) throws Exception {
            logger.error("ERROR: ", exception);
            if (exception instanceof HttpStatusCodeException)
                return RestResultUtil.error((HttpStatusCodeException) exception);
            //ошибки валидации
            if (exception instanceof MethodArgumentNotValidException) {
                try {
                    BindingResult rez = ((MethodArgumentNotValidException) exception).getBindingResult();
                    String mess = getMessage(rez);
                    return RestResultUtil.error(new RestValidationException(mess));
                } catch (IllegalArgumentException ex1) {
                    logger.error("error cast", ex1);
                }
            }

            if (exception instanceof MissingServletRequestParameterException) {
                String paramName = ((MissingServletRequestParameterException) exception).getParameterName();
                return RestResultUtil.error(new RestValidationException(String.format(ErrorCode.VALIDATION_MISSIN_REQ_PARAM.getLocalMessage(messageSource), paramName)));

            }
            if (exception instanceof ValidationException) {
                String paramName = null;
                if (exception instanceof ConstraintViolationException) {
                    paramName = ((ConstraintViolationException) exception).getConstraintViolations().stream().map(cv -> cv.toString()).collect(Collectors.joining(","));
                } else {
                    paramName = exception.getMessage();
                }
                return RestResultUtil.error(new RestValidationException(String.format(ErrorCode.VALIDATION_MISSING.getLocalMessage(messageSource), paramName)));
            }
            //MissingServletRequestParameterException
            //org.springframework.web.bind.MissingServletRequestParameterException: Required String parameter 'discriminator' is not present

            if (exception instanceof MappingException) {
                List<ErrorMessage> errorMessageList = (List<ErrorMessage>) ((MappingException) exception).getErrorMessages();
                ErrorMessage errorMessage = errorMessageList.get(0);
                if (Utils.isNotEmpty(errorMessage)) {
                    return RestResultUtil.error(exception, errorMessage.getMessage());
                }
            }
            logger.error("Unexpected error" , exception);
            return RestResultUtil.error(exception, "error");
        }


        private String getMessage(BindingResult rez) {
            if (rez == null || rez.getAllErrors() == null) return "";
            StringBuilder sb = new StringBuilder("");
            sb.append("[");
            int i = 0;
            for (ObjectError error : rez.getAllErrors()) {
                if (i != 0) sb.append(",");
                i = i + 1;
                try {
                    String errorMes = ErrorCode.getError(error.getDefaultMessage()).getLocalMessage(messageSource);
                    sb.append(errorMes);
                } catch (IllegalArgumentException ex1) {
                    try {
                        Locale locale = LocaleContextHolder.getLocale();
                        String errorMes = messageSource.getMessage(error.getDefaultMessage(), null, locale);
                        sb.append(errorMes);
                    } catch (Exception e) {
                        logger.error("error cast", ex1);
                        sb.append(error.getDefaultMessage());
                    }
                }
            }

            sb.append("] ");
            return sb.toString();


        }

        public Pageable getPageParam(Integer page, Integer pagesize, Sort sort) {
            if (pagesize == null) pagesize = DEFAULT_PAGE_SIZE;
            if (page == null) page = 0;
            return PageRequest.of(page, pagesize, sort);
        }

    }

