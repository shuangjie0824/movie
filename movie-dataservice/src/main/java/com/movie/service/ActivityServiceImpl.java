package com.movie.service;


import com.movie.api.model.entity.Activity;

import com.movie.mapper.ActivityMapper;
import com.movie.utils.DataTimeUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@DubboService(interfaceClass = ActivityService.class,version = "1.0")
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public void create(Activity activity) {
        activity.setId(UUID.randomUUID().toString());
        activity.setCreateAt(DataTimeUtil.getNowTimeString());
        activityMapper.insert(activity);
    }

    @Override
    public Activity findById(String id) {
        return activityMapper.selectById(id);
    }

    @Override
    public List<Activity> findAll() {
        return activityMapper.selectList(null);
    }

    @Override
    public void deleteById(String id) {
        activityMapper.deleteById(id);
    }

}
