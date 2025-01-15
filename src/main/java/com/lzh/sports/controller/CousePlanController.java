package com.lzh.sports.controller;

import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.mapper.*;
import com.lzh.sports.service.*;
import com.lzh.sports.tools.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.SneakyThrows;

import java.util.*;

/**
 * 课程安排控制器
 */
@RestController()
@RequestMapping("/CousePlan")
public class CousePlanController {
    @Autowired()
    private CousePlanService _CousePlanService;
    @Autowired()
    private CousePlanMapper _CousePlanMapper;

    /**
     * 课程安排分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<CousePlanDto> List(@RequestBody CousePlanPagedInput input) {
        return _CousePlanService.List(input);
    }

    /**
     * 单个课程安排查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public CousePlanDto Get(@RequestBody CousePlanPagedInput input) {

        return _CousePlanService.Get(input);
    }


    /**
     * 查看排班信息
     */
    @RequestMapping(value = "/ArrangeList", method = RequestMethod.POST)
    @SneakyThrows
    public List<Map<String, Object>> ArrangeList(@RequestBody CousePlanPagedInput input) {
        return _CousePlanService.ArrangeList(input);
    }
    /**
     * 查看排班信息
     */
    @RequestMapping(value = "/ArrangeDetailList", method = RequestMethod.POST)
    @SneakyThrows
    public   List<CousePlanDto>  ArrangeDetailList(@RequestBody CousePlanPagedInput input) {
        return _CousePlanService.ArrangeDetailList(input);
    }
    /**
     * 得到排班中所有的教练信息
     */
    @RequestMapping(value = "/ArrangeDetailAllCoach", method = RequestMethod.POST)
    @SneakyThrows
    public   List<AppUserDto>  ArrangeDetailAllCoach(@RequestBody CousePlanPagedInput input) {
        return _CousePlanService.ArrangeDetailAllCoach(input);
    }


    /**
     * 课程安排创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public CousePlanDto CreateOrEdit(@RequestBody CousePlanDto input) throws Exception {
        return _CousePlanService.CreateOrEdit(input);
    }

    /**
     * 课程安排删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input) {
        _CousePlanService.removeById(input.getId());
    }

    /**
     * 课程安排批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input) {
        _CousePlanService.removeByIds(input.getIds());
    }


}
