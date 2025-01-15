package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;
import lombok.SneakyThrows;


/**
 * 余额变动记录功能的Service接口的定义清单
 */
public interface MoneyRecordService extends IService<MoneyRecord> {

    /**
     * 余额变动记录的分页查询方法接口定义
     */
    public PagedResult<MoneyRecordDto> List(MoneyRecordPagedInput input) ;
    /**
     * 余额变动记录的新增或者修改方法接口定义
     */
    public MoneyRecordDto CreateOrEdit(MoneyRecordDto input);

     /**
     * 获取余额变动记录信息
     */
    public MoneyRecordDto Get(MoneyRecordPagedInput input);


    @SneakyThrows
    void Recharge(MoneyRecordDto input);
}
