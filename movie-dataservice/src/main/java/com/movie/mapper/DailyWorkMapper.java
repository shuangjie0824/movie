package com.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.api.model.entity.DailyWork;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DailyWorkMapper extends BaseMapper<DailyWork> {
}
