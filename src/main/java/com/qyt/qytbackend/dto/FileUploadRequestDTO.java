package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文件上传请求DTO
 */
@Data
public class FileUploadRequestDTO {
    /**
     * 文件夹路径
     */
    @Schema(description = "文件夹路径", example = "files")
    private String folder;

    /**
     * 原文件名（可选）
     */
    @Schema(description = "原文件名（可选）", example = "example.txt")
    private String originalFileName;
}