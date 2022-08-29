package com.example.bill4self.base.handler;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteByIdWithFill;

import java.util.List;

/**
 * @author Josh-ZJUT
 * @date 2022/8/29 17:09
 * @email dujianghui537885@163.com
 */
public class MysqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        // 根据id逻辑删除数据，并带字段填充功能
        methodList.add(new LogicDeleteByIdWithFill());
        return methodList;
    }
}
