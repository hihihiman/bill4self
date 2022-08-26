package com.example.bill4self;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author lichennan
 * @since 2021年10月24日 16:52
 */
@SpringBootTest
public class CodeGenerator {
    private static final String projectPath = System.getProperty("user.dir");

    private static final String URL = "jdbc:mysql://localhost:3306/bill4self?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "xuebushen";
    // 数据源配置
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(URL, USERNAME, PASSWORD)
            .typeConvert(new MySqlTypeConvert());


    @Test
    public void generatorCode() {

        FastAutoGenerator
                .create(DATA_SOURCE_CONFIG)
                //全局配置
                .globalConfig(builder -> {
                    builder.author("Josh") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir() // 执行完毕不打开文件夹
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.example.bill4self.generator") // 设置父包名
                            .controller("controller") //生成controller层
                            .entity("entity") //生成实体层
                            .service("service") //生成服务层
                            .mapper("mapper"); //生成mapper层
                    // .moduleName("mybatisplus");
                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("role_resource") // 设置需要生成的表名
//                            .addTablePrefix("tbl_")// 设置过滤表前缀
                            .serviceBuilder() //开启service策略配置
                            .formatServiceFileName("%sService") //取消Service前的I
                            .controllerBuilder() //开启controller策略配置
                            .enableRestStyle() //配置restful风格
                            .enableHyphenStyle() //url中驼峰转连字符
                            .entityBuilder() //开启实体类配置
                            .enableLombok() //开启lombok
                            .enableChainModel() //开启lombok链式操作

                            // 继承基类
                            .superClass("com.example.bill4self.base.entity.BaseEntity")
                            .addSuperEntityColumns("create_time","create_account_id","modified_time","modified_account_id","deleted")

                            // 自动填充字段
                            .addTableFills(Arrays.asList(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("create_account_id", FieldFill.INSERT),
                                    new Column("modified_time", FieldFill.INSERT_UPDATE),
                                    new Column("modified_account_id", FieldFill.INSERT_UPDATE)
                            ))
                            // 逻辑删除字段
                            .logicDeleteColumnName("deleted")
                    ;
                })
                //模板配置
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                //执行
                .execute();
    }
}