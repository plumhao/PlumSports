package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comment查询模型
 */
@NoArgsConstructor
@Data
public class CommentPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
     /**
     * 所属话题
     */
  	 @JsonProperty("TopicId")
    private Integer TopicId;
    /**
     * 名称
     */
  	 @JsonProperty("NickName")
    private String NickName;

}
