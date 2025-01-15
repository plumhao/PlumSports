package com.lzh.sports.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.TopicRecordDto;
import com.lzh.sports.dto.query.TopicRecordPagedInput;
import com.lzh.sports.entity.TopicRecord;
import com.lzh.sports.tools.dto.PagedResult;


/**
 * 话题浏览记录功能的Service接口的定义清单
 */
public interface TopicRecordService extends IService<TopicRecord> {

    /**
     * 话题浏览记录的分页查询方法接口定义
     */
    public PagedResult<TopicRecordDto> List(TopicRecordPagedInput input) ;
    /**
     * 话题浏览记录的新增或者修改方法接口定义
     */
    public TopicRecordDto CreateOrEdit(TopicRecordDto input);

     /**
     * 获取话题浏览记录信息
     */
    public TopicRecordDto Get(TopicRecordPagedInput input);


}
