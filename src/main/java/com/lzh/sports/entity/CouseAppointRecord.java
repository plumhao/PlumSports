package com.lzh.sports.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 课程预约记录表
 */
@Data
@TableName("`CouseAppointRecord`")
public class CouseAppointRecord extends BaseEntity {

    /**
     * 关联安排
     */     
   @JsonProperty("CousePlanId")
   @TableField("CousePlanId")
    private Integer CousePlanId;          
    /**
     * 开始时间
     */     
   @JsonProperty("BeginTime")
   @TableField("BeginTime")
    private LocalDateTime BeginTime;             
    /**
     * 结束时间
     */     
   @JsonProperty("EndTime")
   @TableField("EndTime")
    private LocalDateTime EndTime;             
    /**
     * 报名人
     */     
   @JsonProperty("UserId")
   @TableField("UserId")
    private Integer UserId;          
    /**
     * 课程
     */     
   @JsonProperty("CouseId")
   @TableField("CouseId")
    private Integer CouseId;          
    /**
     * 教练
     */     
   @JsonProperty("CoachUserId")
   @TableField("CoachUserId")
    private Integer CoachUserId;          
    /**
     * 报名状态
     */     
   @JsonProperty("CouseAppointStatus")
   @TableField("CouseAppointStatus")
    private Integer CouseAppointStatus;          
    /**
     * 签到时间
     */     
   @JsonProperty("CheckTime")
   @TableField("CheckTime")
    private LocalDateTime CheckTime;             

}
