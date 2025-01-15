package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 话题浏览记录类
 */
@Data
public class TopicRecordDto extends BaseDto
{

    /**
     * 话题类型
     */     
     
    @JsonProperty("TopicTypeId")
    private Integer TopicTypeId;          
    /**
     * 话题
     */     
     
    @JsonProperty("TopicId")
    private Integer TopicId;          
    /**
     * 浏览人
     */     
     
    @JsonProperty("UserId")
    private Integer UserId;          

     @JsonProperty("UserDto") 
    private AppUserDto UserDto;                        
   
     @JsonProperty("TopicTypeDto") 
    private TopicTypeDto TopicTypeDto;                        
   
     @JsonProperty("TopicDto") 
    private TopicDto TopicDto;                        
   
}
