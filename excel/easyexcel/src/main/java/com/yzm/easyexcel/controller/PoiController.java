package com.yzm.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.yzm.easyexcel.entuty.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class PoiController {

    @GetMapping("/excelExport")
    public void download(HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=user_excel_" + System.currentTimeMillis() + ".xlsx");

        List<User> users = Arrays.asList(
                User.builder().id(1L).username("pdai").birthday(new Date()).gender(1).money(199.999).build(),
                User.builder().id(2L).username("yzm").birthday(new Date()).gender(0).money(1998.01).build()
        );

        EasyExcel.write(response.getOutputStream())
                .head(User.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("用户列表")
                .doWrite(users);
    }


    @PostMapping("/excelImport")
    public boolean excelImport(MultipartFile file) {
        try {
            List<User> users = EasyExcel.read(file.getInputStream())
                    .head(User.class)
                    .sheet()
                    .doReadSync();
            users.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
