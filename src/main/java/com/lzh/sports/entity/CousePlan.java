package com.lzh.sports.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 课程安排表
 */
@Data
@TableName("`CousePlan`")
public class CousePlan extends BaseEntity {

    /**
     * 课程
     */     
   @JsonProperty("CouseId")
   @TableField("CouseId")
    private Integer CouseId;          
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
     * 最大报名人数
     */     
   @JsonProperty("MaxParticipants")
   @TableField("MaxParticipants")
    private Integer MaxParticipants;          
    /**
     * 当前报名人数
     */     
   @JsonProperty("CurrentEnrollments")
   @TableField("CurrentEnrollments")
    private Integer CurrentEnrollments;          
    /**
     * 上课地点
     */     
  	 @JsonProperty("Location")
    @TableField("Location")
    private String Location;
    /**
     * 备注
     */     
  	 @JsonProperty("Notes")
    @TableField("Notes")
    private String Notes;
    /**
     * 教练
     */     
   @JsonProperty("CoachUserId")
   @TableField("CoachUserId")
    private Integer CoachUserId;          
    /**
     * 要求会员卡
     */     
   @JsonProperty("CardId")
   @TableField("CardId")
    private Integer CardId;          

}
