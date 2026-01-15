package com.qyt.qytbackend.controller;

import com.qyt.qytbackend.common.oss.OssClient;
import com.qyt.qytbackend.dto.ApiResponseDTO;
import com.qyt.qytbackend.dto.FileUploadResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * OSS文件操作控制器
 */
@RestController
@RequestMapping("/api/oss")
@Tag(name = "OSS文件操作", description = "OSS文件上传、下载相关的API接口")
@Slf4j
public class OssController {

    @Autowired
    private OssClient ossClient;

    /**
     * 上传文件（直接上传OSS）
     *
     * @param file 文件
     * @return API响应
     */
    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "直接上传文件到OSS，自动生成唯一文件路径")
    public ApiResponseDTO<FileUploadResponseDTO> uploadFile(
            @Parameter(description = "文件") @RequestParam("file") MultipartFile file) {
        try {
            // 验证文件
            if (file.isEmpty()) {
                return ApiResponseDTO.error(400, "文件不能为空");
            }

            // 获取文件类型
            String contentType = file.getContentType();
            if (contentType == null) {
                contentType = "application/octet-stream"; // 默认文件类型
            }

            // 生成唯一文件路径
            String fileKey = UUID.randomUUID() + getFileExtension(contentType);

            // 直接使用MultipartFile的输入流上传，不落到本地
            try (InputStream inputStream = file.getInputStream()) {
                // 上传到OSS
                ossClient.upload(fileKey, inputStream, contentType);

                // 构建响应
                FileUploadResponseDTO responseDTO = new FileUploadResponseDTO();
                responseDTO.setFilePath(fileKey);
                responseDTO.setFileUrl(ossClient.getUrl(fileKey));
                responseDTO.setFileName(fileKey);
                responseDTO.setFileSize(file.getSize());

                return ApiResponseDTO.success(responseDTO);
            }
        } catch (Exception e) {
            log.error("上传文件失败: {}", e.getMessage(), e);
            return ApiResponseDTO.error(500, "上传文件失败: " + e.getMessage());
        }
    }

    /**
     * 上传图片
     *
     * @param file   图片文件
     * @param folder 文件夹路径
     * @return API响应
     */
    @PostMapping("/upload/image")
    @Operation(summary = "上传图片", description = "上传图片到OSS")
    public ApiResponseDTO<FileUploadResponseDTO> uploadImage(
            @Parameter(description = "图片文件") @RequestParam("file") MultipartFile file,
            @Parameter(description = "文件夹路径") @RequestParam("folder") String folder) {
        try {
            // 验证文件
            if (file.isEmpty()) {
                return ApiResponseDTO.error(400, "文件不能为空");
            }

            // 验证文件夹路径
            if (folder == null || folder.trim().isEmpty()) {
                folder = "images"; // 默认文件夹
            }

            // 上传图片
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                contentType = "image/jpeg"; // 默认图片类型
            }

            try (InputStream inputStream = file.getInputStream()) {
                // 生成唯一文件名
                String fileName = UUID.randomUUID() + getFileExtension(contentType);
                String filePath = folder + "/" + fileName;

                // 上传到OSS
                ossClient.upload(filePath, inputStream, contentType);

                // 构建响应
                FileUploadResponseDTO responseDTO = new FileUploadResponseDTO();
                responseDTO.setFilePath(filePath);
                responseDTO.setFileUrl(ossClient.getUrl(filePath));
                responseDTO.setFileName(fileName);
                responseDTO.setFileSize(file.getSize());

                return ApiResponseDTO.success(responseDTO);
            }
        } catch (Exception e) {
            log.error("上传图片失败: {}", e.getMessage(), e);
            return ApiResponseDTO.error(500, "上传图片失败: " + e.getMessage());
        }
    }

    /**
     * 下载文件
     *
     * @param filePath        文件路径
     * @param downloadFileName 下载文件名
     * @return 文件响应
     */
    @GetMapping("/download")
    @Operation(summary = "下载文件", description = "从OSS下载文件")
    public ResponseEntity<byte[]> downloadFile(
            @Parameter(description = "文件路径") @RequestParam("filePath") String filePath,
            @Parameter(description = "下载文件名") @RequestParam("downloadFileName") String downloadFileName) {
        try {
            // 验证参数
            if (filePath == null || filePath.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("文件路径不能为空".getBytes());
            }

            // 下载文件
            byte[] fileBytes = ossClient.download(filePath);

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", downloadFileName);
            headers.setContentLength(fileBytes.length);

            return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            log.error("下载文件失败: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("下载文件失败: " + e.getMessage()).getBytes());
        }
    }

    /**
     * 获取文件扩展名
     *
     * @param contentType 文件类型
     * @return 文件扩展名
     */
    private String getFileExtension(String contentType) {
        return switch (contentType) {
            case "image/jpeg" -> ".jpg";
            case "image/png" -> ".png";
            case "image/gif" -> ".gif";
            case "image/webp" -> ".webp";
            case "application/pdf" -> ".pdf";
            case "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" ->
                    ".docx";
            case "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" ->
                    ".xlsx";
            case "application/vnd.ms-powerpoint",
                 "application/vnd.openxmlformats-officedocument.presentationml.presentation" -> ".pptx";
            case "text/plain" -> ".txt";
            case "application/json" -> ".json";
            case "application/xml" -> ".xml";
            case "text/html" -> ".html";
            case "application/javascript" -> ".js";
            case "text/css" -> ".css";
            default -> ".bin";
        };
    }
}