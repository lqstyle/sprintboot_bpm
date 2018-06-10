package com.kpmg.bpm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kpmg.bpm.mapper")
public class BpmApplication {
    public static void main(String[] args) {
        SpringApplication.run(BpmApplication.class, args);
    }
}
