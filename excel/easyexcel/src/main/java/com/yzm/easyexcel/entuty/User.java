package com.yzm.easyexcel.entuty;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.yzm.easyexcel.utils.GenderConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.RoundingMode;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @ExcelIgnore
    private Long id;
    @ExcelProperty("用户名")
    @ColumnWidth(20)
    private String username;
    @ColumnWidth(20)
    @ExcelProperty("出生日期")
    @DateTimeFormat("yyyy-MM-dd")
    private Date birthday;
    @ColumnWidth(10)
    @ExcelProperty("金额")
    @NumberFormat(roundingMode = RoundingMode.HALF_UP)
    private Double money;
    @ColumnWidth(10)
    @ExcelProperty(value = "性别", converter = GenderConverter.class)
    private Integer gender;

}
