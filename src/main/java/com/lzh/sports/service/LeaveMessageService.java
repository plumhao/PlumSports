package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;


/**
 * 留言功能的Service接口的定义清单
 */
public interface LeaveMessageService extends IService<LeaveMessage> {

    /**
     * 留言的分页查询方法接口定义
     */
    public PagedResult<LeaveMessageDto> List(LeaveMessagePagedInput input) ;
    /**
     * 留言的新增或者修改方法接口定义
     */
    public LeaveMessageDto CreateOrEdit(LeaveMessageDto input);

     /**
     * 获取留言信息
     */
    public LeaveMessageDto Get(LeaveMessagePagedInput input);


}
