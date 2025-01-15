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
 * 器材设备控制器
 */
@RestController()
@RequestMapping("/Equipment")
public class EquipmentController {
    @Autowired()
    private  EquipmentService _EquipmentService;
    @Autowired()
    private EquipmentMapper _EquipmentMapper;
    /**
     * 器材设备分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<EquipmentDto> List(@RequestBody EquipmentPagedInput input)  {
        return _EquipmentService.List(input);
    }
     /**
     * 单个器材设备查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public EquipmentDto Get(@RequestBody EquipmentPagedInput input) {

        return _EquipmentService.Get(input);
    }
  
    /**
     * 器材设备创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public EquipmentDto CreateOrEdit(@RequestBody EquipmentDto input) throws Exception {
        return _EquipmentService.CreateOrEdit(input);
    }
    /**
     * 器材设备删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _EquipmentService.removeById(input.getId());
    }

    /**
     * 器材设备批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _EquipmentService.removeByIds(input.getIds());
    }

 
}
