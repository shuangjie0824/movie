package com.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.api.model.entity.Film;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilmMapper extends BaseMapper<Film> {
}
