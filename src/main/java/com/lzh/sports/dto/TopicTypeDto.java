package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
/**
 * 话题类型类
 */
@Data
public class TopicTypeDto extends BaseDto
{

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


    /**
     * 话题集合
     */
    @JsonProperty("TopicDtos")
    private List<TopicDto> TopicDtos;

    /**
     * 描述
     */
    @JsonProperty("Content")
    private String Content;
    /**
     * 封面
     */
    @JsonProperty("Cover")
    private String Cover;
}
