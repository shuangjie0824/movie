package com.movie.front.controller;

import com.movie.api.model.entity.DailyWork;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 每日工作接口
 */
@RestController
@Api(tags = "每日工作")
@RequestMapping("/api/daily")
public class DailyWorkController extends BaseController{

    @PostMapping("")
    //添加每日工作
    @ApiOperation(value = "添加每日工作")
    public void create(@RequestBody DailyWork dailyWork){
        dailyWorkService.save(dailyWork);
    }

    @DeleteMapping("/{id}")
    //根据id删除每日工作
    @ApiOperation(value = "根据id删除每日工作")
    public void delete(@PathVariable String id){
        dailyWorkService.deleteById(id);
    }

    @GetMapping("")
    //查询所有
    @ApiOperation(value = "查询所有")
    public List<DailyWork> findAll(){
        return dailyWorkService.findAll();
    }

}
