package com.movie.front.controller;


import com.movie.api.model.entity.Upload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import com.movie.front.annotation.DisableBaseResponse;
import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 上传接口
 * 上传图片存放为二进制到mysql
 */
@RestController
@Api(tags = "上传图片&处理接口")
@RequestMapping("/api/upload")
public class UploadController extends BaseController{

    @Value("${server.port}")
    private String serverPort;


    @PostMapping("")
    //上传图片
    @ApiOperation(value = "上传图片")
    public String upload(MultipartFile file) throws Exception {
        if (file == null) throw new Exception("请求参数缺失");
        if (file.isEmpty()) {
            throw new Exception("上传失败，请选择文件");
        }
        return "http://localhost:" + serverPort + "/api/upload?id=" + uploadService.checkAndSaveUpload(file);
    }

    @DeleteMapping("")
    //删除图片
    @ApiOperation(value = "删除图片")
    public void delete(@RequestParam("id") String id) {
        uploadService.deleteById(id);
    }

    @GetMapping("")
    //获取图片
    @ApiOperation(value = "获取图片")
    public void get(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
        if ("".equals(id)) {
            return;
        }
        Upload upload = uploadService.findById(id);
        if (upload == null) {
            throw new Exception("图片不存在");
        }
        byte[] data = upload.getBytes();
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputStream = response.getOutputStream();
        InputStream in = new ByteArrayInputStream(data);
        int len;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf, 0, 1024)) != -1) {
            outputStream.write(buf, 0, len);
        }
        outputStream.close();
    }

}
