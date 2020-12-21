package com.init.mini.common.base;

import com.init.mini.common.constant.ErrorCodeConstant;

public class BusinessException extends RuntimeException {

    private String errorCode;

    private String errorMsg;

    public BusinessException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorCode = ErrorCodeConstant.ERROR;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
