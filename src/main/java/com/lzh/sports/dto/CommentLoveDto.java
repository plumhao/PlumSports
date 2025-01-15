package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 评论点赞记录类
 */
@Data
public class CommentLoveDto extends BaseDto
{

    /**
     * 话题
     */     
     
    @JsonProperty("TopicId")
    private Integer TopicId;          
    /**
     * 点赞评论
     */     
     
    @JsonProperty("CommentId")
    private Integer CommentId;          
    /**
     * 点赞人
     */     
     
    @JsonProperty("UserId")
    private Integer UserId;          

     @JsonProperty("TopicDto") 
    private TopicDto TopicDto;                        
   
     @JsonProperty("UserDto") 
    private AppUserDto UserDto;                        
   
     @JsonProperty("CommentDto") 
    private CommentDto CommentDto;                        
   
}
