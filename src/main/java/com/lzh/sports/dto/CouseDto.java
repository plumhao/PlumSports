package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 课程类
 */
@Data
public class CouseDto extends BaseDto
{

    /**
     * 课程名称
     */     
     
    @JsonProperty("Name")
    private String Name;
    /**
     * 类型
     */     
     
    @JsonProperty("CouseTypeId")
    private Integer CouseTypeId;          
    /**
     * 课程描述
     */     
     
    @JsonProperty("Description")
    private String Description;
    /**
     * 备注
     */     
     
    @JsonProperty("Notes")
    private String Notes;
    /**
     * 视频
     */     
     
    @JsonProperty("VideoUrls")
    private String VideoUrls;
    /**
     * 封面
     */     
     
    @JsonProperty("Cover")
    private String Cover;
    /**
     * 是否上架
     */
     
    @JsonProperty("IsPutaway")
    private Boolean IsPutaway;          
    /**
     * 费用
     */     
     
    @JsonProperty("Fee")
    private Double Fee;

    /**
     * 课程类型
     */
    @JsonProperty("CouseTypeDto")
    private CouseTypeDto CouseTypeDto;

}
