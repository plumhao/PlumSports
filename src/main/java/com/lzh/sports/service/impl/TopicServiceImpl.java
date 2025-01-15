package com.lzh.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzh.sports.dto.AppUserDto;
import com.lzh.sports.dto.CommentDto;
import com.lzh.sports.dto.TopicDto;
import com.lzh.sports.dto.TopicTypeDto;
import com.lzh.sports.dto.query.TopicPagedInput;
import com.lzh.sports.entity.*;
import com.lzh.sports.mapper.*;
import com.lzh.sports.service.TopicService;
import com.lzh.sports.tools.BaseContext;
import com.lzh.sports.tools.CollaborativeFiltering;
import com.lzh.sports.tools.Extension;
import com.lzh.sports.tools.dto.CurrentUserDto;
import com.lzh.sports.tools.dto.PagedResult;
import com.lzh.sports.tools.dto.UserCalculateCosineSimilarity;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 话题功能实现类
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的Topic表mapper对象
     */
    @Autowired
    private TopicMapper _TopicMpper;


    @Autowired
    private TopicTypeMapper _TopicTypeMapper;

    @Autowired
    private CommentMapper _CommentMapper;

    /**
     * 操作数据库的TopicRecord表mapper对象
     */
    @Autowired
    private TopicRecordMapper _TopicRecordMpper;


    /**
     * 协同过滤算法推荐(根据浏览记录获取资讯)
     */
    @SneakyThrows
    @Override
    public PagedResult<TopicDto> RecommendTop10(TopicPagedInput input) {
        PagedResult<TopicDto> topicDtoPagedResult = RecommendCollaborativeFilteringTopic(input);

        for (TopicDto item : topicDtoPagedResult.getItems()) {

            //查询出关联的创建用户信息
            AppUserDto  CreatorAppUserDTO=new AppUserDto();
            AppUser  CreatorAppUserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getCreatorId()));
            if(CreatorAppUserEntity!=null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }
            //查询关联的Comment表信息
            List<Comment> CommentEntitys = _CommentMapper.selectList(Wrappers.<Comment>lambdaQuery().eq(Comment::getTopicId, item.getId()));
            List<CommentDto> CommentDTOS = Extension.copyBeanList(CommentEntitys, CommentDto.class);
            item.setCommentDtos(CommentDTOS);



            //查询出关联的TopicType表信息
            TopicTypeDto TopicTypeDTO = new TopicTypeDto();
            TopicType  TopicTypeEntity= _TopicTypeMapper.selectOne(Wrappers.<TopicType>lambdaQuery().eq(TopicType::getId,item.getTopicTypeId()));
            if(TopicTypeEntity!=null) {
                BeanUtils.copyProperties(TopicTypeDTO,TopicTypeEntity);
                item.setTopicTypeDto(TopicTypeDTO);
            }

        }
        return topicDtoPagedResult;
    }

    /**
     * 协同算法推荐
     */
    private PagedResult<TopicDto> RecommendCollaborativeFilteringTopic(TopicPagedInput input)
    {
        CurrentUserDto currentUserDto = BaseContext.getCurrentUserDto();

        //如果用户登录了
        if (currentUserDto != null && currentUserDto.getUserId() != null) {
            //协同过滤算法推荐
            CollaborativeFiltering collaborativeFiltering = new CollaborativeFiltering();

            //当前用户id
            Integer userId = currentUserDto.getUserId();

            //获取所有用户
            List<AppUser> AppUserList = _AppUserMapper.selectList(null).stream().toList();
            //获取所有的浏览记录
            List<TopicRecord> topicRecords = _TopicRecordMpper.selectList(null);

            //遍历所有用户
            for (AppUser appUser : AppUserList) {

                //获取和我有关的浏览资讯
                List<TopicRecord> topicRecordList = topicRecords.stream().filter(x -> x.getUserId() == appUser.getId()).toList();
                //有关联的话题文章编号
                int[] RelativeTopicId = topicRecordList.stream().mapToInt(x -> x.getTopicId()).distinct().toArray();
                //根据编号排除审核失败的文章 失败了就不需要看了
                List<Topic> topics = _TopicMpper.selectList(Wrappers.<Topic>lambdaQuery().eq(Topic::getAuditStatus, Enums.AuditStatus.审核通过.index()).in(Topic::getId, RelativeTopicId));
                for (Topic topic : topics) {

                    //浏览次数
                    //统计我这篇文章浏览的总数
                    long viewCount = topicRecordList.stream().filter(x -> x.getTopicId() == topic.getId()).count();
                    collaborativeFiltering.AddUserRating(appUser.getId(), topic.getId(),(double)viewCount );
                }

            }

            //用户相似度列表
            List<UserCalculateCosineSimilarity> userCalculateCosineSimilarityList = new ArrayList<>();

            //遍历所有用户
            for (AppUser user2 : AppUserList) {
                //如果相同跳过
                if (userId.equals(user2.getId())) {
                    continue;
                }
                //如果存在跳过
                if (userCalculateCosineSimilarityList.stream().filter(x -> x.getUserId1() == userId && x.getUserId2() == user2.getId()).count() > 0) {
                    continue;
                }
                //如果存在跳过
                if (userCalculateCosineSimilarityList.stream().filter(x -> x.getUserId2() == user2.getId() && x.getUserId1() == userId).count() > 0) {
                    continue;
                }
                //计算相似度
                double v = collaborativeFiltering.CalculateCosineSimilarity(userId, user2.getId());
                //添加到相似度列表
                userCalculateCosineSimilarityList.add(new UserCalculateCosineSimilarity(userId, user2.getId(), v));
            }
            //得到相似的用户
            Collections.sort(userCalculateCosineSimilarityList, new Comparator<UserCalculateCosineSimilarity>() {
                @Override
                public int compare(UserCalculateCosineSimilarity o1, UserCalculateCosineSimilarity o2) {
                    return o2.getSimilarity().compareTo(o1.getSimilarity());
                }
            });
            //取前20个
            int endIndex = Math.min(20, userCalculateCosineSimilarityList.size());
            //获取到相似度高的用户
            int[] similarityHighUserId = userCalculateCosineSimilarityList.subList(0, endIndex).stream().mapToInt(x -> x.getUserId2()).toArray();
            //转换成集合
            List<Integer> similarityHighUserIdList = Arrays.stream(similarityHighUserId).boxed().collect(Collectors.toList());

            //获取到相似度高的用户的话题 排除自己
            int[] similarityHighUserIdTopicId=topicRecords.stream().filter(x->similarityHighUserIdList.contains(x.getUserId())&&x.getUserId()!=userId).mapToInt(x->x.getTopicId()).distinct().toArray();


            //转换成list
            List<Integer> similarityHighUserIdTopicIdList = Arrays.stream(similarityHighUserIdTopicId).boxed().collect(Collectors.toList());

            //随机排序
            Collections.shuffle(similarityHighUserIdTopicIdList);

            //取前10个
            int maxGoodCountEndIndex = Math.min(10, similarityHighUserIdTopicIdList.size());

            //得到10个话题id
            List<Integer> top10TopicIds = similarityHighUserIdTopicIdList.subList(0, maxGoodCountEndIndex);

            if(top10TopicIds.stream().count()<10){
                //随机推荐10个话题
                LambdaQueryWrapper<Topic> queryWrapper = Wrappers.<Topic>lambdaQuery()
                        .eq(Topic::getAuditStatus,Enums.AuditStatus.审核通过.index())
                        .orderByDesc(Topic::getCreationTime)
                        .last("limit 10");
                List<Topic> topics = _TopicMpper.selectList(queryWrapper);
                List<TopicDto> items = Extension.copyBeanList(topics, TopicDto.class);
                return PagedResult.GetInstance(items, (long) 10);
            }

            //查询出关联的话题信息
            List<Topic> topics = _TopicMpper.selectList(null);

            topics=topics.stream().filter(x->top10TopicIds.contains(x.getId())).toList();
            List<TopicDto> items = Extension.copyBeanList(topics, TopicDto.class);
            return PagedResult.GetInstance(items, (long) 10);

        } else {
            //随机推荐10个话题
            LambdaQueryWrapper<Topic> queryWrapper = Wrappers.<Topic>lambdaQuery()
                    .orderByDesc(Topic::getCreationTime)
                    .eq(Topic::getAuditStatus,Enums.AuditStatus.审核通过.index())
                    .last("limit 10");
            List<Topic> topics = _TopicMpper.selectList(queryWrapper);
            List<TopicDto> items = Extension.copyBeanList(topics, TopicDto.class);
            return PagedResult.GetInstance(items, (long) 10);
        }
    }

    /**
     * 话题分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<TopicDto> List(TopicPagedInput input) {


        //声明一个支持话题查询的(拉姆达)表达式
        LambdaQueryWrapper<Topic> queryWrapper = Wrappers.<Topic>lambdaQuery()
                .eq(input.getId()!=null,Topic::getId,input.getId())
                .eq(input.getCreatorId()!=null,Topic::getCreatorId,input.getCreatorId());

        //如果前端搜索传入Title不为空,则进行精确查询
        if(Extension.isNotNullOrEmpty(input.getTitle())) {
            queryWrapper=queryWrapper.like(Topic::getTitle,input.getTitle());
        }
        //如果前端搜索传入ViewCountStartRange,则要大于ViewCountStartRange输入的值
        if(input.getViewCountStartRange()!=null)
        {
            queryWrapper=queryWrapper.gt(Topic::getViewCount,input.getViewCountStartRange());
        }
        //如果前端搜索传入ViewCountEndRange,则要小于ViewCountEndRange输入的值
        if(input.getViewCountEndRange()!=null)
        {
            queryWrapper=queryWrapper.lt(Topic::getViewCount,input.getViewCountEndRange());
        }
        //如果前端搜索传入TopicTypeId不为空,则进行精确查询
        if(input.getTopicTypeId()!=null) {
            queryWrapper=queryWrapper.eq(Topic::getTopicTypeId,input.getTopicTypeId());
        }

        //如果前端搜索传入AuditStatus不为空,则进行精确查询
        if(input.getAuditStatus()!=null) {
            queryWrapper=queryWrapper.eq(Topic::getAuditStatus,input.getAuditStatus());
        }
        //如果前端搜索传入IsRecommand不为空,则进行精确查询
        if(input.getIsRecommand()!=null) {
            queryWrapper=queryWrapper.eq(Topic::getIsRecommand,input.getIsRecommand());
        }

        //按创建时间从大到小排序 最新的显示在最前面
        if(input.getIsHot()!=null&&input.getIsHot()) {
            queryWrapper=queryWrapper.orderByDesc(Topic::getViewCount);
        }
        else {

            queryWrapper = queryWrapper.orderByDesc(Topic::getCreationTime);
        }
        //构建一个分页查询的model
        Page<Topic> page = new Page<>(input.getPage(), input.getLimit());


        //从数据库进行分页查询获取话题数据
        IPage<Topic> pageRecords= _TopicMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _TopicMpper.selectCount(queryWrapper);

        //把Topic实体转换成Topic传输模型
        List<TopicDto> items= Extension.copyBeanList(pageRecords.getRecords(),TopicDto.class);
        for (TopicDto item : items) {

            //查询出关联的创建用户信息
            AppUserDto  CreatorAppUserDTO=new AppUserDto();
            AppUser  CreatorAppUserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getCreatorId()));
            if(CreatorAppUserEntity!=null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }
            //查询关联的Comment表信息
            List<Comment> CommentEntitys = _CommentMapper.selectList(Wrappers.<Comment>lambdaQuery().eq(Comment::getTopicId, item.getId()));
            List<CommentDto> CommentDTOS = Extension.copyBeanList(CommentEntitys, CommentDto.class);
            item.setCommentDtos(CommentDTOS);



            //查询出关联的TopicType表信息
            TopicTypeDto TopicTypeDTO = new TopicTypeDto();
            TopicType  TopicTypeEntity= _TopicTypeMapper.selectOne(Wrappers.<TopicType>lambdaQuery().eq(TopicType::getId,item.getTopicTypeId()));
            if(TopicTypeEntity!=null) {
                BeanUtils.copyProperties(TopicTypeDTO,TopicTypeEntity);
                item.setTopicTypeDto(TopicTypeDTO);
            }

        }

        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }

    /**
     * 单个话题查询
     */
    @SneakyThrows
    @Override
    public TopicDto Get(TopicPagedInput input) {
        if(input.getId()==null)
        {
            return new TopicDto();
        }
        PagedResult<TopicDto>  pagedResult =List(input);
        UpdateViewCount(input.getId());
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new TopicDto();
    }

    /**
     *话题创建或者修改
     */
    @SneakyThrows
    @Override
    public TopicDto CreateOrEdit(TopicDto input) {

        //声明一个话题实体
        Topic Topic = new Topic();

        //把前端传入的input参数拷贝到话题实体
        BeanUtils.copyProperties(Topic,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(Topic);

        //定义一个返回给前端的话题传输模型
        TopicDto TopicDto=new TopicDto();

        //同理把操作的话题实体拷贝给话题传输模型
        BeanUtils.copyProperties(TopicDto,Topic);

        //把传输模型返回给前端
        return TopicDto;
    }
    /**
     * 修改话题浏览次数
     */
    @SneakyThrows
    private void UpdateViewCount(Integer id) {

        Topic topic = _TopicMpper.selectById(id);
        if (topic != null) {
            if (topic.getViewCount() == null) {
                topic.setViewCount(0);
            }
            topic.setViewCount(topic.getViewCount()+1);
            _TopicMpper.updateById(topic);
        }

    }



}
