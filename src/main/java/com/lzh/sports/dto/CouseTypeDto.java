package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
/**
 * 课程类型类
 */
@Data
public class CouseTypeDto extends BaseDto
{

    /**
     * 名称
     */     
     
    @JsonProperty("Name")
    private String Name;
    /**
     * 封面
     */     
     
    @JsonProperty("Cover")
    private String Cover;
    /**
     * 显示顺序
     */     
     
    @JsonProperty("Sort")
    private Integer Sort;          

   
  	 /**
    * 课程集合
    */ 
     @JsonProperty("CouseDtos") 
    private List<CouseDto> CouseDtos;                        
}
