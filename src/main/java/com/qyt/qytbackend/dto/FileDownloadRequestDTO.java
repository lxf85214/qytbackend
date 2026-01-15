package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文件下载请求DTO
 */
@Data
public class FileDownloadRequestDTO {
    /**
     * 文件路径
     */
    @Schema(description = "文件路径", example = "files/12345678-1234-1234-1234-1234567890ab.jpg")
    private String filePath;

    /**
     * 下载文件名
     */
    @Schema(description = "下载文件名", example = "example.jpg")
    private String downloadFileName;
}