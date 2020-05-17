package com.tao.shenkeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tao.shenkeng.mapper")
public class ShenkengApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShenkengApplication.class, args);
    }

}
