package com.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.api.model.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
}
