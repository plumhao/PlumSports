package com.lzh.sports.dto;
import com.lzh.sports.entity.Enums;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * 课程预约记录类
 */
@Data
public class CouseAppointRecordDto extends BaseDto
{

    /**
     * 关联安排
     */     
     
    @JsonProperty("CousePlanId")
    private Integer CousePlanId;          
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
    
    public String getCouseAppointStatusFormat() {
        return Enums.CouseAppointStatus.GetEnum(CouseAppointStatus).toString();
    }
    
    private String CouseAppointStatusFormat;
    
    /**
     * 签到时间
     */     
     
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("CheckTime")
    private LocalDateTime CheckTime;             

     @JsonProperty("CousePlanDto") 
    private CousePlanDto CousePlanDto;                        
   
     @JsonProperty("UserDto") 
    private AppUserDto UserDto;                        
   
     @JsonProperty("CoachUserDto") 
    private AppUserDto CoachUserDto;                        
   
     @JsonProperty("CouseDto") 
    private CouseDto CouseDto;                        
   
}
