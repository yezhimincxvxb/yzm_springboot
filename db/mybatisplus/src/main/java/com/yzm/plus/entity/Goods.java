package com.yzm.plus.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.yzm.plus.config.GoodsEnum;
import com.yzm.plus.config.MyDateTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author Yzm
 * @since 2023/01/03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Goods对象", description = "商品表")
@TableName(value = "goods", autoResultMap = true)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("商品名")
    @TableField("name")
    private String name;

    @ApiModelProperty("商品价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("库存")
    @TableField("left_num")
    private Integer leftNum;

    @ApiModelProperty("状态[0-下架,1-上架,2-热销]")
    @TableField("status")
    private GoodsEnum status;

    @ApiModelProperty("版本")
    @Version
    private Integer version;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateDate;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @ApiModelProperty("删除状态[0-未删1-已删]")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("创建时间戳")
    @TableField(value = "date_unix", typeHandler = MyDateTypeHandler.class)
    private LocalDateTime dateUnix;

}
