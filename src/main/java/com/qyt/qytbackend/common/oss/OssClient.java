
package com.qyt.qytbackend.common.oss;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * OSS客户端工具类
 */
@Component
public class OssClient {

    private static final Logger log = LoggerFactory.getLogger(OssClient.class);
    private static S3Client s3Client;
    private static OssProperties ossProperties;

    @Autowired
    public void setS3Client(S3Client s3Client) {
        OssClient.s3Client = s3Client;
    }

    @Autowired
    public void setOssProperties(OssProperties ossProperties) {
        OssClient.ossProperties = ossProperties;
    }

    /**
     * 上传文件
     * @param key 文件路径
     * @param inputStream 文件输入流
     * @param contentType 文件类型
     * @return 上传结果
     * @throws Exception 上传异常
     */
    public boolean upload(String key, InputStream inputStream, String contentType) throws Exception {
        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .key(key)
                    .contentType(contentType)
                    .build();

            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
            return true;
        } catch (Exception e) {
            log.error("上传文件失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 上传文件（字节数组）
     * @param key 文件路径
     * @param bytes 文件字节数组
     * @param contentType 文件类型
     * @return 上传结果
     * @throws Exception 上传异常
     */
    public boolean upload(String key, byte[] bytes, String contentType) throws Exception {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
            return upload(key, inputStream, contentType);
        } catch (IOException e) {
            log.error("上传文件失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 下载文件
     * @param key 文件路径
     * @return 文件字节数组
     * @throws Exception 下载异常
     */
    public byte[] download(String key) throws Exception {
        try {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .key(key)
                    .build();

            ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = response.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            response.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error("下载文件失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 删除文件
     * @param key 文件路径
     * @return 删除结果
     * @throws Exception 删除异常
     */
    public boolean delete(String key) throws Exception {
        try {
            DeleteObjectRequest request = DeleteObjectRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .key(key)
                    .build();

            s3Client.deleteObject(request);
            return true;
        } catch (Exception e) {
            log.error("删除文件失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 批量删除文件
     * @param keys 文件路径列表
     * @return 删除结果
     * @throws Exception 删除异常
     */
    public boolean deleteObjects(List<String> keys) throws Exception {
        try {
            List<ObjectIdentifier> objectIdentifiers = keys.stream()
                    .map(key -> ObjectIdentifier.builder().key(key).build())
                    .collect(Collectors.toList());

            DeleteObjectsRequest request = DeleteObjectsRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .delete(Delete.builder().objects(objectIdentifiers).build())
                    .build();

            s3Client.deleteObjects(request);
            return true;
        } catch (Exception e) {
            log.error("批量删除文件失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 检查文件是否存在
     * @param key 文件路径
     * @return 是否存在
     * @throws Exception 检查异常
     */
    public boolean exists(String key) throws Exception {
        try {
            HeadObjectRequest request = HeadObjectRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .key(key)
                    .build();

            s3Client.headObject(request);
            return true;
        } catch (NoSuchKeyException e) {
            return false;
        } catch (Exception e) {
            log.error("检查文件是否存在失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 获取文件URL
     * @param key 文件路径
     * @return 文件URL
     */
    public String getUrl(String key) {
        return String.format("https://%s.%s/%s", ossProperties.getBucket(), ossProperties.getHostName(), key);
    }

    /**
     * 列出指定前缀的文件
     * @param prefix 前缀
     * @return 文件路径列表
     * @throws Exception 列出异常
     */
    public List<String> listObjects(String prefix) throws Exception {
        try {
            ListObjectsRequest request = ListObjectsRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .prefix(prefix)
                    .build();

            ListObjectsResponse response = s3Client.listObjects(request);
            return response.contents().stream()
                    .map(S3Object::key)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("列出文件失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    // ==================== 静态方法 ====================

    /**
     * 静态方法：上传文件（自动生成文件名）
     * @param inputStream 文件输入流
     * @param contentType 文件类型
     * @param folder 文件夹路径
     * @return OSS文件路径
     * @throws Exception 上传异常
     */
    public static String uploadFile(InputStream inputStream, String contentType, String folder) throws Exception {
        try {
            // 生成唯一文件名
            String fileName = UUID.randomUUID().toString() + getFileExtension(contentType);
            String key = folder + "/" + fileName;

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .key(key)
                    .contentType(contentType)
                    .build();

            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
            return key;
        } catch (Exception e) {
            log.error("上传文件失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 静态方法：上传图片（自动生成文件名）
     * @param inputStream 图片输入流
     * @param contentType 图片类型
     * @param folder 文件夹路径
     * @return OSS文件路径
     * @throws Exception 上传异常
     */
    public static String uploadImage(InputStream inputStream, String contentType, String folder) throws Exception {
        // 验证是否为图片类型
        if (!contentType.startsWith("image/")) {
            contentType = "image/jpeg";
        }
        return uploadFile(inputStream, contentType, folder);
    }

    /**
     * 静态方法：上传文件（使用原文件名）
     * @param inputStream 文件输入流
     * @param contentType 文件类型
     * @param folder 文件夹路径
     * @param originalFileName 原文件名
     * @return OSS文件路径
     * @throws Exception 上传异常
     */
    public static String uploadFileWithOriginalName(InputStream inputStream, String contentType, String folder, String originalFileName) throws Exception {
        try {
            String key = folder + "/" + originalFileName;

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .key(key)
                    .contentType(contentType)
                    .build();

            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
            return key;
        } catch (Exception e) {
            log.error("上传文件失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 静态方法：根据OSS路径下载文件到本地
     * @param ossKey OSS文件路径
     * @param localFilePath 本地文件路径
     * @return 下载结果
     * @throws Exception 下载异常
     */
    public static boolean downloadFile(String ossKey, String localFilePath) throws Exception {
        try {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(ossProperties.getBucket())
                    .key(ossKey)
                    .build();

            ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request);
            
            // 创建本地文件
            File file = new File(localFilePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            // 写入文件
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = response.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
            } finally {
                response.close();
            }
            
            return true;
        } catch (Exception e) {
            log.error("下载文件失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 静态方法：获取文件扩展名
     * @param contentType 文件类型
     * @return 文件扩展名
     */
    private static String getFileExtension(String contentType) {
        switch (contentType) {
            case "image/jpeg":
                return ".jpg";
            case "image/png":
                return ".png";
            case "image/gif":
                return ".gif";
            case "image/webp":
                return ".webp";
            case "application/pdf":
                return ".pdf";
            case "application/msword":
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return ".docx";
            case "application/vnd.ms-excel":
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                return ".xlsx";
            case "application/vnd.ms-powerpoint":
            case "application/vnd.openxmlformats-officedocument.presentationml.presentation":
                return ".pptx";
            case "text/plain":
                return ".txt";
            case "application/json":
                return ".json";
            case "application/xml":
                return ".xml";
            case "text/html":
                return ".html";
            case "application/javascript":
                return ".js";
            case "text/css":
                return ".css";
            default:
                return ".bin";
        }
    }
}


