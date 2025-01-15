package com.lzh.sports.controller;

import com.lzh.sports.dto.TopicCollectDto;
import com.lzh.sports.dto.query.TopicCollectPagedInput;
import com.lzh.sports.mapper.TopicCollectMapper;
import com.lzh.sports.service.TopicCollectService;
import com.lzh.sports.tools.dto.IdInput;
import com.lzh.sports.tools.dto.IdsInput;
import com.lzh.sports.tools.dto.PagedResult;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题收藏控制器
 */
@RestController()
@RequestMapping("/TopicCollect")
public class TopicCollectController {
    @Autowired()
    private TopicCollectService _TopicCollectService;
    @Autowired()
    private TopicCollectMapper _TopicCollectMapper;
    /**
     * 话题收藏分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<TopicCollectDto> List(@RequestBody TopicCollectPagedInput input)  {
        return _TopicCollectService.List(input);
    }
     /**
     * 单个话题收藏查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public TopicCollectDto Get(@RequestBody TopicCollectPagedInput input) {

        return _TopicCollectService.Get(input);
    }
  
    /**
     * 话题收藏创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public TopicCollectDto CreateOrEdit(@RequestBody TopicCollectDto input) throws Exception {
        return _TopicCollectService.CreateOrEdit(input);
    }
    /**
     * 话题收藏删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _TopicCollectService.removeById(input.getId());
    }

    /**
     * 话题收藏批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _TopicCollectService.removeByIds(input.getIds());
    }

 
}
