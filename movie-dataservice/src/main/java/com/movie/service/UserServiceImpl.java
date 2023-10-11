package com.movie.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.movie.api.model.entity.User;
import com.movie.api.model.pojo.LoginDto;
import com.movie.mapper.UserMapper;
import com.movie.utils.DataTimeUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;


import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@DubboService(interfaceClass = UserService.class,version = "1.0")
public class UserServiceImpl implements UserService {

    @Value("${movie.config.password-salt}")
    private String passwordSalt;

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(LoginDto dto) throws Exception {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.in("username", dto.getUsername());
        User user = userMapper.selectOne(wrapper);
        if (user == null) throw new Exception("用户名或密码错误");

        // 给原始的密码加盐（salt).对比数据库中的密码
        String newPassword = DigestUtils.md5Hex( dto.getPassword() + passwordSalt);

        // 对比加密后的密码
        if (!user.getPassword().equals(newPassword)) throw new Exception("用户名或密码错误");
        return user;
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public User findById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public User update(User user) {
        //注册密码的md5二次加密。 给原始的密码加盐（salt)
        String newPassword = DigestUtils.md5Hex( user.getPassword() + passwordSalt);
        user.setPassword(newPassword);
        userMapper.updateById(user);
        return user;
    }

    @Override
    public User save(User user) throws Exception {
        if (findByUsername(user.getUsername()) != null) {
            throw new Exception("用户名已注册");
        }
        String now = DataTimeUtil.getNowTimeString();
        user.setId(UUID.randomUUID().toString());
        user.setCreateAt(now);
        user.setUpdateAt(now);
        //注册密码的md5二次加密。 给原始的密码加盐（salt)
        String newPassword = DigestUtils.md5Hex( user.getPassword() + passwordSalt);
        user.setPassword(newPassword);

        userMapper.insert(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void deleteById(String id) {
        userMapper.deleteById(id);
    }

}
