package com.example.kameleon.utils.restutils;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
public class RestResultUtil {
    private static final Logger logger = LoggerFactory.getLogger(RestResultUtil.class);
    private static final String PACKAGE = "com.example.kameleon";

    /**
     * формирование тела ответа - OK(200)
     *
     * @param data - данные запроса
     * @return
     */
    public static <T, E> ResponseEntity<RestResult<E>> success(T data) {
        RestResult<E> result = new RestResult(data);
        return updateSuccess(result);
    }

    /**
     * формирование тела ответа - OK(200)
     *
     * @return
     */
    public static <T> ResponseEntity<RestResult> success() {
        RestResult result = new RestResult();
        return updateSuccess(result);
    }

    public static <TResult extends RestResult> ResponseEntity<TResult> updateSuccess(TResult result) {
        HttpHeaders responseHeaders = new HttpHeaders();
        result.setCode(Code.SUCCESS);
        return ResponseEntity.ok().headers(responseHeaders).body(result);
    }

    /**
     * формирование тела ответа - ошибки
     *
     * @param data    - данные запроса
     * @param status  - статус в ответе
     * @param message - сообщение в ответе
     * @return
     */
    public static <T> ResponseEntity<RestResult<T>> error(T data, HttpStatus status, String message) {
        RestResult result = new RestResult(data);
        result.setSynopsis(message);
        HttpHeaders responseHeaders = new HttpHeaders();
        result.setCode(Code.ERROR);
        return ResponseEntity.status(status).headers(responseHeaders).body(result);
    }

    /**
     * формирование тела OK
     *
     * @param data - данные запроса
     * @return
     */
    public static <T> RestResult<T> successRestResult(T data) {
        RestResult<T> result = new RestResult(data);
        result.setCode(Code.SUCCESS);
        return result;
    }

    /**
     * формирование тела ответа - ошибки
     *
     * @param data - данные запроса
     * @param e    - исключение
     * @return
     */
    public static <T> RestResult<T> errorRestResult(T data, Exception e) {
        RestResult<T> result = new RestResult(data , e);
        result.setCode(Code.ERROR);
        result.setSynopsis(e.getLocalizedMessage());
        result.setStackTrace(e.getMessage());
        return result;
    }

    /**
     * получим данные ошибки
     *
     * @param exception
     */
    private static ErrorObject getDataError(HttpStatusCodeException exception) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ErrorObject eo = null;
        try {
            eo = mapper.readValue(exception.getResponseBodyAsString(), ErrorObject.class);
            eo.setStatus(exception.getStatusCode());
        } catch (Exception e) {
            // Ignored
        }
        return eo;
    }

    /**
     * получим объект ошибки из запроса
     *
     * @param ex - ошибка клиента , с кодом
     * @return
     */
    public static <T> ResponseEntity<RestResult<T>> error(HttpStatusCodeException ex) {
        ErrorObject error = getDataError(ex);
        RestResult rez = new RestResult(error, ex);
        String message = ex.getLocalizedMessage();
        String code = String.valueOf(ex.getStatusCode().value());
        if (!code.equals("") && code != null) {
            message = message.replaceFirst(code, "").trim();
        }
        rez.setSynopsis(message);
        rez.setCode(Code.ERROR);
        return new ResponseEntity<RestResult<T>>(rez, ex.getStatusCode());
    }

    public static <T> ResponseEntity<RestResult<T>> error(Exception ex, String message) {
        RestResult result = new RestResult(ex);
        result.setStackTrace(filterStackTrace(ex));
        result.setCode(Code.ERROR);

        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static String filterStackTrace(Exception e) {
        String filteredString = Arrays.stream(e.getStackTrace())
                .filter(st -> st.getClassName().contains(PACKAGE))
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));
            return filteredString;
        }



}