package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;


/**
 * 会员卡功能的Service接口的定义清单
 */
public interface CardService extends IService<Card> {

    /**
     * 会员卡的分页查询方法接口定义
     */
    public PagedResult<CardDto> List(CardPagedInput input) ;
    /**
     * 会员卡的新增或者修改方法接口定义
     */
    public CardDto CreateOrEdit(CardDto input);

     /**
     * 获取会员卡信息
     */
    public CardDto Get(CardPagedInput input);


}
