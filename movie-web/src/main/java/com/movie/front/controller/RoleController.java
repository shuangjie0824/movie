package com.movie.front.controller;

import com.movie.api.model.entity.Role;
import com.movie.constants.Roles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限接口
 * 对工作人员的权限管理
 * 所有接口都需要管理员权限验证
 */
@RestController
@Api(tags = "权限管理")
@RequestMapping("/api/role")
public class RoleController extends BaseController{

    @GetMapping("/system")
    @ApiOperation(value = "查看系统权限")
    //查看系统设置有哪些权限
    public String[] listSystemRoles() {
        return Roles.roles;
    }

    @PostMapping("")
    //添加权限
    @ApiOperation(value = "添加权限")
    public Role create(@RequestBody Role role) throws Exception {
        return roleService.create(role);
    }

    @GetMapping("")
    //查询员工的权限
    @ApiOperation(value = "查询员工的权限")
    public List<Role> listByWorkerId(String wid) {
        return roleService.listRolesByWorkerId(wid);
    }

    @DeleteMapping("/{id}")
    //删除权限
    @ApiOperation(value = "删除权限")
    public void delete(@PathVariable String id) throws Exception {
        roleService.deleteById(id);
    }


}
