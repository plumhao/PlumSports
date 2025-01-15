package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 话题收藏类
 */
@Data
public class TopicCollectDto extends BaseDto
{

    /**
     * 话题
     */     
     
    @JsonProperty("TopicId")
    private Integer TopicId;          
    /**
     * 收藏人
     */     
     
    @JsonProperty("UserId")
    private Integer UserId;          

     @JsonProperty("TopicDto") 
    private TopicDto TopicDto;                        
   
     @JsonProperty("UserDto") 
    private AppUserDto UserDto;                        
   
}
