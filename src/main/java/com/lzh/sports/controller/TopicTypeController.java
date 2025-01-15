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
 * 话题类型控制器
 */
@RestController()
@RequestMapping("/TopicType")
public class TopicTypeController {
    @Autowired()
    private  TopicTypeService _TopicTypeService;
    @Autowired()
    private TopicTypeMapper _TopicTypeMapper;
    /**
     * 话题类型分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<TopicTypeDto> List(@RequestBody TopicTypePagedInput input)  {
        return _TopicTypeService.List(input);
    }
     /**
     * 单个话题类型查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public TopicTypeDto Get(@RequestBody TopicTypePagedInput input) {

        return _TopicTypeService.Get(input);
    }
  
    /**
     * 话题类型创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public TopicTypeDto CreateOrEdit(@RequestBody TopicTypeDto input) throws Exception {
        return _TopicTypeService.CreateOrEdit(input);
    }
    /**
     * 话题类型删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _TopicTypeService.removeById(input.getId());
    }

    /**
     * 话题类型批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _TopicTypeService.removeByIds(input.getIds());
    }

    /**
     * 话题类型导入
     */
    @RequestMapping(value = "/Import", method = RequestMethod.POST)
    public void Import(MultipartFile file, HttpServletRequest request) throws IOException {
        if(file==null)
        {
            throw  new CustomException("导入的文件不能为空");
        }      
    }

    /**
     * 话题类型导出
     */
    @RequestMapping(value = "/Export", method = RequestMethod.POST)
    public void Export(HttpServletRequest request)
    {
    }
}
