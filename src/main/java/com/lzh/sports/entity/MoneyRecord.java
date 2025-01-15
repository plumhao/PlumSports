package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 余额变动记录表
 */
@Data
@TableName("`MoneyRecord`")
public class MoneyRecord extends BaseEntity {

    /**
     * 会员
     */
    @JsonProperty("UserId")
    @TableField("UserId")
    private Integer UserId;
    /**
     * 变动金额
     */
    @JsonProperty("Amount")
    @TableField("Amount")
    private Double Amount;
    /**
     * 变动内容
     */
    @JsonProperty("Content")
    @TableField("Content")
    private String Content;
    /**
     * 余额
     */
    @JsonProperty("Balance")
    @TableField("Balance")
    private Double Balance;
    /**
     * 变动类型 1:充值 2:消费 3:退款
     */
    @JsonProperty("TransactionType")
    @TableField("TransactionType")
    private String TransactionType;

    /**
     * 关联ID
     */
    @JsonProperty("RelativeId")
    @TableField("RelativeId")
    private Integer RelativeId;
    /**
     * 变动时间
     */
    @JsonProperty("TransactionTime")
    @TableField("TransactionTime")
    private LocalDateTime TransactionTime;

}
