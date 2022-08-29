package com.example.bill4self.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.example.bill4self.base.mapper.MyMapper;

/**
 * @author Josh-ZJUT
 * @date 2022/8/29 17:00
 * @email dujianghui537885@163.com
 */
public interface MyService<T> extends IService<T> {

    /**
     * 逻辑删除带自动填充功能
     *
     * @param entity
     * @return
     */
    default boolean removeByIdWithFill(T entity) {
        int count = ((MyMapper<T>) getBaseMapper()).deleteByIdWithFill(entity);
        return SqlHelper.retBool(count);
    }
}
