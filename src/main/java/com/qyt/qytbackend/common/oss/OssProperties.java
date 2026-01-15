package com.qyt.qytbackend.common.oss;

import lombok.Data;

/**
 * OSS配置属性
 */
@Data
public class OssProperties {

    /**
     * 访问密钥
     */
    private String accessKey;

    /**
     * 密钥
     */
    private String secretKey;

    /**
     * 主机名
     */
    private String hostName;

    /**
     * 超时时间（毫秒）
     */
    private int timeOut;

    /**
     * 区域
     */
    private String region;

    /**
     * 端点
     */
    private String endPoint;

    /**
     * 存储桶
     */
    private String bucket;
}
