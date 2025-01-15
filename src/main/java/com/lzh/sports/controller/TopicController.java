package com.lzh.sports.controller;

import com.lzh.sports.dto.TopicDto;
import com.lzh.sports.dto.query.TopicPagedInput;
import com.lzh.sports.entity.*;
import com.lzh.sports.mapper.*;
import com.lzh.sports.service.TopicService;
import com.lzh.sports.tools.Extension;
import com.lzh.sports.tools.dto.IdInput;
import com.lzh.sports.tools.dto.IdsInput;
import com.lzh.sports.tools.dto.PagedResult;
import com.lzh.sports.tools.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 话题控制器
 */
@RestController()
@RequestMapping("/Topic")
public class TopicController {
    @Autowired()
    private TopicService _TopicService;
    @Autowired()
    private TopicMapper _TopicMapper;
    @Autowired()
    private TopicRecordMapper _TopicRecordMapper;
    @Autowired()
    private TopicTypeMapper _TopicTypeMapper;
    @Autowired()
    private TopicCollectMapper _TopicCollectMapper;

    @Autowired()
    private AppUserMapper _AppUserMapper;
    /**
     * 话题分页查询
     */
    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<TopicDto> List(@RequestBody TopicPagedInput input)  {
        return _TopicService.List(input);
    }

    /**
     * 话题推荐
     */
    @RequestMapping(value = "/RecommendTop10", method = RequestMethod.POST)
    @SneakyThrows
    public PagedResult<TopicDto> RecommendTop10(@RequestBody TopicPagedInput input)  {
        return _TopicService.RecommendTop10(input);
    }



    /**
     * 单个话题查询接口
     */
    @RequestMapping(value = "/Get", method = RequestMethod.POST)
    @SneakyThrows
    public TopicDto Get(@RequestBody TopicPagedInput input) {

        return _TopicService.Get(input);
    }

    /**
     * 话题创建或则修改
     */
    @RequestMapping(value = "/CreateOrEdit", method = RequestMethod.POST)
    public TopicDto CreateOrEdit(@RequestBody TopicDto input) throws Exception {
        return _TopicService.CreateOrEdit(input);
    }
    /**
     * 话题删除
     */
    @RequestMapping(value = "/Delete", method = RequestMethod.POST)
    public void Delete(@RequestBody IdInput input)
    {
        _TopicService.removeById(input.getId());
    }

    /**
     * 话题批量删除
     */
    @RequestMapping(value = "/BatchDelete", method = RequestMethod.POST)
    public void BatchDelete(@RequestBody IdsInput input)
    {
        _TopicService.removeByIds(input.getIds());
    }

    /**
     * 话题导入
     */
    @RequestMapping(value = "/Import", method = RequestMethod.POST)
    public void Import(MultipartFile file, HttpServletRequest request) throws IOException {
        if(file==null)
        {
            throw  new CustomException("导入的文件不能为空");
        }
    }
    /**
     * 得到最活跃的健身分享人
     */
    @RequestMapping(value = "/MostActiveTopicOfBlogger", method = RequestMethod.POST)
    @SneakyThrows
    private Map<String, Object> MostActiveTopicOfBlogger(){
        var map=new HashMap<String,Object>();
        //得到所有的话题
        List<Topic> topics = _TopicMapper.selectList(null).stream().toList();
        //得到所有的话题浏览记录
        List<TopicRecord> topicRecords = _TopicRecordMapper.selectList(null).stream().toList();
        //得到所有的话题收藏记录
        List<TopicCollect> topicCollects = _TopicCollectMapper.selectList(null).stream().toList();

        //得到为用户角色的列表
        List<AppUser> users = _AppUserMapper.selectList(null).stream().filter(x -> x.getRoleType() == Enums.RoleType.用户.index()).toList();


        //发帖数量来决定它的活跃程度
        //得到最活跃的话题博主
        List<Map<String, Object>> mostActiveTopicOfBlogger = new ArrayList<>();
        for (AppUser user : users) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", user.getName());
            //得到对应的话题编号
            List<Integer> topicIds = topics.stream().filter(x -> x.getCreatorId() == user.getId()).map(x -> x.getId()).toList();

            //得到它的最新发的话题
            List<Topic> topicss = topics.stream().filter(x -> topicIds.contains(x.getId())&&x.getAuditStatus()==Enums.AuditStatus.审核通过.index()).toList();
            data.put("topicCount",topicss.stream().count());
            //按创建时间进行倒叙
            topicss=topicss.stream().sorted((x,y)->{
                return y.getCreationTime().compareTo(x.getCreationTime());
            }).toList();
            //取前3个
            topicss=topicss.stream().limit(3).toList();
            data.put("topics", topicss);
            //得到话题所有的浏览记录
            List<TopicRecord> topicRecords1 = topicRecords.stream().filter(x -> topicIds.contains(x.getTopicId())).toList();
            //得到话题所有的收藏记录
            List<TopicCollect> topicCollects1 = topicCollects.stream().filter(x -> topicIds.contains(x.getTopicId())).toList();
            data.put("readCount", topicRecords1.size());
            data.put("collectCount", topicCollects1.size());
            data.put("userInfo", user);
            mostActiveTopicOfBlogger.add(data);

        }
        //根据发帖数进行排序
        mostActiveTopicOfBlogger=mostActiveTopicOfBlogger.stream().sorted((x,y)->{
            return (int)y.get("readCount")-(int)x.get("readCount");
        }).toList();
        //top10个
        mostActiveTopicOfBlogger=mostActiveTopicOfBlogger.stream().limit(10).toList();

