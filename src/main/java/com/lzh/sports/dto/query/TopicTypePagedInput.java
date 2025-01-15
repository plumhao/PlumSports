package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TopicType查询模型
 */
@NoArgsConstructor
@Data
public class TopicTypePagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
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

}
