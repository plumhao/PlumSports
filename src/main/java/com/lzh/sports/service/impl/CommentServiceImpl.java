package com.lzh.sports.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.mapper.*;
import com.lzh.sports.service.*;
import com.lzh.sports.tools.dto.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.SneakyThrows;
import com.lzh.sports.tools.*;
/**
 * 评论功能实现类
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的Comment表mapper对象
     */
    @Autowired
    private CommentMapper _CommentMpper;

 			
    @Autowired
    private TopicMapper _TopicMapper;

    /**
     * 操作数据库的CommentLove表mapper对象
     */
    @Autowired
    private CommentLoveMapper _CommentLoveMpper;

    /**
     * 评论分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<CommentDto> List(CommentPagedInput input) {




        //声明一个支持评论查询的(拉姆达)表达式
        LambdaQueryWrapper<Comment> queryWrapper = Wrappers.<Comment>lambdaQuery()
                .eq(input.getId()!=null,Comment::getId,input.getId())
                .eq(input.getCreatorId()!=null,Comment::getCreatorId,input.getCreatorId());
      
     	 //如果前端搜索传入TopicId不为空,则进行精确查询
        if(input.getTopicId()!=null) {
            queryWrapper=queryWrapper.eq(Comment::getTopicId,input.getTopicId());
       	 }
     	 //如果前端搜索传入NickName不为空,则进行精确查询
        if(Extension.isNotNullOrEmpty(input.getNickName())) {
            queryWrapper=queryWrapper.eq(Comment::getNickName,input.getNickName());
       	 }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(Comment::getCreationTime);
        //构建一个分页查询的model
        Page<Comment> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取评论数据
        IPage<Comment> pageRecords= _CommentMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _CommentMpper.selectCount(queryWrapper);

        //把Comment实体转换成Comment传输模型
        List<CommentDto> items= Extension.copyBeanList(pageRecords.getRecords(),CommentDto.class);

        for (CommentDto item : items) {




          //查询出关联的创建用户信息
            AppUserDto  CreatorAppUserDTO=new AppUserDto();
            AppUser  CreatorAppUserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getCreatorId()));
           if(CreatorAppUserEntity!=null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }  
         
           
          	            
           //查询出关联的Topic表信息
           TopicDto TopicDTO = new TopicDto();        
            Topic  TopicEntity= _TopicMapper.selectOne(Wrappers.<Topic>lambdaQuery().eq(Topic::getId,item.getTopicId()));
           if(TopicEntity!=null) {  
          BeanUtils.copyProperties(TopicDTO,TopicEntity);
            item.setTopicDto(TopicDTO); 
           }
          
        }
      
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }


    /**
     * 根评论
     */
    @SneakyThrows
    @Override
    public PagedResult<CommentDto> RootList(CommentPagedInput input) {

        CurrentUserDto currentUserDto = BaseContext.getCurrentUserDto();
        //只查询根评论的并且大于0
        LambdaQueryWrapper<Comment> queryWrapper = Wrappers.<Comment>lambdaQuery()
                .eq(input.getId()!=null,Comment::getId,input.getId())
                .eq(input.getCreatorId()!=null,Comment::getCreatorId,input.getCreatorId())
                .eq(Comment::getRootCommentId,0);


        //如果前端搜索传入TopicId不为空,则进行精确查询
        if(input.getTopicId()!=null) {
            queryWrapper=queryWrapper.eq(Comment::getTopicId,input.getTopicId());
        }
        //如果前端搜索传入NickName不为空,则进行精确查询
        if(Extension.isNotNullOrEmpty(input.getNickName())) {
            queryWrapper=queryWrapper.eq(Comment::getNickName,input.getNickName());
        }

        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(Comment::getCreationTime);
        //构建一个分页查询的model
        Page<Comment> page = new Page<>(input.getPage(), input.getLimit());

        //从数据库进行分页查询获取评论数据
        IPage<Comment> pageRecords= _CommentMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _CommentMpper.selectCount(queryWrapper);

        //把Comment实体转换成Comment传输模型
        List<CommentDto> items= Extension.copyBeanList(pageRecords.getRecords(),CommentDto.class);


        //查询出关联为根评论的子评论
        List<Integer> rootIds = items.stream().map(x -> x.getId()).distinct().toList();
        List<Comment> childComments =new ArrayList<>();

        if(rootIds.stream().count()>0) {
            //查询出所有的子评论
            childComments = _CommentMpper.selectList(Wrappers.<Comment>lambdaQuery().in(Comment::getRootCommentId, rootIds));
        }
        //子评论转换成dto
        List<CommentDto> childCommentDtos = Extension.copyBeanList(childComments, CommentDto.class);
        //得到根评论的所有用户id
        ArrayList<Integer> userIds =  new ArrayList<Integer>(items.stream().map(x -> x.getCreatorId()).distinct().toList()); ;

        //得到所有子评论的用户id
        ArrayList<Integer> childCommentUserIds =  new ArrayList<Integer>(childComments.stream().map(x -> x.getCreatorId()).distinct().toList()); ;

        if(childCommentUserIds.stream().count()>0){
            userIds.addAll(childCommentUserIds);

        }

        //得到所有的用户信息并且转换成Dto
        List<AppUser> appUsers=new ArrayList<>();
        if(userIds.stream().count()>0) {
            appUsers = _AppUserMapper.selectList(Wrappers.<AppUser>lambdaQuery().in(AppUser::getId, userIds));
        }
        List<AppUserDto> appUserDtos = Extension.copyBeanList(appUsers, AppUserDto.class);


        for (CommentDto item : items) {
            //查询该评论的所有点赞数
            Long loveCount = _CommentLoveMpper.selectCount(Wrappers.<CommentLove>lambdaQuery().eq(CommentLove::getCommentId, item.getId()));
            item.setLikeCount(loveCount.intValue());
            if(currentUserDto!=null&&currentUserDto.getUserId()!=null)
            {
                //查询当前用户是否在这个评论进行了点赞
                CommentLove  commentLove= _CommentLoveMpper.selectOne(Wrappers.<CommentLove>lambdaQuery().eq(CommentLove::getCommentId,item.getId()).eq(CommentLove::getUserId,currentUserDto.getUserId()));
                if(commentLove!=null)
                {
                    item.setIsLove(true);
                }
            }

            Optional<AppUserDto> CreatorAppUserDTO = appUserDtos.stream().filter(x -> x.getId().equals(item.getCreatorId())).findFirst();

            if (CreatorAppUserDTO.isEmpty()==false) {
                item.setCreatorAppUserDto(CreatorAppUserDTO.get());
                item.setSendUserDto(CreatorAppUserDTO.get());
            }
            //查询关联下面的子评论
            List<CommentDto> childCommentDto = new ArrayList<>();
            if (childCommentDtos.stream().count() > 0)
            {
             childCommentDto = childCommentDtos.stream().filter(x -> x.getRootCommentId().equals(item.getId())).toList();

           }

            item.setChildren(childCommentDto);

            //循环子评论
            for (CommentDto child : childCommentDto) {
                //查询该评论的所有点赞数
                Long childLoveCount = _CommentLoveMpper.selectCount(Wrappers.<CommentLove>lambdaQuery().eq(CommentLove::getCommentId, child.getId()));
                child.setLikeCount(childLoveCount.intValue());
                if(currentUserDto!=null&&currentUserDto.getUserId()!=null)
                {
                    //查询当前用户是否在这个评论进行了点赞
                    CommentLove  commentLove= _CommentLoveMpper.selectOne(Wrappers.<CommentLove>lambdaQuery().eq(CommentLove::getCommentId,child.getId()).eq(CommentLove::getUserId,currentUserDto.getUserId()));
                    if(commentLove!=null)
                    {
                        child.setIsLove(true);
                    }
                }
                //设置发送人
                Optional<AppUserDto> SendUserDto = appUserDtos.stream().filter(x -> x.getId().equals(child.getSendUserId())).findFirst();
                if(SendUserDto.isEmpty()==false){
                    child.setSendUserDto(SendUserDto.get());
                }
                //设置接受人
                Optional<AppUserDto> TargetUserDto = appUserDtos.stream().filter(x -> x.getId().equals(child.getTargetUserId())).findFirst();
                if(TargetUserDto.isEmpty()==false){
                    child.setTargetUserDto(TargetUserDto.get());
                }


            }



            //查询出关联的Topic表信息
            TopicDto TopicDTO = new TopicDto();
            Topic  TopicEntity= _TopicMapper.selectOne(Wrappers.<Topic>lambdaQuery().eq(Topic::getId,item.getTopicId()));
            if(TopicEntity!=null) {
                BeanUtils.copyProperties(TopicDTO,TopicEntity);
                item.setTopicDto(TopicDTO);
            }

        }

        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }




    /**
     * 单个评论查询
     */
    @SneakyThrows
    @Override
    public CommentDto Get(CommentPagedInput input) {
       if(input.getId()==null)
        {
            return new CommentDto();
        }
        PagedResult<CommentDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new CommentDto();
    }

    /**
     *评论创建或者修改
     */
    @SneakyThrows
    @Override
    public CommentDto CreateOrEdit(CommentDto input) {

        //声明一个评论实体
        Comment Comment = new Comment();

        //把前端传入的input参数拷贝到评论实体
        BeanUtils.copyProperties(Comment,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(Comment);

        //定义一个返回给前端的评论传输模型
        CommentDto CommentDto=new CommentDto();

        //同理把操作的评论实体拷贝给评论传输模型
        BeanUtils.copyProperties(CommentDto,Comment);

        //把传输模型返回给前端
        return CommentDto;
    }



}
