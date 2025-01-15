package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 系统通知类
 */
@Data
public class SysNoticeDto extends BaseDto
{

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
    /**
     * 通知内容
     */     
     
    @JsonProperty("Content")
    private String Content;
    /**
     * 附件
     */     
     
    @JsonProperty("File")
    private String File;

}
