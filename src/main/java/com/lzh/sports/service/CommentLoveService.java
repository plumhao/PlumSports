package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;


/**
 * 评论点赞记录功能的Service接口的定义清单
 */
public interface CommentLoveService extends IService<CommentLove> {

    /**
     * 评论点赞记录的分页查询方法接口定义
     */
    public PagedResult<CommentLoveDto> List(CommentLovePagedInput input) ;
    /**
     * 评论点赞记录的新增或者修改方法接口定义
     */
    public CommentLoveDto CreateOrEdit(CommentLoveDto input);

     /**
     * 获取评论点赞记录信息
     */
    public CommentLoveDto Get(CommentLovePagedInput input);


}
