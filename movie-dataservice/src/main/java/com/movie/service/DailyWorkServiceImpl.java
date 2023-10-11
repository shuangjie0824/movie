package com.movie.service;


import com.movie.api.model.entity.DailyWork;

import com.movie.mapper.DailyWorkMapper;
import com.movie.utils.DataTimeUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@DubboService(interfaceClass = DailyWorkService.class,version = "1.0")
public class DailyWorkServiceImpl implements DailyWorkService {

    @Resource
    private DailyWorkMapper dailyWorkMapper;

    @Override
    public void save(DailyWork dailyWork) {
        dailyWork.setId(UUID.randomUUID().toString());
        dailyWork.setCreateAt(DataTimeUtil.getNowTimeString());
        dailyWorkMapper.insert(dailyWork);
    }

    @Override
    public List<DailyWork> findAll() {
        return dailyWorkMapper.selectList(null);
    }

    @Override
    public void deleteById(String id) {
        dailyWorkMapper.deleteById(id);
    }

}
