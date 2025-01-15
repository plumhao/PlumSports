package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * CousePlan查询模型
 */
@NoArgsConstructor
@Data
public class CousePlanPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
     /**
     * 课程
     */
  	 @JsonProperty("CouseId")
    private Integer CouseId;
    /**
     * 开始时间时间范围
     */
    @JsonProperty("BeginTimeRange")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> BeginTimeRange;

    /**
     * 日期
     */
    @JsonProperty("BelongDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate BelongDate;
    /**
     * 结束时间时间范围
     */
    @JsonProperty("EndTimeRange")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> EndTimeRange;
  	 /**
     * 最大报名人数起始范围条件
     */
  	 @JsonProperty("MaxParticipantsStartRange")
    private Integer MaxParticipantsStartRange;
 	
    /**
     * 最大报名人数结束范围条件
     */
  	 @JsonProperty("MaxParticipantsEndRange")
    private Integer MaxParticipantsEndRange;
  	 /**
     * 当前报名人数起始范围条件
     */
  	 @JsonProperty("CurrentEnrollmentsStartRange")
    private Integer CurrentEnrollmentsStartRange;
 	
    /**
     * 当前报名人数结束范围条件
     */
  	 @JsonProperty("CurrentEnrollmentsEndRange")
    private Integer CurrentEnrollmentsEndRange;
    /**
     * 上课地点
     */
  	 @JsonProperty("Location")
    private String Location;
    /**
     * 备注
     */
  	 @JsonProperty("Notes")
    private String Notes;
     /**
     * 教练
     */
  	 @JsonProperty("CoachUserId")
    private Integer CoachUserId;
     /**
     * 要求会员卡
     */
  	 @JsonProperty("CardId")
    private Integer CardId;

}
