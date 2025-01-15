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
 * 课程预约记录控制器
 */
@RestController()
@RequestMapping("/CouseAppointRecord")
public class CouseAppointRecordController {
    @Autowired()
    private  CouseAppointRecordService _CouseAppointRecordService;
    @Autowired()
    private CouseAppointRecordMapper _CouseAppointRecordMapper;
    /**
     * 课程预约记录分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<CouseAppointRecordDto> List(@RequestBody CouseAppointRecordPagedInput input)  {
        return _CouseAppointRecordService.List(input);
    }
     /**
     * 单个课程预约记录查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public CouseAppointRecordDto Get(@RequestBody CouseAppointRecordPagedInput input) {

        return _CouseAppointRecordService.Get(input);
    }
  
    /**
     * 课程预约记录创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public CouseAppointRecordDto CreateOrEdit(@RequestBody CouseAppointRecordDto input) throws Exception {
        return _CouseAppointRecordService.CreateOrEdit(input);
    }
    /**
     * 课程预约记录删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _CouseAppointRecordService.removeById(input.getId());
    }

    /**
     * 课程预约记录批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _CouseAppointRecordService.removeByIds(input.getIds());
    }

    /**
     * 课程预约
     */
    @RequestMapping(value = "/Appoint", method = RequestMethod.POST)
    public void Appoint(@RequestBody CouseAppointRecordDto input) {
        _CouseAppointRecordService.Appoint(input);
    }

    /**
     * 课程取消
     */
    @RequestMapping(value = "/Cancel", method = RequestMethod.POST)
    public void Cancel(@RequestBody CouseAppointRecordDto input) {
        _CouseAppointRecordService.Cancel(input);
    }
    /**
     * 课程签到
     */
    @RequestMapping(value = "/Check", method = RequestMethod.POST)
    public void Check(@RequestBody CouseAppointRecordDto input) {
        _CouseAppointRecordService.Check(input);
    }

}
