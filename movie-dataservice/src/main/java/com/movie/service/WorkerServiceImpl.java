package com.movie.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.movie.api.model.entity.Role;
import com.movie.api.model.entity.Worker;
import com.movie.api.model.pojo.LoginDto;

import com.movie.constants.Roles;
import com.movie.mapper.RoleMapper;
import com.movie.mapper.WorkerMapper;
import com.movie.utils.DataTimeUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@DubboService(interfaceClass = WorkerService.class,version = "1.0")
public class WorkerServiceImpl implements WorkerService {

    @Resource
    private WorkerMapper workerMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private RoleMapper roleMapper;

    @Value("${movie.config.password-salt}")
    private String passwordSalt;

    @Override
    public Worker create(Worker worker) throws Exception {
        if (workerMapper.selectOne(new QueryWrapper<Worker>().in("username", worker.getUsername())) != null) {
            throw new Exception("已存在的用户名");
        }
        String now = DataTimeUtil.getNowTimeString();
        worker.setEntry(true);
        // 加盐 加密
        String newPassword = DigestUtils.md5Hex( worker.getPassword() + passwordSalt);
        worker.setPassword(newPassword);

        worker.setId(UUID.randomUUID().toString());
        worker.setCreateAt(now);
        worker.setUpdateAt(now);
        workerMapper.insert(worker);
        //添加worker权限
        roleMapper.insert(new Role(UUID.randomUUID().toString(), worker.getId(), Roles.ROLE_WORKER, now));
        return worker;
    }

    @Override
    public Worker login(LoginDto dto) throws Exception {
        QueryWrapper<Worker> wrapper = new QueryWrapper<>();
        wrapper.in("username", dto.getUsername());
        Worker worker = workerMapper.selectOne(wrapper);
        if (worker == null) throw new Exception("用户名或密码错误");
        // 加盐 加密
        String newPassword = DigestUtils.md5Hex( dto.getPassword() + passwordSalt);

        if (!worker.getPassword().equals(newPassword)) throw new Exception("用户名或密码错误");
        return worker;
    }

    @Override
    public void update(Worker worker) throws Exception {
        Worker one = workerMapper.selectOne(new QueryWrapper<Worker>().in("username", worker.getUsername()));
        if (one != null && !one.getId().equals(worker.getId())) {
            throw new Exception("已存在的用户名");
        }
        // 加盐 加密
        String newPassword = DigestUtils.md5Hex( worker.getPassword() + passwordSalt);
        worker.setPassword(newPassword);

        worker.setUpdateAt(DataTimeUtil.getNowTimeString());
        workerMapper.updateById(worker);
    }

    @Override
    public List<Worker> findAll() {
        return workerMapper.selectList(null);
    }

    @Override
    public Worker findById(String id) {
        return workerMapper.selectById(id);
    }

    @Override
    public void deleteById(String id) {
        workerMapper.deleteById(id);
        //删除所有权限
        roleService.deleteWorkerAllRoles(id);
    }

}
