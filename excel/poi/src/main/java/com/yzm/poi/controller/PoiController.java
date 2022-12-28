package com.yzm.poi.controller;

import com.yzm.poi.entuty.User;
import com.yzm.poi.utils.PoiUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

@RestController
public class PoiController {


    @GetMapping("/excelExport")
    public void download(HttpServletResponse response) {
        try {

            List<User> users = Arrays.asList(
                    User.builder().id(1L).username("pdai").email("pdai@pdai.tech").phone("121231231231").money(100.0).description("hello world").build(),
                    User.builder().id(2L).username("yzm").email("yzm@pdai.tech").phone("13232596678").money(200.0).description("hello yzm").build()
            );

            SXSSFWorkbook workbook = PoiUtils.excelExport(users, User.class);
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=user_excel_" + System.currentTimeMillis() + ".xlsx");
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            workbook.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/excelImport")
    public boolean upload(MultipartFile file) {
        try {
            List<User> users = PoiUtils.excelImport(file.getInputStream(), User.class);
            users.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
