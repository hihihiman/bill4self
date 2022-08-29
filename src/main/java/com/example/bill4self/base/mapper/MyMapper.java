package com.example.bill4self.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author Josh-ZJUT
 * @date 2022/8/29 17:02
 * @email dujianghui537885@163.com
 */
public interface MyMapper<T> extends BaseMapper<T> {

    /**
     * 逻辑删除带自动填充功能
     *
     * @param entity
     * @return
     */
    int deleteByIdWithFill(T entity);
}
