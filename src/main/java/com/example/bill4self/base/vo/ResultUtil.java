package com.example.bill4self.base.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;

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
    public static Result<Map<String, Object>> buildPageResult(IPage<?> page) {
        Map<String, Object> data = new HashMap<>(4);
        data.put("count", page.getTotal());
        data.put("current", page.getCurrent());
        data.put("page_size", page.getSize());
        data.put("records", page.getRecords());
        return Result.success(data);
    }

    /**
     * 成功或失败的响应信息
     *
     * @param success
     * @return
     */
    public static Result<Object> buildResult(boolean success) {
        if (success) {
            return Result.success(null);
        } else {
            return Result.error("操作失败");
        }
    }
}
