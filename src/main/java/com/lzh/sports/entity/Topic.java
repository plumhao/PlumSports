package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 话题表
 */
@Data
@TableName("Topic")
public class Topic extends BaseEntity {

    /**
     * 标题
     */
    @JsonProperty("Title")
    private String Title;
    /**
     * 封面
     */
    @JsonProperty("Cover")
    private String Cover;
    /**
     * 主图
     */
    @JsonProperty("ImageUrls")
    private String ImageUrls;
    /**
     * 浏览数
     */
    @JsonProperty("ViewCount")
    private Integer ViewCount;
    /**
     * 内容
     */
    @JsonProperty("Content")
    private String Content;
    /**
     * 话题类型
     */
    @JsonProperty("TopicTypeId")
    private Integer TopicTypeId;

    /**
     * 审核状态
     */
    @JsonProperty("AuditStatus")
    private Integer AuditStatus;
    /**
     * 是否推荐
     */
    @JsonProperty("IsRecommand")
    private Boolean IsRecommand;
}
