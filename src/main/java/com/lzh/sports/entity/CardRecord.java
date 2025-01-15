package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 会员变动记录表
 */
@Data
@TableName("`CardRecord`")
public class CardRecord extends BaseEntity {

    /**
     * 用户
     */
    @JsonProperty("UserId")
    @TableField("UserId")
    private Integer UserId;
    /**
     * 操作内容
     */
    @JsonProperty("Operation")
    @TableField("Operation")
    private String Operation;
    /**
     * 会员卡号
     */
    @JsonProperty("CardNo")
    @TableField("CardNo")
    private String CardNo;
    /**
     * 操作时间
     */
    @JsonProperty("OperateTime")
    @TableField("OperateTime")
    private LocalDateTime OperateTime;

}
