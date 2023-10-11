package com.movie.front;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwaggerBootstrapUI
@EnableSwagger2
@EnableDubbo
//@EnableCaching
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WebApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("----------------------WEB启动成功----------------------");
    }
}
