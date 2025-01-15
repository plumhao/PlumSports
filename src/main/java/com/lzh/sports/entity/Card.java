package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会员卡表
 */
@Data
@TableName("`Card`")
public class Card extends BaseEntity {

    /**
     * 会员卡名
     */
    @JsonProperty("Name")
    @TableField("Name")
    private String Name;
    /**
     * 等级
     */
    @JsonProperty("Ranks")
    @TableField("Ranks")
    private Integer Ranks;
    /**
     * 达到充值余额
     */
    @JsonProperty("Money")
    @TableField("Money")
    private Double Money;
    /**
     * 折扣百分比
     */
    @JsonProperty("Discounts")
    @TableField("Discounts")
    private Double Discounts;

}
