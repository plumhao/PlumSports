package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;
import lombok.SneakyThrows;


/**
 * 器材租借功能的Service接口的定义清单
 */
public interface EquipmentRentService extends IService<EquipmentRent> {

    /**
     * 器材租借的分页查询方法接口定义
     */
    public PagedResult<EquipmentRentDto> List(EquipmentRentPagedInput input) ;
    /**
     * 器材租借的新增或者修改方法接口定义
     */
    public EquipmentRentDto CreateOrEdit(EquipmentRentDto input);

     /**
     * 获取器材租借信息
     */
    public EquipmentRentDto Get(EquipmentRentPagedInput input);


    @SneakyThrows
    void Rent(EquipmentRentDto input);

    @SneakyThrows
    void ReturnEquipment(EquipmentRentDto input);
}
