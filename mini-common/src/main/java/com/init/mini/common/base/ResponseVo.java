package com.init.mini.common.base;

import java.io.Serializable;

public class ResponseVo<T> implements Serializable {

    private String responseCode;

    private String responseMessage;

    private T body;

    public ResponseVo() {
        this.responseCode = "0";
        this.responseMessage = "SUCCESS";
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
