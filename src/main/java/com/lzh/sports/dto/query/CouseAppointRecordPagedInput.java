package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * CouseAppointRecord查询模型
 */
@NoArgsConstructor
@Data
public class CouseAppointRecordPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
     /**
     * 关联安排
     */
  	 @JsonProperty("CousePlanId")
    private Integer CousePlanId;
    /**
     * 开始时间时间范围
     */
    @JsonProperty("BeginTimeRange")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> BeginTimeRange;
    /**
     * 结束时间时间范围
     */
    @JsonProperty("EndTimeRange")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> EndTimeRange;
     /**
     * 报名人
     */
  	 @JsonProperty("UserId")
    private Integer UserId;
     /**
     * 课程
     */
  	 @JsonProperty("CouseId")
    private Integer CouseId;
     /**
     * 教练
     */
  	 @JsonProperty("CoachUserId")
    private Integer CoachUserId;
     /**
     * 报名状态
     */
  	 @JsonProperty("CouseAppointStatus")
    private Integer CouseAppointStatus;
    /**
     * 签到时间时间范围
     */
    @JsonProperty("CheckTimeRange")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> CheckTimeRange;

}
