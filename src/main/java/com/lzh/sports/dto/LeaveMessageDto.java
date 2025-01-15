package com.lzh.sports.dto;
import com.lzh.sports.entity.Enums;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 留言类
 */
@Data
public class LeaveMessageDto extends BaseDto
{

    /**
     * 用户
     */     
     
    @JsonProperty("UserId")
    private Integer UserId;          
    /**
     * 留言内容
     */     
     
    @JsonProperty("Content")
    private String Content;
    /**
     * 回复内容
     */     
     
    @JsonProperty("ReplyContent")
    private String ReplyContent;
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

     @JsonProperty("PersonUserDto") 
    private AppUserDto PersonUserDto;                        
   
     @JsonProperty("UserDto") 
    private AppUserDto UserDto;

    /**
     * 审核状态描述
     * @return
     */
    @JsonProperty("ProcessingStatusFormat")
    public String ProcessingStatusFormat() {
        return  Enums.ProcessingStatus.GetEnum(ProcessingStatus).toString();
    }


}
