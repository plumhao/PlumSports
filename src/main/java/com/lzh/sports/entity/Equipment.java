package com.lzh.sports.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 器材设备表
 */
@Data
@TableName("`Equipment`")
public class Equipment extends BaseEntity {

    /**
     * 设备编号
     */     
  	 @JsonProperty("No")
    @TableField("No")
    private String No;
    /**
     * 设备名称
     */     
  	 @JsonProperty("Name")
    @TableField("Name")
    private String Name;
    /**
     * 类型
     */     
  	 @JsonProperty("Type")
    @TableField("Type")
    private String Type;
    /**
     * 品牌
     */     
  	 @JsonProperty("Brand")
    @TableField("Brand")
    private String Brand;

    /**
     * 使用状态
     */     
   @JsonProperty("EquipmentStatus")
   @TableField("EquipmentStatus")
    private Integer EquipmentStatus;          
    /**
     * 存放位置
     */     
  	 @JsonProperty("Location")
    @TableField("Location")
    private String Location;
    /**
     * 操作指南
     */     
  	 @JsonProperty("Instructions")
    @TableField("Instructions")
    private String Instructions;
    /**
     * 备注
     */     
  	 @JsonProperty("Notes")
    @TableField("Notes")
    private String Notes;

}
