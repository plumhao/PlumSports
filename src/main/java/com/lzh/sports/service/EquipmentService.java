package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;


/**
 * 器材设备功能的Service接口的定义清单
 */
public interface EquipmentService extends IService<Equipment> {

    /**
     * 器材设备的分页查询方法接口定义
     */
    public PagedResult<EquipmentDto> List(EquipmentPagedInput input) ;
    /**
     * 器材设备的新增或者修改方法接口定义
     */
    public EquipmentDto CreateOrEdit(EquipmentDto input);

     /**
     * 获取器材设备信息
     */
    public EquipmentDto Get(EquipmentPagedInput input);


}
