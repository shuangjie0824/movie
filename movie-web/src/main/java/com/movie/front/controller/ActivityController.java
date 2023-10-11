package com.movie.front.controller;

import com.movie.api.model.entity.Activity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = "活动接口")
@RequestMapping("/api/activity")
public class ActivityController extends BaseController{

    @PostMapping("")
    //新增活动
    @ApiOperation(value = "新增活动")
    public void create(@RequestBody Activity activity) {
        activityService.create(activity);
    }

    @GetMapping("")
    //获取全部活动
    @ApiOperation(value = "获取全部活动")
    public List<Activity> findAll() {
        return activityService.findAll();
    }

    @GetMapping("{id}")
    //根据id查找活动
    @ApiOperation(value = "根据id查找活动")
    public Activity findById(@PathVariable String id) {
        return activityService.findById(id);
    }

    @DeleteMapping("{id}")
    //删除活动
    @ApiOperation(value = "删除活动")
    public void delete(@PathVariable String id) {
        activityService.deleteById(id);
    }

}
