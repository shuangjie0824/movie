package com.movie.front.controller;

import com.movie.api.model.entity.FilmEvaluate;
import com.movie.api.model.vo.FilmEvaluateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 电影评价接口
 */
@RestController
@Api(tags = "电影评价")
@RequestMapping("/api/fe")
public class FilmEvaluateController extends BaseController{

    @PostMapping("")
    //评论电影
    @ApiOperation(value = "评论电影")
    public void save(@RequestBody FilmEvaluate filmEvaluate) throws Exception {
        filmEvaluateService.save(filmEvaluate);
    }

    @GetMapping("")
    //获取电影评论
    @ApiOperation(value = "获取电影评论")
    public List<FilmEvaluateVO> list(@RequestParam(name = "fid") String fid) {
        if (fid != null) {
            return filmEvaluateService.findAllByFilmId(fid);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    //根据id删除评论
    @ApiOperation(value = "根据id删除评论")
    public void remove(@PathVariable String id) {
        filmEvaluateService.deleteById(id);
    }

    @DeleteMapping("")
    //删除该电影的所有评分
    @ApiOperation(value = "删除该电影的所有评分")
    public void removeAll(@RequestParam(name = "fid") String fid) {
        filmEvaluateService.deleteAllByFilmId(fid);
    }

}
