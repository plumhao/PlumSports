package com.lzh.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;


/**
 * 课程安排功能的Service接口的定义清单
 */
public interface CousePlanService extends IService<CousePlan> {

    /**
     * 课程安排的分页查询方法接口定义
     */
    public PagedResult<CousePlanDto> List(CousePlanPagedInput input);

    /**
     * 课程安排的新增或者修改方法接口定义
     */
    public CousePlanDto CreateOrEdit(CousePlanDto input);

    /**
     * 查看排班信息
     *
     * @param input
     * @return
     */
    @SneakyThrows
    List<Map<String, Object>> ArrangeList(CousePlanPagedInput input);

    List<AppUserDto> ArrangeDetailAllCoach(CousePlanPagedInput input);

    /**
     * 排班明细列表
     */
    @SneakyThrows
    List<CousePlanDto> ArrangeDetailList(CousePlanPagedInput input);

    /**
     * 获取课程安排信息
     */
    public CousePlanDto Get(CousePlanPagedInput input);


}
