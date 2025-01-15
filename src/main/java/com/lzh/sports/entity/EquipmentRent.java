package com.lzh.sports.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 器材租借表
 */
@Data
@TableName("`EquipmentRent`")
public class EquipmentRent extends BaseEntity {

    /**
     * 设备
     */     
   @JsonProperty("EquipmentId")
   @TableField("EquipmentId")
    private Integer EquipmentId;          
    /**
     * 会员
     */     
   @JsonProperty("UserId")
   @TableField("UserId")
    private Integer UserId;          
    /**
     * 租借时间
     */     
   @JsonProperty("RentalTime")
   @TableField("RentalTime")
    private LocalDateTime RentalTime;
    /**
     * 预计归还时间
     */     
   @JsonProperty("ReturnTime")
   @TableField("ReturnTime")
    private LocalDateTime ReturnTime;             
    /**
     * 实际归还时间
     */     
   @JsonProperty("ActualReturnTime")
   @TableField("ActualReturnTime")
    private LocalDateTime ActualReturnTime;             
    /**
     * 租界费用
     */     
   @JsonProperty("RentalFee")
   @TableField("RentalFee")
    private Double RentalFee;      
    /**
     * 损坏赔偿
     */     
   @JsonProperty("DamageMoney")
   @TableField("DamageMoney")
    private Double DamageMoney;      
    /**
     * 备注
     */     
  	 @JsonProperty("Notes")
    @TableField("Notes")
    private String Notes;

}
