package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SysNotice查询模型
 */
@NoArgsConstructor
@Data
public class SysNoticePagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
    /**
     * 通知标题
     */
  	 @JsonProperty("Name")
    private String Name;
    /**
     * 作者
     */
  	 @JsonProperty("Author")
    private String Author;

}
