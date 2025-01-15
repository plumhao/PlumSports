package com.lzh.sports.dto.query;

import com.lzh.sports.tools.dto.PagedInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Equipment查询模型
 */
@NoArgsConstructor
@Data
public class EquipmentPagedInput extends PagedInput {
    
    /**
     * Id主键
     */
    @JsonProperty("Id")
    private Integer Id;
    /**
     * 设备编号
     */
  	 @JsonProperty("No")
    private String No;
    /**
     * 设备名称
     */
  	 @JsonProperty("Name")
    private String Name;
    /**
     * 类型
     */
  	 @JsonProperty("Type")
    private String Type;
    /**
     * 品牌
     */
  	 @JsonProperty("Brand")
    private String Brand;
    /**
     * 购买日期
     */
  	 @JsonProperty("PurchaseDate")
    private String PurchaseDate;
     /**
     * 使用状态
     */
  	 @JsonProperty("EquipmentStatus")
    private Integer EquipmentStatus;
    /**
     * 存放位置
     */
  	 @JsonProperty("Location")
    private String Location;
    /**
     * 操作指南
     */
  	 @JsonProperty("Instructions")
    private String Instructions;
    /**
     * 备注
     */
  	 @JsonProperty("Notes")
    private String Notes;

}
