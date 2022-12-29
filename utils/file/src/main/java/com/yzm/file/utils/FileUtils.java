package com.yzm.file.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public enum FileUtils {
    INSTANCE;

    private final Logger log = LoggerFactory.getLogger(getClass());

    public static final String localPath = "D://file//";

    public boolean upload(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            if (StringUtils.hasLength(fileName)) {
                log.info("filename={}", fileName);
                File dest = new File(localPath + "/" + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf(".")));
                if (!dest.exists()) dest.mkdirs();
                log.info("filename absolutePath={}", dest.getAbsolutePath());

                file.transferTo(dest);
                //dest.delete();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
