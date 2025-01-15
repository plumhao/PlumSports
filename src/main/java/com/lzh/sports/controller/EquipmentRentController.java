package com.lzh.sports.controller;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.mapper.*;
import com.lzh.sports.service.*;
import com.lzh.sports.tools.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.SneakyThrows;

/**
 * 器材租借控制器
 */
@RestController()
@RequestMapping("/EquipmentRent")
public class EquipmentRentController {
    @Autowired()
    private  EquipmentRentService _EquipmentRentService;
    @Autowired()
    private EquipmentRentMapper _EquipmentRentMapper;
    /**
     * 器材租借分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<EquipmentRentDto> List(@RequestBody EquipmentRentPagedInput input)  {
        return _EquipmentRentService.List(input);
    }
     /**
     * 单个器材租借查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public EquipmentRentDto Get(@RequestBody EquipmentRentPagedInput input) {

        return _EquipmentRentService.Get(input);
    }
  
    /**
     * 器材租借创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public EquipmentRentDto CreateOrEdit(@RequestBody EquipmentRentDto input) throws Exception {
        return _EquipmentRentService.CreateOrEdit(input);
    }
    /**
     * 器材租借删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _EquipmentRentService.removeById(input.getId());
    }

    /**
     * 器材租借批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _EquipmentRentService.removeByIds(input.getIds());
    }
    /**
     * 租借
     * @param input
     */
    @RequestMapping(value = "/Rent", method = RequestMethod.POST)
    public void Rent(@RequestBody EquipmentRentDto input){
        _EquipmentRentService.Rent(input);
    }
    /**
     * 归还
     * @param input
     */
    @RequestMapping(value = "/ReturnEquipment", method = RequestMethod.POST)
    public void ReturnEquipment(@RequestBody EquipmentRentDto input){
        _EquipmentRentService.ReturnEquipment(input);
    }
}
