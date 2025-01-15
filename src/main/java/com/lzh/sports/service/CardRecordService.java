package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;


/**
 * 会员变动记录功能的Service接口的定义清单
 */
public interface CardRecordService extends IService<CardRecord> {

    /**
     * 会员变动记录的分页查询方法接口定义
     */
    public PagedResult<CardRecordDto> List(CardRecordPagedInput input) ;
    /**
     * 会员变动记录的新增或者修改方法接口定义
     */
    public CardRecordDto CreateOrEdit(CardRecordDto input);

     /**
     * 获取会员变动记录信息
     */
    public CardRecordDto Get(CardRecordPagedInput input);


}
