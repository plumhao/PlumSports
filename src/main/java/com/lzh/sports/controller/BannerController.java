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
 * 门户封面控制器
 */
@RestController()
@RequestMapping("/Banner")
public class BannerController {
    @Autowired()
    private  BannerService _BannerService;
    @Autowired()
    private BannerMapper _BannerMapper;
    /**
     * 门户封面分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<BannerDto> List(@RequestBody BannerPagedInput input)  {
        return _BannerService.List(input);
    }
     /**
     * 单个门户封面查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public BannerDto Get(@RequestBody BannerPagedInput input) {

        return _BannerService.Get(input);
    }
  
    /**
     * 门户封面创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public BannerDto CreateOrEdit(@RequestBody BannerDto input) throws Exception {
        return _BannerService.CreateOrEdit(input);
    }
    /**
     * 门户封面删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _BannerService.removeById(input.getId());
    }

    /**
     * 门户封面批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _BannerService.removeByIds(input.getIds());
    }

   
}
