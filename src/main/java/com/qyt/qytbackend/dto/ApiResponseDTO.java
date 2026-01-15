package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * API响应DTO
 */
@Data
@Schema(name = "ApiResponseDTO", description = "API响应结果")
public class ApiResponseDTO<T> {
    @Schema(description = "响应码", example = "0000")
    private int code;
    @Schema(description = "响应消息", example = "success")
    private String message;
    @Schema(description = "响应数据")
    private T data;

    public ApiResponseDTO() {
    }

    public ApiResponseDTO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponseDTO<T> success(T data) {
        return new ApiResponseDTO<>(200, "success", data);
    }

    public static <T> ApiResponseDTO<T> success() {
        return new ApiResponseDTO<>(200, "success", null);
    }

    public static <T> ApiResponseDTO<T> error(int code, String message) {
        return new ApiResponseDTO<>(code, message, null);
    }

    public static <T> ApiResponseDTO<T> error(String message) {
        return new ApiResponseDTO<>(500, message, null);
    }
}
