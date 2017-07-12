package com.mm.common.exception;

/**
 * Author:chenyanlong@meituan.com
 * Date:17/7/11
 * Time:16:26
 * Desc:业务异常
 */
public class ServiceException extends RuntimeException {

    private Integer code;

    private String errorMessage;

    public ServiceException(String errorMessage) {
        super(errorMessage);
    }

    public ServiceException(Integer code, String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.code=code;
        this.errorMessage=errorMessage;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.errorMessage=message;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public Integer getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
