package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;
import lombok.SneakyThrows;


/**
 * 评论功能的Service接口的定义清单
 */
public interface CommentService extends IService<Comment> {

    /**
     * 评论的分页查询方法接口定义
     */
    public PagedResult<CommentDto> List(CommentPagedInput input) ;
    /**
     * 评论的新增或者修改方法接口定义
     */
    public CommentDto CreateOrEdit(CommentDto input);

    @SneakyThrows
    PagedResult<CommentDto> RootList(CommentPagedInput input);

    /**
     * 获取评论信息
     */
    public CommentDto Get(CommentPagedInput input);


}
