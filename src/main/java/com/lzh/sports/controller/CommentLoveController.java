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
 * 评论点赞记录控制器
 */
@RestController()
@RequestMapping("/CommentLove")
public class CommentLoveController {
    @Autowired()
    private  CommentLoveService _CommentLoveService;
    @Autowired()
    private CommentLoveMapper _CommentLoveMapper;
    /**
     * 评论点赞记录分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<CommentLoveDto> List(@RequestBody CommentLovePagedInput input)  {
        return _CommentLoveService.List(input);
    }
     /**
     * 单个评论点赞记录查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public CommentLoveDto Get(@RequestBody CommentLovePagedInput input) {

        return _CommentLoveService.Get(input);
    }
  
    /**
     * 评论点赞记录创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public CommentLoveDto CreateOrEdit(@RequestBody CommentLoveDto input) throws Exception {
        return _CommentLoveService.CreateOrEdit(input);
    }
    /**
     * 评论点赞记录删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {

        _CommentLoveService.removeById(input.getId());
    }

    /**
     * 评论点赞记录批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _CommentLoveService.removeByIds(input.getIds());
    }

 
}
