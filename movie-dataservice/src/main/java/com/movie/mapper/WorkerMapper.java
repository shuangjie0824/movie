package com.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.api.model.entity.Worker;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkerMapper extends BaseMapper<Worker> {
}
