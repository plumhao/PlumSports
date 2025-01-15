package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 门户封面类
 */
@Data
public class BannerDto extends BaseDto
{

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
    /**
     * 封面
     */     
     
    @JsonProperty("ImageUrls")
    private String ImageUrls;

    /**
     * 跳转路径
     */
    @JsonProperty("LinkUrl")
    private String LinkUrl;

}
