package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 余额变动记录类
 */
@Data
public class MoneyRecordDto extends BaseDto
{

    /**
     * 会员
     */     
     
    @JsonProperty("UserId")
    private Integer UserId;          
    /**
     * 变动金额
     */     
     
    @JsonProperty("Amount")
    private Double Amount;      
    /**
     * 变动内容
     */     
     
    @JsonProperty("Content")
    private String Content;
    /**
     * 余额
     */     
     
    @JsonProperty("Balance")
    private Double Balance;      
    /**
     * 变动类型
     */     
     
    @JsonProperty("TransactionType")
    private String TransactionType;
    /**
     * 变动时间
     */     
     
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("TransactionTime")
    private LocalDateTime TransactionTime;             

     @JsonProperty("UserDto") 
    private AppUserDto UserDto;

    /**
     * 关联ID
     */
    @JsonProperty("RelativeId")
    private Integer RelativeId;
   
}
