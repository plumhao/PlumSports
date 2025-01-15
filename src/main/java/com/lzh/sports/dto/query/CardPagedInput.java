package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Card查询模型
 */
@NoArgsConstructor
@Data
public class CardPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
    /**
     * 会员卡名
     */
  	 @JsonProperty("Name")
    private String Name;
  	 /**
     * 等级起始范围条件
     */
  	 @JsonProperty("RanksStartRange")
    private Integer RanksStartRange;
 	
    /**
     * 等级结束范围条件
     */
  	 @JsonProperty("RanksEndRange")
    private Integer RanksEndRange;
     /**
     * 达到充值余额起始范围条件
     */
  	 @JsonProperty("MoneyStartRange")
    private Double MoneyStartRange;
 	
    /**
     * 达到充值余额结束范围条件
     */
  	 @JsonProperty("MoneyEndRange")
    private Double MoneyEndRange;
     /**
     * 折扣百分比起始范围条件
     */
  	 @JsonProperty("DiscountsStartRange")
    private Double DiscountsStartRange;
 	
    /**
     * 折扣百分比结束范围条件
     */
  	 @JsonProperty("DiscountsEndRange")
    private Double DiscountsEndRange;

}
