package com.example.kameleon.utils.restutils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Getter
@Setter
public class ErrorObject {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String reason;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String synopsis;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HttpStatus status;

    public String getReason() {
        if (reason == null){
            reason = synopsis;
        }
        return reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorObject that = (ErrorObject) o;
        return Objects.equals(reason, that.reason) &&
                Objects.equals(date, that.date) &&
                status == that.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(reason, date, status);
    }
}