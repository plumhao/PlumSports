package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * EquipmentRent查询模型
 */
@NoArgsConstructor
@Data
public class EquipmentRentPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
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
     * 租借时间起始范围条件
     */
  	 @JsonProperty("RentalTimeStartRange")
    private Integer RentalTimeStartRange;
 	
    /**
     * 租借时间结束范围条件
     */
  	 @JsonProperty("RentalTimeEndRange")
    private Integer RentalTimeEndRange;
    /**
     * 预计归还时间时间范围
     */
    @JsonProperty("ReturnTimeRange")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> ReturnTimeRange;
    /**
     * 实际归还时间时间范围
     */
    @JsonProperty("ActualReturnTimeRange")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> ActualReturnTimeRange;
     /**
     * 租界费用起始范围条件
     */
  	 @JsonProperty("RentalFeeStartRange")
    private Double RentalFeeStartRange;
 	
    /**
     * 租界费用结束范围条件
     */
  	 @JsonProperty("RentalFeeEndRange")
    private Double RentalFeeEndRange;
     /**
     * 损坏赔偿起始范围条件
     */
  	 @JsonProperty("DamageMoneyStartRange")
    private Double DamageMoneyStartRange;
 	
    /**
     * 损坏赔偿结束范围条件
     */
  	 @JsonProperty("DamageMoneyEndRange")
    private Double DamageMoneyEndRange;
    /**
     * 备注
     */
  	 @JsonProperty("Notes")
    private String Notes;

}
