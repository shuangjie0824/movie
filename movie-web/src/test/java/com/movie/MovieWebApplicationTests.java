package com.movie;

import com.movie.api.model.entity.User;
import com.movie.front.MovieWebApplication;
import com.movie.front.controller.BaseController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MovieWebApplication.class)
class MovieWebApplicationTests extends BaseController {

    @Test
    void contextLoads() {
        List<User> users = userService.findAll();
        users.forEach(System.out::println);
    }

}
