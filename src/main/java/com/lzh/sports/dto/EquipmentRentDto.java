package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 器材租借类
 */
@Data
public class EquipmentRentDto extends BaseDto
{

    /**
     * 设备
     */     
     
    @JsonProperty("EquipmentId")
    private Integer EquipmentId;          
    /**
     * 会员
     */     
     
    @JsonProperty("UserId")
    private Integer UserId;          
    /**
     * 租借时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("RentalTime")
    private LocalDateTime RentalTime;
    /**
     * 预计归还时间
     */     
     
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("ReturnTime")
    private LocalDateTime ReturnTime;             
    /**
     * 实际归还时间
     */     
     
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("ActualReturnTime")
    private LocalDateTime ActualReturnTime;             
    /**
     * 租界费用
     */     
     
    @JsonProperty("RentalFee")
    private Double RentalFee;      
    /**
     * 损坏赔偿
     */     
     
    @JsonProperty("DamageMoney")
    private Double DamageMoney;      
    /**
     * 备注
     */     
     
    @JsonProperty("Notes")
    private String Notes;

     @JsonProperty("EquipmentDto") 
    private EquipmentDto EquipmentDto;                        
   
     @JsonProperty("UserDto") 
    private AppUserDto UserDto;                        
   
}
