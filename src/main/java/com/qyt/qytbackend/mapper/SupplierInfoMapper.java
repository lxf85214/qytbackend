package com.qyt.qytbackend.mapper;

import com.qyt.qytbackend.entity.SupplierInfo;

import java.util.List;

/**
 * 供应商信息表Mapper接口
 */
public interface SupplierInfoMapper {
    /**
     * 查询供应商信息
     *
     * @param id 供应商信息主键
     * @return 供应商信息
     */
    SupplierInfo selectSupplierInfoById(Long id);

    /**
     * 查询供应商信息列表
     *
     * @param supplierInfo 供应商信息
     * @return 供应商信息集合
     */
    List<SupplierInfo> selectSupplierInfoList(SupplierInfo supplierInfo);

    /**
     * 新增供应商信息
     *
     * @param supplierInfo 供应商信息
     * @return 结果
     */
    int insertSupplierInfo(SupplierInfo supplierInfo);

    /**
     * 修改供应商信息
     *
     * @param supplierInfo 供应商信息
     * @return 结果
     */
    int updateSupplierInfo(SupplierInfo supplierInfo);

    /**
     * 删除供应商信息
     *
     * @param id 供应商信息主键
     * @return 结果
     */
    int deleteSupplierInfoById(Long id);

    /**
     * 批量删除供应商信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSupplierInfoByIds(String[] ids);
}
