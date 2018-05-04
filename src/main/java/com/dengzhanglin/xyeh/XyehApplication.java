package com.dengzhanglin.xyeh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dengzhanglin.xyeh.mapper")
public class XyehApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyehApplication.class, args);
    }
}
