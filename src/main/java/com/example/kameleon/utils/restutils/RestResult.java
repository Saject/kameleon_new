package com.example.kameleon.utils.restutils;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class RestResult<T> implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Code code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer errorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String synopsis;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stackTrace;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String requestId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String responseId;

    {
        this.responseId = UUID.randomUUID().toString();
    }

    public RestResult() {
    }

    public <F> RestResult(F data, Exception e){
        if (data instanceof Page) {
            Page tmpPage = (Page) data;
            this.setData((T)((Page) data).getContent());
        } else {
            this.setData((T)data);
        }

    }
    public <F> RestResult(F data) {
        if (data instanceof Page) {
            Page tmpPage = (Page) data;
            this.setData((T)((Page) data).getContent());
        } else {
            this.setData((T)data);
        }
    }

    public RestResult(String requestId) {
        this.requestId = requestId;
    }

    public RestResult(T data, Code code, String synopsis) {
        this.data = data;
        this.code = code;
        this.synopsis = synopsis;
    }

    public RestResult(T data, Code code, String synopsis, String stackTrace) {
        this.data = data;
        this.code = code;
        this.synopsis = synopsis;
        this.stackTrace = stackTrace;
    }

    public RestResult(String textError, Code code, Exception e) {
        this.code = code;
        this.synopsis = String.format("%s %s", textError, e.getLocalizedMessage());
        if (e.getCause() != null && e.getCause().getMessage() != null)
            this.stackTrace = e.getCause().getMessage();
    }

    public RestResult(String textError, Code code) {
        this.code = code;
        this.synopsis = String.format("%s", textError);
    }

    public RestResult(Exception ex) {
        this.synopsis = String.format("%s",  ex.getLocalizedMessage());
        if (ex.getCause() != null && ex.getCause().getMessage() != null)
            this.stackTrace = ex.getCause().getMessage();
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "data=" + data +
                ", code=" + code +
                ", errorCode=" + errorCode +
                ", synopsis='" + synopsis + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", requestId='" + requestId + '\'' +
                ", responseId='" + responseId + '\'' +
                '}';
    }
}

