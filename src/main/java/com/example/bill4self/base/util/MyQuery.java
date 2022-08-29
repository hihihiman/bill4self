package com.example.bill4self.base.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author Josh-ZJUT
 * @date 2022/8/29 16:30
 * @email dujianghui537885@163.com
 */
@Data
public class MyQuery<T> {
    private Page<T> page;

    private QueryWrapper<T> wrapper;
}
