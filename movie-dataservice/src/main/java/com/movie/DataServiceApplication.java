package com.movie;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableDubbo
@EnableCaching
@MapperScan(basePackages = "com/movie/mapper")
@SpringBootApplication
public class DataServiceApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DataServiceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("----------------------DataService启动成功----------------------");
    }
}
