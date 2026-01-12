package com.qyt.qytbackend.rpc.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * HTTP客户端工具类
 * 用于RPC层调用外部接口
 *
 * @author qyt
 * @date 2024
 */
@Slf4j
@Component
public class RpcHttpClient {

    private final java.net.http.HttpClient httpClient;

    public RpcHttpClient() {
        this.httpClient = java.net.http.HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    /**
     * 发送GET请求
     *
     * @param url 请求URL
     * @return 响应内容
     */
    public String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * 发送GET请求（带请求头）
     *
     * @param url     请求URL
     * @param headers 请求头
     * @return 响应内容
     */
    public String doGet(String url, java.util.Map<String, String> headers) {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(30))
                    .GET();

            if (headers != null) {
                headers.forEach(requestBuilder::header);
            }

            HttpRequest request = requestBuilder.build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            log.debug("GET请求 - URL: {}, 状态码: {}", url, response.statusCode());
            return response.body();

        } catch (IOException | InterruptedException e) {
            log.error("GET请求失败 - URL: {}", url, e);
            throw new RuntimeException("HTTP请求失败", e);
        }
    }

    /**
     * 发送POST请求
     *
     * @param url  请求URL
     * @param body 请求体
     * @return 响应内容
     */
    public String doPost(String url, String body) {
        return doPost(url, body, null);
    }

    /**
     * 发送POST请求（带请求头）
     *
     * @param url     请求URL
     * @param body    请求体
     * @param headers 请求头
     * @return 响应内容
     */
    public String doPost(String url, String body, java.util.Map<String, String> headers) {
        try {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(30))
                    .POST(HttpRequest.BodyPublishers.ofString(body));

            if (headers != null) {
                headers.forEach(requestBuilder::header);
            } else {
                requestBuilder.header("Content-Type", "application/json");
            }

            HttpRequest request = requestBuilder.build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            log.debug("POST请求 - URL: {}, 状态码: {}", url, response.statusCode());
            return response.body();

        } catch (IOException | InterruptedException e) {
            log.error("POST请求失败 - URL: {}", url, e);
            throw new RuntimeException("HTTP请求失败", e);
        }
    }
}
