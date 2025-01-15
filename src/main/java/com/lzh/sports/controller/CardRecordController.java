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
 * 会员变动记录控制器
 */
@RestController()
@RequestMapping("/CardRecord")
public class CardRecordController {
    @Autowired()
    private  CardRecordService _CardRecordService;
    @Autowired()
    private CardRecordMapper _CardRecordMapper;
    /**
     * 会员变动记录分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<CardRecordDto> List(@RequestBody CardRecordPagedInput input)  {
        return _CardRecordService.List(input);
    }
     /**
     * 单个会员变动记录查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public CardRecordDto Get(@RequestBody CardRecordPagedInput input) {

        return _CardRecordService.Get(input);
    }
  
    /**
     * 会员变动记录创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public CardRecordDto CreateOrEdit(@RequestBody CardRecordDto input) throws Exception {
        return _CardRecordService.CreateOrEdit(input);
    }
    /**
     * 会员变动记录删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _CardRecordService.removeById(input.getId());
    }

    /**
     * 会员变动记录批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _CardRecordService.removeByIds(input.getIds());
    }

 
}
