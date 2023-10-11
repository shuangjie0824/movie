package com.movie.front.controller;

import com.movie.api.model.entity.Registration;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 活动接口
 */
@RestController
@Api(tags = "参加活动接口")
@RequestMapping("/api/registration")
public class RegistrationController extends BaseController{

    @PostMapping("")
    //报名活动
    @ApiOperation(value = "报名活动")
    public void create(@RequestBody Registration registration) throws Exception {
        registrationService.create(registration);
    }

    @GetMapping("")
    //查询所有报名信息
    @ApiOperation(value = "查询所有报名信息")
    public List<Registration> create() {
        return registrationService.findAll();
    }

}
