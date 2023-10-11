package com.movie.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.movie.api.model.entity.Admin;
import com.movie.api.model.pojo.LoginDto;

import com.movie.constants.Roles;
import com.movie.mapper.AdminMapper;
import com.movie.utils.JwtTokenUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = AdminService.class,version = "1.0")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public String login(LoginDto loginDto) throws Exception {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.in("username", loginDto.getUsername());
        wrapper.in("password", loginDto.getPassword());
        Admin admin = adminMapper.selectOne(wrapper);
        if (admin == null) throw new Exception("用户名密码错误");
        //是否选择记住我
        long exp = loginDto.isRemember() ? JwtTokenUtil.REMEMBER_EXPIRATION_TIME : JwtTokenUtil.EXPIRATION_TIME;
        List<String> roles = new ArrayList<>();
        roles.add(Roles.ROLE_ADMIN);
        return JwtTokenUtil.createToken(loginDto.getUsername(), roles, exp);
    }

}
