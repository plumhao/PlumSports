package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 门户封面表
 */
@Data
@TableName("Banner")
public class Banner extends BaseEntity {

    /**
     * 标题
     */
    @JsonProperty("Title")
    private String Title;
    /**
     * 备注
     */
    @JsonProperty("Remark")
    private String Remark;
    /**
     * 封面
     */
    @JsonProperty("ImageUrls")
    private String ImageUrls;

    /**
     * 跳转路径
     */
    @JsonProperty("LinkUrl")
    private String LinkUrl;



}