        map.put("datas",mostActiveTopicOfBlogger);
        return map;
    }
    /**
     * 查询我的话题一些信息(文章 收藏人数  阅读人数)
     * @return
     */
    @RequestMapping(value = "/MyTopicInfoCard", method = RequestMethod.POST)
    @SneakyThrows
    private Map<String, Object> MyTopicInfoCard(@RequestBody IdInput input){
        var map=new HashMap<String,Object>();

        Integer userId =input.getId();
        //得到我的审核过了的文章的ids
        var topicIds= _TopicMapper.selectList(null).stream().filter(x->x.getAuditStatus()== Enums.AuditStatus.审核通过.index()&&x.getCreatorId().equals(userId)).toList().stream().map(x->x.getId()).toList();

        map.put("articleCount",topicIds.size());
        //得到我的文章总阅读人数
        var readCount= _TopicRecordMapper.selectList(null).stream().filter(x->topicIds.contains(x.getTopicId())).toList().size();
        map.put("readCount",readCount);
        //得到我的文章总收藏人数
        var collectCount= _TopicCollectMapper.selectList(null).stream().filter(x->topicIds.contains(x.getTopicId())).toList().size();
        map.put("collectCount",collectCount);
        return map;
    }

    /**
     * 话题导出
     */
    @RequestMapping(value = "/Export", method = RequestMethod.POST)
    public void Export(HttpServletRequest request)
    {
    }
    @RequestMapping(value = "/TopicAnalyse", method = RequestMethod.POST)
    @SneakyThrows
    private Map<String, Object> TopicAnalyse() {

        List<Topic> list = _TopicMapper.selectList(null).stream().toList();

        //得到话题类型
        List<TopicType> topicTypes = _TopicTypeMapper.selectList(null).stream().toList();
        List<TopicRecord> topicRecords = _TopicRecordMapper.selectList(null).stream().toList();

        //得到最近7天的浏览记录
        topicRecords = topicRecords.stream().filter(x -> x.getCreationTime().toLocalDate().isAfter(LocalDate.now().minusDays(7))).toList();

        Map<String,Object> result=new HashMap<>();
        //得到最近7天的日期
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dates.add(Extension.GetWeek(LocalDate.now().minusDays(i)));
        }
        result.put("week",dates);

        List<Map<String, Object>> allDataList = new ArrayList<>();
        //循环话题类型
        for (TopicType topicType : topicTypes) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", topicType.getName());
            //得到对应的话题编号
            List<Integer> topicIds = list.stream().filter(x -> x.getTopicTypeId() == topicType.getId()).map(x -> x.getId()).toList();
            //得到话题所有的浏览记录
            List<TopicRecord> topicRecords1 = topicRecords.stream().filter(x -> topicIds.contains(x.getTopicId())).toList();

            ArrayList<Integer> counts=new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                //得到当天的浏览记录
                int day=i;
                List<TopicRecord>  currentTopicRecords = topicRecords1.stream().filter(x -> x.getCreationTime().toLocalDate().equals(LocalDate.now().minusDays(day))).toList();
                counts.add(currentTopicRecords.size());
            }
            data.put("values", counts);

            allDataList.add(data);
        }
        result.put("datas",allDataList);
        return result;
    }

}
