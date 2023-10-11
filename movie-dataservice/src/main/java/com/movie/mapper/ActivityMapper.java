package com.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.api.model.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
}
