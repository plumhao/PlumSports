package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 话题类型表
 */
@Data
@TableName("TopicType")
public class TopicType extends BaseEntity {

    /**
     * 名称
     */
    @JsonProperty("Name")
    private String Name;
    /**
     * 编码
     */
    @JsonProperty("Code")
    private String Code;


    /**
     * 封面
     */
    @JsonProperty("Cover")
    private String Cover;
    /**
     * 描述
     */
    @JsonProperty("Content")
    private String Content;
}
