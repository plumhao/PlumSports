package com.lzh.sports.dto;
import com.lzh.sports.entity.Enums;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 器材设备类
 */
@Data
public class EquipmentDto extends BaseDto
{

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
     * 使用状态
     */     
     
    @JsonProperty("EquipmentStatus")
    private Integer EquipmentStatus;    
    
    public String getEquipmentStatusFormat() {
        return Enums.EquipmentStatus.GetEnum(EquipmentStatus).toString();
    }
    
    private String EquipmentStatusFormat;
    
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
