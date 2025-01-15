package com.lzh.sports.dto.query;
import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AppUserPagedInput  extends PagedInput {
    private Integer Id;

    /**
     * 姓名
     */
    @JsonProperty("Name")
    private String Name;


    /**
     * 手机号码
     */
    @JsonProperty("PhoneNumber")
    private String PhoneNumber;

    /**
     * 出生日期搜索
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("BirthRange")
    private List<LocalDateTime> BirthRange;

    /**
     * 用户角色
     */
    @JsonProperty("RoleType")
    private Integer RoleType;


    /**
     * 会员卡号
     */
    @JsonProperty("CardNo")
    private String CardNo;


}
