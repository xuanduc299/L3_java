package com.globits.da.dto;

import com.globits.da.validate.ResponseStatus;

import java.util.List;

public class Response<T> {
    private T data;
    private int code;
    private String message;

    public Response() {
        this.code = ResponseStatus.SUCCESS.getCode();
        this.message = ResponseStatus.SUCCESS.getMessage();
    }

    public Response(T data) {
        this.data = data;
        this.code = ResponseStatus.SUCCESS.getCode();
        this.message = ResponseStatus.SUCCESS.getMessage();
    }

    public Response(T data, ResponseStatus status) {
        this.data = data;
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public Response(ResponseStatus status) {
        this.data = null;
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public void setResponseStatus(ResponseStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
