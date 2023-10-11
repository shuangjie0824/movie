package com.movie.front.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 处理请求地址 拦截这些地址 使用跨域处理逻辑
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:8080")// 可跨域的域名，可以为'*'
                .allowedOriginPatterns("http://localhost:8081")
                .allowedOriginPatterns("http://localhost:8083")
                // 支持的跨域请求的方式
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS","HEAD")
                .allowCredentials(true)// 是否允许浏览器发送Cookie
                .maxAge(3600)
                // 支持跨域的请求头，在请求头包含哪些数据时可以支持跨域功能
                .allowedHeaders("*");
    }
}
