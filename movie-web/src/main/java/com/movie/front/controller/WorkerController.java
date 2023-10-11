package com.movie.front.controller;


import com.movie.api.model.entity.Role;
import com.movie.api.model.entity.Worker;
import com.movie.api.model.pojo.LoginDto;
import com.movie.constants.Roles;
import com.movie.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工接口
 */
@RestController
@Api(tags = "员工接口")
@RequestMapping("/api/worker")
public class WorkerController extends BaseController{

    @PostMapping("/login")
    //员工登录
    @ApiOperation(value = "员工登录")
    public Map<String, Object> login(@RequestBody LoginDto dto) throws Exception {
        Worker worker = workerService.login(dto);
        Map<String, Object> map = new HashMap<>();
        //是否选择记住我
        long exp = dto.isRemember() ? JwtTokenUtil.REMEMBER_EXPIRATION_TIME : JwtTokenUtil.EXPIRATION_TIME;

        //查询登录的客服具有哪些权限
        List<String> roles = new ArrayList<>();
        //添加最基本的客服权限
        roles.add(Roles.ROLE_WORKER);

        for (Role role : roleService.listRolesByWorkerId(worker.getId())){
            roles.add(role.getValue());
        }
        map.put("token", JwtTokenUtil.createToken(dto.getUsername(), roles, exp));
        map.put("worker", worker);
        return map;
    }

    @PostMapping("")
    //添加员工
    @ApiOperation(value = "添加员工")
    public Worker create(@RequestBody Worker worker) throws Exception {
        return workerService.create(worker);
    }

    @GetMapping("")
    //查询全部员工
    @ApiOperation(value = "查询全部员工")
    public List<Worker> list(){
        return workerService.findAll();
    }

    @GetMapping("/{id}")
    //根据id查询员工
    @ApiOperation(value = "根据id查询员工")
    public Worker findById(@PathVariable String id){
        return workerService.findById(id);
    }

    @DeleteMapping("/{id}")
    //根据id删除员工
    @ApiOperation(value = "根据id删除员工")
    public void deleteById(@PathVariable String id){
        workerService.deleteById(id);
    }

    @PutMapping("")
    //更新员工信息
    @ApiOperation(value = "更新员工信息")
    public void update(@RequestBody Worker worker) throws Exception {
        workerService.update(worker);
    }

}
