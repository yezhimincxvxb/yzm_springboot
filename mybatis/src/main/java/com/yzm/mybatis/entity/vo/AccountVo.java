package com.yzm.mybatis.entity.vo;

import com.yzm.mybatis.entity.base.BaseVo;
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
public class AccountVo extends BaseVo {

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
