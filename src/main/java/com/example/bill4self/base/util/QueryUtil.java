package com.example.bill4self.base.util;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * @author Josh-ZJUT
 * @date 2022/8/29 16:31
 * @email dujianghui537885@163.com
 */
public class QueryUtil {

    /**
     * 构建 MyQuery 对象，服务于分页查询方法
     *
     * @param param
     * @param <T>
     * @return
     */
    public static <T> MyQuery<T> buildMyQuery(Map<String, String> param) {
        MyQuery<T> myQuery = new MyQuery<>();
        Long page = 1L;
        Long limit = 10L;

        QueryWrapper<T> wrapper = new QueryWrapper<>();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            final String value = entry.getValue();
            if (StrUtil.isNotBlank(value)) {
                String key = entry.getKey();
                if ("page".equals(key)) {
                    page = Long.parseLong(value);
                } else if ("limit".equals(key)) {
                    limit = Long.parseLong(value);
                } else {
                    if (!key.contains("$")) {
                        key = "$" + key;
                    }
                    final String[] keyArray = key.split("\\$");
                    switch (keyArray[0]) {
                        case "like":
                            wrapper.like(keyArray[1], value);
                            break;
                        case "ge":
                            wrapper.ge(keyArray[1], value);
                            break;
                        case "le":
                            wrapper.le(keyArray[1], value);
                            break;
                        case "eq":
                        default:
                            wrapper.eq(keyArray[1], value);
                            break;
                    }
                }
            }
        }
        myQuery.setPage(new Page<>(page, limit));
        myQuery.setWrapper(wrapper);
        return myQuery;
    }
}
