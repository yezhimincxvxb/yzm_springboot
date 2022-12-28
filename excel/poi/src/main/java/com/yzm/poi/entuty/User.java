package com.yzm.poi.entuty;

import com.yzm.poi.utils.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @ExcelColumn(value = "ID", col = 1, colWidths = 2000)
    private Long id;
    @ExcelColumn(value = "用户名", col = 2, colWidths = 5000)
    private String username;
    @ExcelColumn(value = "邮箱", col = 4, colWidths = 5000)
    private String email;
    @ExcelColumn(value = "手机号", col = 3, colWidths = 5000)
    private String phone;
    @ExcelColumn(value = "金额", col = 11, colWidths = 5000)
    private Double money;
    @ExcelColumn(value = "描述", col = 99, colWidths = 8000)
    private String description;

}
