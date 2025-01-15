package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CommentLove查询模型
 */
@NoArgsConstructor
@Data
public class CommentLovePagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
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

}
