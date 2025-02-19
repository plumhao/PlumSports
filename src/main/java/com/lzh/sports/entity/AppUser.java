package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@TableName("AppUser")
public class AppUser extends BaseEntity {

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


}
