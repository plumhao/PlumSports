package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;


/**
 * 课程类型功能的Service接口的定义清单
 */
public interface CouseTypeService extends IService<CouseType> {

    /**
     * 课程类型的分页查询方法接口定义
     */
    public PagedResult<CouseTypeDto> List(CouseTypePagedInput input) ;
    /**
     * 课程类型的新增或者修改方法接口定义
     */
    public CouseTypeDto CreateOrEdit(CouseTypeDto input);

     /**
     * 获取课程类型信息
     */
    public CouseTypeDto Get(CouseTypePagedInput input);


}
