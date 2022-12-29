package com.yzm.file.controller;

import com.yzm.file.utils.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 批量文件操作
     */
    @PostMapping("/batchUpload")
    public boolean batchUpload(HttpServletRequest request) {
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
            if (fileMap.values().size() > 0) {
                for (MultipartFile file : fileMap.values()) {
                    if (!FileUtils.INSTANCE.upload(file)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 批量文件操作(name=file有多个)
     */
    @PostMapping("/uploads")
    public boolean uploads(@RequestParam("file") MultipartFile[] files) {
        if (files.length > 0) {
            for (MultipartFile file : files) {
                if (!FileUtils.INSTANCE.upload(file)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 单个文件操作(name=file)
     */
    @PostMapping("/upload")
    public boolean upload(@RequestParam("file") MultipartFile file) {
        return FileUtils.INSTANCE.upload(file);
    }

    /**
     * 文件下载
     */
    @GetMapping("/download/{fileName}")
    public String download(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        response.setHeader("content-type", "image/png");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        BufferedInputStream bis;
        OutputStream os;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("D:/file/" + fileName)));

            byte[] buff = new byte[1024 * 8];
            int len;
            while ((len = bis.read(buff)) != -1) {
                os.write(buff, 0, len);
                os.flush();
            }

            bis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
