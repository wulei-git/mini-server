package com.init.mini.common.base;

import com.init.mini.common.constant.ErrorCodeConstant;

public class ResponseVo<T> extends VO {

    private String responseCode;

    private String responseMessage;

    private T body;

    public ResponseVo() {
        this.responseCode = ErrorCodeConstant.SUCCESS;
        this.responseMessage = ErrorCodeConstant.SUCCESS_MSG;
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
