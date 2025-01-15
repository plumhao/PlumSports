package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Topic查询模型
 */
@NoArgsConstructor
@Data
public class TopicPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
    /**
     * 标题
     */
  	 @JsonProperty("Title")
    private String Title;
  	 /**
     * 浏览数起始范围条件
     */
  	 @JsonProperty("ViewCountStartRange")
    private Integer ViewCountStartRange;
 	
    /**
     * 浏览数结束范围条件
     */
  	 @JsonProperty("ViewCountEndRange")
    private Integer ViewCountEndRange;
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


    /**
     * 最新的话题
     */
    @JsonProperty("IsNew")
    private Boolean IsNew;

    /**
     * 是否最热
     */
    @JsonProperty("IsHot")
    private Boolean IsHot;


}
