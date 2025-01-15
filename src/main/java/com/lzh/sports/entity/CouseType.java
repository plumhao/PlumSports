package com.lzh.sports.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 课程类型表
 */
@Data
@TableName("`CouseType`")
public class CouseType extends BaseEntity {

    /**
     * 名称
     */     
  	 @JsonProperty("Name")
    @TableField("Name")
    private String Name;
    /**
     * 封面
     */     
  	 @JsonProperty("Cover")
    @TableField("Cover")
    private String Cover;
    /**
     * 显示顺序
     */     
   @JsonProperty("Sort")
   @TableField("Sort")
    private Integer Sort;          

}
