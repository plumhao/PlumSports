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
 * 留言控制器
 */
@RestController()
@RequestMapping("/LeaveMessage")
public class LeaveMessageController {
    @Autowired()
    private  LeaveMessageService _LeaveMessageService;
    @Autowired()
    private LeaveMessageMapper _LeaveMessageMapper;
    /**
     * 留言分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<LeaveMessageDto> List(@RequestBody LeaveMessagePagedInput input)  {
        return _LeaveMessageService.List(input);
    }
     /**
     * 单个留言查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public LeaveMessageDto Get(@RequestBody LeaveMessagePagedInput input) {

        return _LeaveMessageService.Get(input);
    }
  
    /**
     * 留言创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public LeaveMessageDto CreateOrEdit(@RequestBody LeaveMessageDto input) throws Exception {
        return _LeaveMessageService.CreateOrEdit(input);
    }
    /**
     * 留言删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _LeaveMessageService.removeById(input.getId());
    }

    /**
     * 留言批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _LeaveMessageService.removeByIds(input.getIds());
    }

 
}
