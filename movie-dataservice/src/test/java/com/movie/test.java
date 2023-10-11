package com.movie;

import com.movie.api.model.entity.User;
import com.movie.mapper.FilmMapper;
import com.movie.mapper.OrderMapper;
import com.movie.mapper.UserMapper;
import com.movie.service.OrderService;
import com.movie.service.UserService;

import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = DataServiceApplication.class)
public class test {

    @DubboReference(interfaceClass = UserService.class,version = "1.0")
    UserService userService;

    @Resource
    OrderMapper orderMapper;
    @Test
    void test(){
        List<User> all = userService.findAll();
        all.forEach(System.out::println);
    }
    @Resource
    OrderService orderService;

    @Resource
    FilmMapper filmMapper;

    @Test
    void test2(){
        System.out.println("----------------------------------------------test2----------------------------------------------");
//        System.out.println(filmMapper.selectById("dbd62f019c06c94a1994e282905b5cc5"));
        orderService.findAll().forEach(System.out::println);
//        orderMapper.selectList(null).forEach(System.out::println);

        System.out.println("----------------------------------------------test2----------------------------------------------");

    }

}
