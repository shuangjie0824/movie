package com.movie.front.controller;

import com.movie.api.model.pojo.LoginDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员接口
 */
@RestController
@Api(tags = "管理员接口")
@RequestMapping("/api/admin")
public class AdminController extends BaseController{
    //管理员登陆
    @PostMapping("/login")
    @ApiOperation(value = "管理员登陆")
    public Map<String, String> login(@RequestBody LoginDto loginDto) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", adminService.login(loginDto));
        return map;
    }

}
