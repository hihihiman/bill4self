package com.example.bill4self.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
@ConditionalOnExpression("${knife4j.enable}") //开启访问接口文档的权限  **knife4j.enable是在yml配置文件中配置为true**
public class Knife4jConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("基础功能")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.bill4self.system.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        String TITLE = "bill4self RESTful APIs";
        return new ApiInfoBuilder()
                .title(TITLE)
                .description("# " + TITLE)
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}