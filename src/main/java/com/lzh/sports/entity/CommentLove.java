package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 评论点赞记录表
 */
@Data
@TableName("`CommentLove`")
public class CommentLove extends BaseEntity {

    /**
     * 话题
     */
    @JsonProperty("TopicId")
    @TableField("TopicId")
    private Integer TopicId;
    /**
     * 点赞评论
     */
    @JsonProperty("CommentId")
    @TableField("CommentId")
    private Integer CommentId;
    /**
     * 点赞人
     */
    @JsonProperty("UserId")
    @TableField("UserId")
    private Integer UserId;

}
