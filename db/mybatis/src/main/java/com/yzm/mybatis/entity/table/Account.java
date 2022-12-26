package com.yzm.mybatis.entity.table;

import com.yzm.mybatis.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {

    private static final long serialVersionUID = -2209821905931217493L;
    private Integer id;
    private String userId;
    private String username;
    private String payPassword;
    private BigDecimal totalMoney;
    private BigDecimal availableMoney;
    private BigDecimal frozenMoney;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
