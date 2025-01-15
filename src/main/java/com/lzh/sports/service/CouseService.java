package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;


/**
 * 课程功能的Service接口的定义清单
 */
public interface CouseService extends IService<Couse> {

    /**
     * 课程的分页查询方法接口定义
     */
    public PagedResult<CouseDto> List(CousePagedInput input) ;
    /**
     * 课程的新增或者修改方法接口定义
     */
    public CouseDto CreateOrEdit(CouseDto input);

     /**
     * 获取课程信息
     */
    public CouseDto Get(CousePagedInput input);


}
