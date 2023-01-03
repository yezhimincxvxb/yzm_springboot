package com.yzm.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号资金表
 * </p>
 *
 * @author Yzm
 * @since 2023/01/03
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("account")
@ApiModel(value = "Account对象", description = "账号资金表")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("user表的id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("账户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("支付密码")
    @TableField("pay_password")
    private String payPassword;

    @ApiModelProperty("总资金")
    @TableField("total_money")
    private Double totalMoney;

    @ApiModelProperty("可用资金")
    @TableField("available_money")
    private Double availableMoney;

    @ApiModelProperty("冻结资金")
    @TableField("frozen_money")
    private Double frozenMoney;

    @ApiModelProperty("版本号")
    @TableField("version")
    private Integer version;

    @ApiModelProperty("创建时间")
    @TableField("create_date")
    private LocalDateTime createDate;

    @ApiModelProperty("更新时间")
    @TableField("update_date")
    private LocalDateTime updateDate;


}
