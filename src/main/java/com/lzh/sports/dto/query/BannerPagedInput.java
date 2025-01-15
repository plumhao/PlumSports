package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Banner查询模型
 */
@NoArgsConstructor
@Data
public class BannerPagedInput extends PagedInput {
    
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
     * 备注
     */
  	 @JsonProperty("Remark")
    private String Remark;
  

}
