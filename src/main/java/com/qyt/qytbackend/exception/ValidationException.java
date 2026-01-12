package com.qyt.qytbackend.exception;

/**
 * 参数校验异常类
 *
 * @author qyt
 * @date 2024
 */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    public ValidationException(String message) {
        super(message);
        this.code = 400;
    }

    public ValidationException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
        this.code = 400;
    }

    public ValidationException(Integer code, String message, Throwable cause) {
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
