package com.example.bill4self.base.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Josh-ZJUT
 * @date 2022/8/27 15:31
 * @email dujianghui537885@163.com
 */
public class ResultUtil {
    /**
     * 分页查询的返回结果
     *
     * @param page
     * @return
     */
    public static Result<Map<String,Object>> buildPageResult(IPage<?> page){
        Map<String,Object> data = new HashMap<>(4);
        data.put("count",page.getTotal());
        data.put("current",page.getCurrent());
        data.put("page_size",page.getSize());
        data.put("records",page.getRecords());
        return Result.success(data);
    }
}
