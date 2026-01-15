package com.qyt.qytbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 文件上传响应DTO
 */
@Data
public class FileUploadResponseDTO {
    /**
     * 文件路径
     */
    @Schema(description = "文件路径", example = "files/12345678-1234-1234-1234-1234567890ab.jpg")
    private String filePath;

    /**
     * 文件URL
     */
    @Schema(description = "文件URL", example = "https://bucket-name.host-name/files/12345678-1234-1234-1234-1234567890ab.jpg")
    private String fileUrl;

    /**
     * 文件名
     */
    @Schema(description = "文件名", example = "12345678-1234-1234-1234-1234567890ab.jpg")
    private String fileName;

    /**
     * 文件大小（字节）
     */
    @Schema(description = "文件大小（字节）", example = "1024")
    private long fileSize;
}