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
 * 课程控制器
 */
@RestController()
@RequestMapping("/Couse")
public class CouseController {
    @Autowired()
    private  CouseService _CouseService;
    @Autowired()
    private CouseMapper _CouseMapper;
    /**
     * 课程分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<CouseDto> List(@RequestBody CousePagedInput input)  {
        return _CouseService.List(input);
    }
     /**
     * 单个课程查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public CouseDto Get(@RequestBody CousePagedInput input) {

        return _CouseService.Get(input);
    }
  
    /**
     * 课程创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public CouseDto CreateOrEdit(@RequestBody CouseDto input) throws Exception {
        return _CouseService.CreateOrEdit(input);
    }
    /**
     * 课程删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _CouseService.removeById(input.getId());
    }

    /**
     * 课程批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _CouseService.removeByIds(input.getIds());
    }

 
}
