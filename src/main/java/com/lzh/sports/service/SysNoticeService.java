package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;


/**
 * 系统通知功能的Service接口的定义清单
 */
public interface SysNoticeService extends IService<SysNotice> {

    /**
     * 系统通知的分页查询方法接口定义
     */
    public PagedResult<SysNoticeDto> List(SysNoticePagedInput input) ;
    /**
     * 系统通知的新增或者修改方法接口定义
     */
    public SysNoticeDto CreateOrEdit(SysNoticeDto input);

     /**
     * 获取系统通知信息
     */
    public SysNoticeDto Get(SysNoticePagedInput input);


}
