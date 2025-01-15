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
 * 系统通知控制器
 */
@RestController()
@RequestMapping("/SysNotice")
public class SysNoticeController {
    @Autowired()
    private  SysNoticeService _SysNoticeService;
    @Autowired()
    private SysNoticeMapper _SysNoticeMapper;
    /**
     * 系统通知分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<SysNoticeDto> List(@RequestBody SysNoticePagedInput input)  {
        return _SysNoticeService.List(input);
    }
     /**
     * 单个系统通知查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public SysNoticeDto Get(@RequestBody SysNoticePagedInput input) {

        return _SysNoticeService.Get(input);
    }
  
    /**
     * 系统通知创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public SysNoticeDto CreateOrEdit(@RequestBody SysNoticeDto input) throws Exception {
        return _SysNoticeService.CreateOrEdit(input);
    }
    /**
     * 系统通知删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _SysNoticeService.removeById(input.getId());
    }

    /**
     * 系统通知批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _SysNoticeService.removeByIds(input.getIds());
    }

 
}
