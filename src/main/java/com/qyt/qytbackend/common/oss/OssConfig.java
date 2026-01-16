package com.qyt.qytbackend.common.oss;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.retry.RetryPolicy;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

import java.time.Duration;

/**
 * OSS配置类
 */
// @Configuration
public class OssConfig {

    @Value("${oss.access-key}")
    private String accessKey;

    @Value("${oss.secret-key}")
    private String secretKey;

    @Value("${oss.host-name}")
    private String hostName;

    @Value("${oss.time-out}")
    private int timeOut;

    @Value("${oss.region}")
    private String region;

    @Value("${oss.end-point}")
    private String endPoint;

    @Value("${oss.bucket}")
    private String bucket;

    /**
     * 初始化S3客户端
     * @return S3Client实例
     */
    // @Bean
    public S3Client s3Client() {
        // 创建凭证
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        // 配置客户端
        ClientOverrideConfiguration overrideConfiguration = ClientOverrideConfiguration.builder()
                .apiCallTimeout(Duration.ofMillis(timeOut))
                .apiCallAttemptTimeout(Duration.ofMillis(timeOut))
                .build();

        // 构建客户端
        S3ClientBuilder builder = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .overrideConfiguration(overrideConfiguration);

        // 如果配置了区域，设置区域
        if (region != null && !region.isEmpty()) {
            builder.region(Region.of(region));
        }

        // 如果配置了端点，设置端点
        if (endPoint != null && !endPoint.isEmpty()) {
            builder.endpointOverride(java.net.URI.create(endPoint));
        }

        return builder.build();
    }

    /**
     * 获取OSS配置
     * @return OssProperties实例
     */
    // @Bean
    public OssProperties ossProperties() {
        OssProperties properties = new OssProperties();
        properties.setAccessKey(accessKey);
        properties.setSecretKey(secretKey);
        properties.setHostName(hostName);
        properties.setTimeOut(timeOut);
        properties.setRegion(region);
        properties.setEndPoint(endPoint);
        properties.setBucket(bucket);
        return properties;
    }
}
