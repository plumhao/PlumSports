package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * MoneyRecord查询模型
 */
@NoArgsConstructor
@Data
public class MoneyRecordPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
     /**
     * 会员
     */
  	 @JsonProperty("UserId")
    private Integer UserId;
     /**
     * 变动金额起始范围条件
     */
  	 @JsonProperty("AmountStartRange")
    private Double AmountStartRange;
 	
    /**
     * 变动金额结束范围条件
     */
  	 @JsonProperty("AmountEndRange")
    private Double AmountEndRange;
     /**
     * 余额起始范围条件
     */
  	 @JsonProperty("BalanceStartRange")
    private Double BalanceStartRange;
 	
    /**
     * 余额结束范围条件
     */
  	 @JsonProperty("BalanceEndRange")
    private Double BalanceEndRange;
    /**
     * 变动类型
     */
  	 @JsonProperty("TransactionType")
    private String TransactionType;
    /**
     * 变动时间时间范围
     */
    @JsonProperty("TransactionTimeRange")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> TransactionTimeRange;

}
