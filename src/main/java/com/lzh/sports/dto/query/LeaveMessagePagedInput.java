package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LeaveMessage查询模型
 */
@NoArgsConstructor
@Data
public class LeaveMessagePagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
     /**
     * 用户
     */
  	 @JsonProperty("UserId")
    private Integer UserId;
     /**
     * 处理状态
     */
  	 @JsonProperty("ProcessingStatus")
    private Integer ProcessingStatus;
    /**
     * 备注
     */
  	 @JsonProperty("Notes")
    private String Notes;
     /**
     * 负责人
     */
  	 @JsonProperty("PersonUserId")
    private Integer PersonUserId;
    /**
     * 留言类型
     */
  	 @JsonProperty("Type")
    private String Type;

}
