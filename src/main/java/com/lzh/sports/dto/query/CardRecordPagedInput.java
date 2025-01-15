package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * CardRecord查询模型
 */
@NoArgsConstructor
@Data
public class CardRecordPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
     /**
     * 用户
     */
  	 @JsonProperty("UserId")
    private Integer UserId;
    /**
     * 操作内容
     */
  	 @JsonProperty("Operation")
    private String Operation;
    /**
     * 会员卡号
     */
  	 @JsonProperty("CardNo")
    private String CardNo;
    /**
     * 操作时间时间范围
     */
    @JsonProperty("OperateTimeRange")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private List<LocalDateTime> OperateTimeRange;

}
