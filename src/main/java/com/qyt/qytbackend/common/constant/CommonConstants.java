package com.qyt.qytbackend.common.constant;

/**
 * 通用常量类
 *
 * @author qyt
 * @date 2024
 */
public class CommonConstants {

    /**
     * 默认页码
     */
    public static final Integer DEFAULT_PAGE = 1;

    /**
     * 默认每页数量
     */
    public static final Integer DEFAULT_PAGE_SIZE = 10;

    /**
     * 最大每页数量
     */
    public static final Integer MAX_PAGE_SIZE = 100;

    /**
     * 逻辑删除 - 未删除
     */
    public static final Integer NOT_DELETED = 0;

    /**
     * 逻辑删除 - 已删除
     */
    public static final Integer DELETED = 1;

    /**
     * 成功状态码
     */
    public static final Integer SUCCESS_CODE = 200;

    /**
     * 失败状态码
     */
    public static final Integer ERROR_CODE = 500;

    /**
     * API版本前缀
     */
    public static final String API_PREFIX = "/api/v1";

    private CommonConstants() {
        // 工具类，禁止实例化
    }
}
