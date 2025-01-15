package com.lzh.sports.dto;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
/**
 * 课程安排类
 */
@Data
public class CousePlanDto extends BaseDto
{

    /**
     * 课程
     */     
     
    @JsonProperty("CouseId")
    private Integer CouseId;          
    /**
     * 开始时间
     */     
     
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("BeginTime")
    private LocalDateTime BeginTime;             
    /**
     * 结束时间
     */     
     
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("EndTime")
    private LocalDateTime EndTime;             
    /**
     * 最大报名人数
     */     
     
    @JsonProperty("MaxParticipants")
    private Integer MaxParticipants;          
    /**
     * 当前报名人数
     */     
     
    @JsonProperty("CurrentEnrollments")
    private Integer CurrentEnrollments;          
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

    /**
     * 距离目标会员卡需要充值
     */

    @JsonProperty("RemindRechargeMoney")
    private Double RemindRechargeMoney;


    @JsonProperty("CardDto")
    private CardDto CardDto;                        
   
     @JsonProperty("CouseDto") 
    private CouseDto CouseDto;                        
   
     @JsonProperty("CoachUserDto") 
    private AppUserDto CoachUserDto;
    /**
     * 选择的年月日范围
     */
    @JsonProperty("DateRange")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> DateRange;

    /**
     * 选择的时间范围
     */
    @JsonProperty("TimeRange")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> TimeRange;

    /**
     * 所属日期
     */

    @JsonProperty("BelongDate")
    private LocalDate BelongDate;

    @JsonProperty("BeginHMS")
    private String BeginHMS;

    @JsonProperty("EndHMS")
    private String EndHMS;


    @JsonProperty("IsLoseEfficacy")
    private Boolean IsLoseEfficacy;

}
