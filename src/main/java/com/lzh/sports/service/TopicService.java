package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;
import lombok.SneakyThrows;


/**
 * 话题功能的Service接口的定义清单
 */
public interface TopicService extends IService<Topic> {

    @SneakyThrows
    PagedResult<TopicDto> RecommendTop10(TopicPagedInput input);

    /**
     * 话题的分页查询方法接口定义
     */
    public PagedResult<TopicDto> List(TopicPagedInput input) ;
    /**
     * 话题的新增或者修改方法接口定义
     */
    public TopicDto CreateOrEdit(TopicDto input);

     /**
     * 获取话题信息
     */
    public TopicDto Get(TopicPagedInput input);


}
