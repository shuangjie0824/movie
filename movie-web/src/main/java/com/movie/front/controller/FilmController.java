package com.movie.front.controller;

import com.movie.api.model.entity.Film;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 电影接口
 */
@RestController
@Api(tags = "电影信息")
@RequestMapping("/api/film")
public class FilmController extends BaseController{

    @PostMapping("")
    //保存电影
    @ApiOperation(value = "保存电影信息")
    public void save(@RequestBody Film film) {
        filmService.save(film);
    }

    @GetMapping("")
    //列出所有电影
    @ApiOperation(value = "列出所有电影")
    public List<Film> list(String region, String type) {
        if (region != null && type != null) {
            return filmService.findByRegionAndType(region, type);
        }
        return filmService.findAll();
    }

    @GetMapping("/hot/{limit}")
    //获取热榜电影
    @ApiOperation(value = "获取热榜电影")
    public List<Film> listHots(@PathVariable Integer limit) {
        return filmService.findHots(limit);
    }

    @GetMapping("/name/{name}")
    //搜索电影
    @ApiOperation(value = "搜索电影")
    public List<Film> search(@PathVariable String name) {
        return filmService.findLikeName(name);
    }

    @GetMapping("/{id}")
    //根据id查找电影
    @ApiOperation(value = "根据id查找电影")
    public Film findById(@PathVariable String id) {
        return filmService.findById(id);
    }

    @PutMapping("")
    //更新电影
    @ApiOperation(value = "更新电影")
    public void update(@RequestBody Film film) {
        filmService.update(film);
    }

    @DeleteMapping("/{id}")
    //根据id删除电影
    @ApiOperation(value = "根据id删除电影")
    public void deleteById(@PathVariable String id) {
        filmService.deleteById(id);
    }

}
