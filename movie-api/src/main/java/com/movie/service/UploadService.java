package com.movie.service;

import com.movie.api.model.entity.Upload;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    //检验文件并且保存 返回值为Upload字段的id
    String checkAndSaveUpload(MultipartFile file);

    void deleteById(String id);

    Upload findById(String id);

}