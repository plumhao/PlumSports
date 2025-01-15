package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;


/**
 * 话题类型功能的Service接口的定义清单
 */
public interface TopicTypeService extends IService<TopicType> {

    /**
     * 话题类型的分页查询方法接口定义
     */
    public PagedResult<TopicTypeDto> List(TopicTypePagedInput input) ;
    /**
     * 话题类型的新增或者修改方法接口定义
     */
    public TopicTypeDto CreateOrEdit(TopicTypeDto input);

     /**
     * 获取话题类型信息
     */
    public TopicTypeDto Get(TopicTypePagedInput input);


}
