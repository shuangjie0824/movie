package com.movie.front.controller;

import com.movie.api.model.entity.Poster;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页海报接口
 */
@RestController
@Api(tags = "首页海报")
@RequestMapping("/api/poster")
public class PosterController extends BaseController{

    @PostMapping("")
    //添加首页海报
    @ApiOperation(value = "添加首页海报")
    public void save(@RequestBody Poster poster) {
        posterService.save(poster);
    }

    @PutMapping("")
    //更新海报
    @ApiOperation(value = "更新海报")
    public void update(@RequestBody Poster poster) {
        posterService.update(poster);
    }

    @GetMapping("")
    //获取所有海报
    @ApiOperation(value = "获取所有海报")
    public List<Poster> list(String status) {
        if (status != null) {
            return posterService.findByStatus(Boolean.parseBoolean(status));
        }
        return posterService.findAll();
    }

    @DeleteMapping("/{id}")
    //删除海报
    @ApiOperation(value = "删除海报")
    public void delete(@PathVariable String id) {
        posterService.deleteById(id);
    }

    @DeleteMapping("")
    //删除所有海报
    @ApiOperation(value = "删除所有海报")
    public void deleteAll() {
        posterService.deleteAll();
    }

}
