package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TopicCollect查询模型
 */
@NoArgsConstructor
@Data
public class TopicCollectPagedInput extends PagedInput {
    
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
     * 收藏人
     */
  	 @JsonProperty("UserId")
    private Integer UserId;

}
