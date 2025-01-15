package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CouseType查询模型
 */
@NoArgsConstructor
@Data
public class CouseTypePagedInput extends PagedInput {
    
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
     * 显示顺序起始范围条件
     */
  	 @JsonProperty("SortStartRange")
    private Integer SortStartRange;
 	
    /**
     * 显示顺序结束范围条件
     */
  	 @JsonProperty("SortEndRange")
    private Integer SortEndRange;

}
