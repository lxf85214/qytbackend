package com.qyt.qytbackend.exception;

/**
 * 系统异常类
 *
 * @author qyt
 * @date 2024
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    public SystemException(String message) {
        super(message);
        this.code = 500;
    }

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
    }

    public SystemException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
