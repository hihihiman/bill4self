package com.example.bill4self;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.bill4self.**.mapper")
public class Bill4selfApplication {

    public static void main(String[] args) {
        SpringApplication.run(Bill4selfApplication.class, args);
    }

}
