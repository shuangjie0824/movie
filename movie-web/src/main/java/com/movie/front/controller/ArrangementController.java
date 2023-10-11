package com.movie.front.controller;

import com.movie.api.model.entity.Arrangement;
import com.movie.api.model.vo.ArrangementVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电影排片场次接口
 */
@RestController
@Api(tags = "电影排片场次")
@RequestMapping("/api/arrangement")
public class ArrangementController extends BaseController{

    @PostMapping("")
    //新增电影场次
    @ApiOperation("新增电影场次")
    public void save(@RequestBody Arrangement arrangement) {
        arrangementService.save(arrangement);
    }

    @PutMapping("")
    //修改排片信息
    @ApiOperation("修改排片信息")
    public Arrangement update(@RequestBody Arrangement arrangement) {
        return arrangementService.Update(arrangement);
    }

    @DeleteMapping("")
    //根据id删除排片
    @ApiOperation("根据id删除排片")
    public void delete(@RequestParam String id) {
        arrangementService.deleteById(id);
    }

    @GetMapping("")
    //列出电影排片
    @ApiOperation("列出电影排片")
    public List<Arrangement> list() {
        return arrangementService.findAll();
    }

    @GetMapping("/{id}")
    //查询拍片
    @ApiOperation("根据Id查询拍片")
    public Map<String, Object> findById(@PathVariable String id) {
        HashMap<String, Object> map = new HashMap<>();
        Arrangement arrangement = arrangementService.findById(id);
        map.put("film", filmService.findById(arrangement.getFid()));
        map.put("arrangement", arrangement);
        return map;
    }

    @GetMapping("/getSeats")
    //获取座位情况
    @ApiOperation("获取座位情况")
    public List<Integer> getSeats(String id) {
        return arrangementService.getSeatsHaveSelected(id);
    }

    @GetMapping("/film/{fid}")
    //查询某个电影的所有拍片
    @ApiOperation("查询某个电影的所有拍片")
    public ArrangementVO findByFilmId(@PathVariable String fid) {
        return arrangementService.findByFilmId(fid);
    }

}
