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
 * 余额变动记录控制器
 */
@RestController()
@RequestMapping("/MoneyRecord")
public class MoneyRecordController {
    @Autowired()
    private  MoneyRecordService _MoneyRecordService;
    @Autowired()
    private MoneyRecordMapper _MoneyRecordMapper;
    /**
     * 余额变动记录分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<MoneyRecordDto> List(@RequestBody MoneyRecordPagedInput input)  {
        return _MoneyRecordService.List(input);
    }
     /**
     * 单个余额变动记录查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public MoneyRecordDto Get(@RequestBody MoneyRecordPagedInput input) {

        return _MoneyRecordService.Get(input);
    }
  
    /**
     * 余额变动记录创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public MoneyRecordDto CreateOrEdit(@RequestBody MoneyRecordDto input) throws Exception {
        return _MoneyRecordService.CreateOrEdit(input);
    }
    /**
     * 余额变动记录删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _MoneyRecordService.removeById(input.getId());
    }

    /**
     * 余额变动记录批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _MoneyRecordService.removeByIds(input.getIds());
    }

    /**
     * 充值
     */
    @RequestMapping(value = "/Recharge", method = RequestMethod.POST)
    public void Recharge(@RequestBody MoneyRecordDto input){
        _MoneyRecordService.Recharge(input);

    }
}
