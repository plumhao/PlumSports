package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会员卡类
 */
@Data
public class CardDto extends BaseDto
{

    /**
     * 会员卡名
     */     
     
    @JsonProperty("Name")
    private String Name;
    /**
     * 等级
     */     
     
    @JsonProperty("Ranks")
    private Integer Ranks;          
    /**
     * 达到充值余额
     */     
     
    @JsonProperty("Money")
    private Double Money;      
    /**
     * 折扣百分比
     */     
     
    @JsonProperty("Discounts")
    private Double Discounts;      

}
