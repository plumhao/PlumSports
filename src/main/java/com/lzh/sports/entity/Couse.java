package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 课程表
 */
@Data
@TableName("`Couse`")
public class Couse extends BaseEntity {

    /**
     * 课程名称
     */
    @JsonProperty("Name")
    @TableField("Name")
    private String Name;
    /**
     * 类型
     */
    @JsonProperty("CouseTypeId")
    @TableField("CouseTypeId")
    private Integer CouseTypeId;
    /**
     * 课程描述
     */
    @JsonProperty("Description")
    @TableField("Description")
    private String Description;
    /**
     * 备注
     */
    @JsonProperty("Notes")
    @TableField("Notes")
    private String Notes;
    /**
     * 视频
     */
    @JsonProperty("VideoUrls")
    @TableField("VideoUrls")
    private String VideoUrls;
    /**
     * 封面
     */
    @JsonProperty("Cover")
    @TableField("Cover")
    private String Cover;
    /**
     * 是否上架
     */
    @JsonProperty("IsPutaway")
    @TableField("IsPutaway")
    private Boolean IsPutaway;
    /**
     * 费用
     */
    @JsonProperty("Fee")
    @TableField("Fee")
    private Double Fee;

}
