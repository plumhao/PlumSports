package com.lzh.sports.controller;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.mapper.*;
import com.lzh.sports.service.*;
import com.lzh.sports.tools.dto.*;
import com.lzh.sports.tools.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.SneakyThrows;
import java.io.IOException;

/**
 * 评论控制器
 */
@RestController()
@RequestMapping("/Comment")
public class CommentController {
    @Autowired()
    private  CommentService _CommentService;
    @Autowired()
    private CommentMapper _CommentMapper;
    /**
     * 评论分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<CommentDto> List(@RequestBody CommentPagedInput input)  {
        return _CommentService.List(input);
    }

    /**
     * 获取评论信息
     */
    @RequestMapping(value = "/RootList", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<CommentDto> RootList(@RequestBody CommentPagedInput input) {

        return _CommentService.RootList(input);
    }
     /**
     * 单个评论查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public CommentDto Get(@RequestBody CommentPagedInput input) {

        return _CommentService.Get(input);
    }
  
    /**
     * 评论创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public CommentDto CreateOrEdit(@RequestBody CommentDto input) throws Exception {
        return _CommentService.CreateOrEdit(input);
    }
    /**
     * 评论删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _CommentService.removeById(input.getId());
    }

    /**
     * 评论批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _CommentService.removeByIds(input.getIds());
    }

    /**
     * 评论导入
     */
    @RequestMapping(value = "/Import", method = RequestMethod.POST)
    public void Import(MultipartFile file, HttpServletRequest request) throws IOException {
        if(file==null)
        {
            throw  new CustomException("导入的文件不能为空");
        }      
    }

    /**
     * 评论导出
     */
    @RequestMapping(value = "/Export", method = RequestMethod.POST)
    public void Export(HttpServletRequest request)
    {
    }
}
