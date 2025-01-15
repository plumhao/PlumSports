package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 话题收藏表
 */
@Data
@TableName("`TopicCollect`")
public class TopicCollect extends BaseEntity {

    /**
     * 话题
     */
    @JsonProperty("TopicId")
    @TableField("TopicId")
    private Integer TopicId;
    /**
     * 收藏人
     */
    @JsonProperty("UserId")
    @TableField("UserId")
    private Integer UserId;

}
