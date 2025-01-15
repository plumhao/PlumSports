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
 * 课程类型控制器
 */
@RestController()
@RequestMapping("/CouseType")
public class CouseTypeController {
    @Autowired()
    private  CouseTypeService _CouseTypeService;
    @Autowired()
    private CouseTypeMapper _CouseTypeMapper;
    /**
     * 课程类型分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<CouseTypeDto> List(@RequestBody CouseTypePagedInput input)  {
        return _CouseTypeService.List(input);
    }
     /**
     * 单个课程类型查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public CouseTypeDto Get(@RequestBody CouseTypePagedInput input) {

        return _CouseTypeService.Get(input);
    }
  
    /**
     * 课程类型创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public CouseTypeDto CreateOrEdit(@RequestBody CouseTypeDto input) throws Exception {
        return _CouseTypeService.CreateOrEdit(input);
    }
    /**
     * 课程类型删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _CouseTypeService.removeById(input.getId());
    }

    /**
     * 课程类型批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _CouseTypeService.removeByIds(input.getIds());
    }

 
}
