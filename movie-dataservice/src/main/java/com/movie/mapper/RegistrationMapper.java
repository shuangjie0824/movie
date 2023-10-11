package com.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.api.model.entity.Registration;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegistrationMapper extends BaseMapper<Registration> {
}
