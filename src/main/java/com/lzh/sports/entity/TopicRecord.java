package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 话题浏览记录表
 */
@Data
@TableName("`TopicRecord`")
public class TopicRecord extends BaseEntity {

    /**
     * 话题类型
     */
    @JsonProperty("TopicTypeId")
    @TableField("TopicTypeId")
    private Integer TopicTypeId;
    /**
     * 话题
     */
    @JsonProperty("TopicId")
    @TableField("TopicId")
    private Integer TopicId;
    /**
     * 浏览人
     */
    @JsonProperty("UserId")
    @TableField("UserId")
    private Integer UserId;

}
