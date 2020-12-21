package com.init.mini.common.base;

import com.init.mini.common.constant.ErrorCodeConstant;

public class ResultVo<T> extends VO {

    private String resultCode;

    private String resultMsg;

    private T body;

    public ResultVo() {
        this.resultCode = ErrorCodeConstant.SUCCESS;
        this.resultCode = ErrorCodeConstant.SUCCESS_MSG;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Boolean isSuccess() {
        return ErrorCodeConstant.SUCCESS.equals(this.resultCode) ? true : false;
    }
}
