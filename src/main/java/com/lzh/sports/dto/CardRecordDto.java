package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 会员变动记录类
 */
@Data
public class CardRecordDto extends BaseDto
{

    /**
     * 用户
     */     
     
    @JsonProperty("UserId")
    private Integer UserId;          
    /**
     * 操作内容
     */     
     
    @JsonProperty("Operation")
    private String Operation;
    /**
     * 会员卡号
     */     
     
    @JsonProperty("CardNo")
    private String CardNo;
    /**
     * 操作时间
     */     
     
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("OperateTime")
    private LocalDateTime OperateTime;             

     @JsonProperty("UserDto") 
    private AppUserDto UserDto;                        
   
}
