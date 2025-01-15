package com.lzh.sports.dto;

import com.lzh.sports.entity.Enums;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppUserDto extends BaseDto
{
    /**
     * 账号
     */
    @JsonProperty("UserName")
    private String UserName;
    /**
     * 密码
     */
    @JsonProperty("Password")
    private String Password;
    /**
     * 原始密码
     */
    @JsonProperty("OrginPassword")
    private String OrginPassword;



    /**
     * 头像
     */
    @JsonProperty("ImageUrls")
    private String ImageUrls;
    /**
     * 名称
     */
    @JsonProperty("Name")
    private String Name;
    /**
     * 手机号码
     */
    @JsonProperty("PhoneNumber")
    private String PhoneNumber;
    /**
     * 出生年月
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("Birth")
    private LocalDateTime Birth;


    /**
     * 用户角色
     */
    @JsonProperty("RoleType")
    private Integer RoleType;


    /**
     * 登录时间段
     */
    @JsonProperty("LoginTimePeriod")
    private String LoginTimePeriod;

    /**
     * 用户角色描述
     * @return
     */
     @JsonProperty("RoleTypeFormat")
    public String RoleTypeFormat() {
        return  Enums.RoleType.GetEnum(RoleType).toString();
    }


    /**
     * 会员卡号
     */
    @JsonProperty("CardNo")
    private String CardNo;

    /**
     * 备注
     */
    @JsonProperty("Notes")
    private String Notes;

    /**
     * 办卡时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("OpenCardTime")
    private LocalDateTime OpenCardTime;

    /**
     * 关联会员卡
     */
    @JsonProperty("CardId")
    private Integer CardId;
    /**
     * 资质认证
     */
    @JsonProperty("Certifications")
    private String Certifications;
    /**
     * 专长领域
     */
    @JsonProperty("AreasOfExpertise")
    private String AreasOfExpertise;

    /**
     * 工作经验
     */
    @JsonProperty("WorkExperience")
    private String WorkExperience;
    /**
     * 余额
     */
    @JsonProperty("Money")
    private Double Money;

    /**
     * 累积余额
     */
    @JsonProperty("CumulativeMoney")
    private Double CumulativeMoney;
    /**
     * 会员信息
     */
    @JsonProperty("CardDto")
    private CardDto CardDto;

    /**
     * 距离下一级需要充值
     */
    @JsonProperty("NextGradeRechageMoney")
    private Double NextGradeRechageMoney;




}
