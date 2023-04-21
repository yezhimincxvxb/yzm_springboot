package com.yzm.file.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public enum FileUtils {
    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static final String localPath = "D://file//";

    public static boolean upload(MultipartFile file) {
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

    /**
     * 读取文件内容，作为字符串返回
     */
    public static String readFileAsString(String filePath) throws IOException {
        File file = new File(filePath);
        checkFileExists(filePath, file);
        checkFileLength(file);

        StringBuilder sb = new StringBuilder((int) (file.length()));
        // 创建字节输入流
        FileInputStream fis = new FileInputStream(filePath);
        // 创建一个长度为10240的Buffer
        byte[] buffer = new byte[10240];
        // 用于保存实际读取的字节数
        int hasRead = 0;
        while ((hasRead = fis.read(buffer)) > 0) {
            sb.append(new String(buffer, 0, hasRead));
        }
        fis.close();
        return sb.toString();
    }

    /**
     * 根据文件路径读取byte[] 数组
     */
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        checkFileExists(filePath, file);

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(file)))
        {
            short bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len;
            while (-1 != (len = in.read(buffer, 0, bufSize))) {
                bos.write(buffer, 0, len);
            }

            return bos.toByteArray();
        }
    }

    private static void checkFileLength(File file) throws IOException {
        if (file.length() > 1024 * 1024 * 1024) {
            throw new IOException("File is too large");
        }
    }

    private static void checkFileExists(String filePath, File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        }
    }

}
