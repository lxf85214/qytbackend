package com.qyt.qytbackend.common.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.StandardCharsets;

/**
 * Redis序列化配置类
 */
public class RedisSerializerConfig implements RedisSerializer<Object> {

    private final ObjectMapper objectMapper;

    public RedisSerializerConfig() {
        this.objectMapper = new ObjectMapper();
        // 注册Java 8时间模块
        this.objectMapper.registerModule(new JavaTimeModule());
        // 禁用序列化时的时间戳格式
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return new byte[0];
        }
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (Exception e) {
            throw new SerializationException("Could not serialize object: " + e.getMessage(), e);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, Object.class);
        } catch (Exception e) {
            throw new SerializationException("Could not deserialize bytes: " + e.getMessage(), e);
        }
    }
}
