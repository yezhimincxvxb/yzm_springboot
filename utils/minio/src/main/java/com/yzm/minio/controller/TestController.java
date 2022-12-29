package com.yzm.minio.controller;

import com.yzm.minio.config.UploadResponse;
import com.yzm.minio.utils.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private MinioUtil minioUtil;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public UploadResponse minioUpload(MultipartFile file) {
        UploadResponse response = null;
        try {
            response = minioUtil.uploadFile(file, "bucket01");
        } catch (Exception e) {
            log.error("上传失败", e);
        }
        return response;
    }

    /**
     * 文件下载
     */
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        minioUtil.downloadFile(response, "bucket01", fileName);
    }

    /**
     * 上传视频
     */
    @PostMapping("/uploadVideo")
    public UploadResponse uploadVideo(@RequestParam(value = "file") MultipartFile file) {
        UploadResponse response = null;
        try {
            response = minioUtil.uploadVideo(file, "video-test");
        } catch (Exception e) {
            log.error("上传失败", e);
        }
        return response;
    }

}

