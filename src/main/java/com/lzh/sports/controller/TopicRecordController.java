package com.lzh.sports.controller;

import com.lzh.sports.dto.TopicRecordDto;
import com.lzh.sports.dto.query.TopicRecordPagedInput;
import com.lzh.sports.mapper.TopicRecordMapper;
import com.lzh.sports.service.TopicRecordService;
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
 * 话题浏览记录控制器
 */
@RestController()
@RequestMapping("/TopicRecord")
public class TopicRecordController {
    @Autowired()
    private TopicRecordService _TopicRecordService;
    @Autowired()
    private TopicRecordMapper _TopicRecordMapper;
    /**
     * 话题浏览记录分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<TopicRecordDto> List(@RequestBody TopicRecordPagedInput input)  {
        return _TopicRecordService.List(input);
    }
     /**
     * 单个话题浏览记录查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public TopicRecordDto Get(@RequestBody TopicRecordPagedInput input) {

        return _TopicRecordService.Get(input);
    }
  
    /**
     * 话题浏览记录创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public TopicRecordDto CreateOrEdit(@RequestBody TopicRecordDto input) throws Exception {
        return _TopicRecordService.CreateOrEdit(input);
    }
    /**
     * 话题浏览记录删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _TopicRecordService.removeById(input.getId());
    }

    /**
     * 话题浏览记录批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _TopicRecordService.removeByIds(input.getIds());
    }

 
}
