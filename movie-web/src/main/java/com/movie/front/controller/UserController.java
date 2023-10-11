package com.movie.front.controller;


import com.movie.api.model.entity.User;
import com.movie.api.model.pojo.LoginDto;
import com.movie.constants.Roles;
import com.movie.utils.JwtTokenUtil;
import com.movie.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户接口
 */
@RestController
@Api(tags = "用户接口")
@RequestMapping("/api/user")
public class UserController extends BaseController{


    @PostMapping("/login")
    //用户登录
    @ApiOperation(value = "用户登录")
    public Map<String, Object> login(@RequestBody LoginDto dto) throws Exception {
        User user = userService.login(dto);
        Map<String, Object> map = new HashMap<>();
        //是否选择记住我
        long exp = dto.isRemember() ? JwtTokenUtil.REMEMBER_EXPIRATION_TIME : JwtTokenUtil.EXPIRATION_TIME;
        List<String> roles = new ArrayList<>();
        roles.add(Roles.ROLE_USER);
        map.put("token", JwtTokenUtil.createToken(dto.getUsername(), roles, exp));
        map.put("user", user);
        return map;
    }

    @GetMapping("")
    //查找所有用户
    @ApiOperation(value = "查找所有用户")
    public List<User> findAll() {
        System.out.println("======================================fundAll======================================");
        return userService.findAll();
    }

    @PutMapping("")
    //更新用户
    @ApiOperation(value = "更新用户")
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("/{id}")
    //查找用户
    @ApiOperation(value = "查找用户")
    public User findById(@PathVariable String id) {
        System.out.println("id = " + id);
        if (id!=null && id !=""&&id.length()>1) return userService.findById(id);
        else return null;
    }

    @PostMapping("/register")
    //用户注册
    @ApiOperation(value = "用户注册")
    public User save(@RequestBody User user) throws Exception {

        return userService.save(user);
    }

}
