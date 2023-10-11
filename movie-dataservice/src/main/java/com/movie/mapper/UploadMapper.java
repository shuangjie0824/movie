package com.movie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.movie.api.model.entity.Upload;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadMapper extends BaseMapper<Upload> {
}
