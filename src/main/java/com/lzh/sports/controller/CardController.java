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
 * 会员卡控制器
 */
@RestController()
@RequestMapping("/Card")
public class CardController {
    @Autowired()
    private  CardService _CardService;
    @Autowired()
    private CardMapper _CardMapper;
    /**
     * 会员卡分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<CardDto> List(@RequestBody CardPagedInput input)  {
        return _CardService.List(input);
    }
     /**
     * 单个会员卡查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public CardDto Get(@RequestBody CardPagedInput input) {

        return _CardService.Get(input);
    }
  
    /**
     * 会员卡创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public CardDto CreateOrEdit(@RequestBody CardDto input) throws Exception {
        return _CardService.CreateOrEdit(input);
    }
    /**
     * 会员卡删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _CardService.removeById(input.getId());
    }

    /**
     * 会员卡批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _CardService.removeByIds(input.getIds());
    }

 
}
