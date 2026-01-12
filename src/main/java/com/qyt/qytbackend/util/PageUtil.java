package com.qyt.qytbackend.util;

import com.qyt.qytbackend.common.constant.CommonConstants;

/**
 * 分页工具类
 *
 * @author qyt
 * @date 2024
 */
public class PageUtil {

    /**
     * 获取页码（从1开始）
     */
    public static Integer getPage(Integer page) {
        if (page == null || page < 1) {
            return CommonConstants.DEFAULT_PAGE;
        }
        return page;
    }

    /**
     * 获取每页数量
     */
    public static Integer getSize(Integer size) {
        if (size == null || size < 1) {
            return CommonConstants.DEFAULT_PAGE_SIZE;
        }
        if (size > CommonConstants.MAX_PAGE_SIZE) {
            return CommonConstants.MAX_PAGE_SIZE;
        }
        return size;
    }

    /**
     * 计算偏移量
     */
    public static Integer getOffset(Integer page, Integer size) {
        page = getPage(page);
        size = getSize(size);
        return (page - 1) * size;
    }

    private PageUtil() {
        // 工具类，禁止实例化
    }
}
