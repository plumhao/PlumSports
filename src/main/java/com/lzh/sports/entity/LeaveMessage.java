package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 留言表
 */
@Data
@TableName("`LeaveMessage`")
public class LeaveMessage extends BaseEntity {

    /**
     * 用户
     */
    @JsonProperty("UserId")
    @TableField("UserId")
    private Integer UserId;
    /**
     * 留言内容
     */
    @JsonProperty("Content")
    @TableField("Content")
    private String Content;
    /**
     * 回复内容
     */
    @JsonProperty("ReplyContent")
    @TableField("ReplyContent")
    private String ReplyContent;
    /**
     * 处理状态
     */
    @JsonProperty("ProcessingStatus")
    @TableField("ProcessingStatus")
    private Integer ProcessingStatus;
    /**
     * 备注
     */
    @JsonProperty("Notes")
    @TableField("Notes")
    private String Notes;
    /**
     * 负责人
     */
    @JsonProperty("PersonUserId")
    @TableField("PersonUserId")
    private Integer PersonUserId;
    /**
     * 留言类型
     */
    @JsonProperty("Type")
    @TableField("Type")
    private String Type;

}
