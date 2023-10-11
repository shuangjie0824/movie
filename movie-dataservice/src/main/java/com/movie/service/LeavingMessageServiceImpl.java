package com.movie.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.movie.api.model.entity.LeavingMessage;
import com.movie.api.model.entity.User;
import com.movie.api.model.vo.ActiveUserVO;
import com.movie.api.model.vo.LeavingMessageVO;

import com.movie.mapper.LeavingMessageMapper;
import com.movie.mapper.UserMapper;
import com.movie.utils.DataTimeUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = LeavingMessageService.class,version = "1.0")
@CacheConfig(cacheNames = "leavingMessage")
public class LeavingMessageServiceImpl implements LeavingMessageService {

    @Resource
    private LeavingMessageMapper leavingMessageMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public void save(LeavingMessage leavingMessage) {
        leavingMessage.setCreateAt(DataTimeUtil.getNowTimeString());
        leavingMessageMapper.insert(leavingMessage);
    }

    @Override
    public void reply(LeavingMessage leavingMessage) {
        leavingMessageMapper.updateById(leavingMessage);
    }

    @Override
    public List<LeavingMessageVO> findAll() {
        List<LeavingMessage> list = leavingMessageMapper.selectList(null);
        List<LeavingMessageVO> result = new ArrayList<>();
        for (LeavingMessage m : list) {
            User user = userMapper.selectById(m.getUid());
            result.add(new LeavingMessageVO(m.getId(), m, user));
        }
        return result;
    }

    @Override
    public List<ActiveUserVO> findActiveUsers() {
        List<ActiveUserVO> result = new ArrayList<>();
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            ActiveUserVO activeUserVO = new ActiveUserVO();
            activeUserVO.setUser(user);
            activeUserVO.setNumber(leavingMessageMapper.selectList(
                    new QueryWrapper<LeavingMessage>().in("uid", user.getId())).size());
            result.add(activeUserVO);
        }
        //按留言数量排序
        result.sort((v1, v2) -> v2.getNumber().compareTo(v1.getNumber()));
        return result;
    }

}
