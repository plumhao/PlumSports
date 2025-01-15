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

import java.util.List;
import lombok.SneakyThrows;
import com.lzh.sports.tools.*;
/**
 * 评论点赞记录功能实现类
 */
@Service
public class CommentLoveServiceImpl extends ServiceImpl<CommentLoveMapper, CommentLove> implements CommentLoveService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的CommentLove表mapper对象
     */
    @Autowired
    private CommentLoveMapper _CommentLoveMpper;

 			
    @Autowired
    private TopicMapper _TopicMapper;                        
			
 			
			
 			
    @Autowired
    private CommentMapper _CommentMapper;                        
			

    /**
     * 评论点赞记录分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<CommentLoveDto> List(CommentLovePagedInput input) {

      
        //声明一个支持评论点赞记录查询的(拉姆达)表达式
        LambdaQueryWrapper<CommentLove> queryWrapper = Wrappers.<CommentLove>lambdaQuery()
                .eq(input.getId()!=null&&input.getId()!=0,CommentLove::getId,input.getId())
                .eq(input.getCreatorId()!=null,CommentLove::getCreatorId,input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件
        if(input.getTopicId()!=null) {
            queryWrapper=queryWrapper.eq(CommentLove::getTopicId,input.getTopicId());
       	 }
        if(input.getCommentId()!=null) {
            queryWrapper=queryWrapper.eq(CommentLove::getCommentId,input.getCommentId());
       	 }
        if(input.getUserId()!=null) {
            queryWrapper=queryWrapper.eq(CommentLove::getUserId,input.getUserId());
       	 }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(CommentLove::getCreationTime);
        //构建一个分页查询的model
        Page<CommentLove> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取评论点赞记录数据
        IPage<CommentLove> pageRecords= _CommentLoveMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _CommentLoveMpper.selectCount(queryWrapper);

        //把CommentLove实体转换成CommentLove传输模型
        List<CommentLoveDto> items= Extension.copyBeanList(pageRecords.getRecords(),CommentLoveDto.class);
        for (CommentLoveDto item : items) {
          
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
          
           
          	            
           //查询出关联的AppUser表信息
           AppUserDto UserDTO = new AppUserDto();        
            AppUser  UserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getUserId()));
           if(UserEntity!=null) {  
          BeanUtils.copyProperties(UserDTO,UserEntity);
            item.setUserDto(UserDTO); 
           }
          
           
          	            
           //查询出关联的Comment表信息
           CommentDto CommentDTO = new CommentDto();        
            Comment  CommentEntity= _CommentMapper.selectOne(Wrappers.<Comment>lambdaQuery().eq(Comment::getId,item.getCommentId()));
           if(CommentEntity!=null) {  
          BeanUtils.copyProperties(CommentDTO,CommentEntity);
            item.setCommentDto(CommentDTO); 
           }
          
        }
      
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个评论点赞记录查询
     */
    @SneakyThrows
    @Override
    public CommentLoveDto Get(CommentLovePagedInput input) {
       if(input.getId()==null)
        {
            return new CommentLoveDto();
        }
        PagedResult<CommentLoveDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new CommentLoveDto();
    }

    /**
     *评论点赞记录创建或者修改
     */
    @SneakyThrows
    @Override
    public CommentLoveDto CreateOrEdit(CommentLoveDto input) {

        //声明一个评论点赞记录实体
        CommentLove CommentLove = new CommentLove();

        //把前端传入的input参数拷贝到评论点赞记录实体
        BeanUtils.copyProperties(CommentLove,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(CommentLove);

        //定义一个返回给前端的评论点赞记录传输模型
        CommentLoveDto CommentLoveDto=new CommentLoveDto();

        //同理把操作的评论点赞记录实体拷贝给评论点赞记录传输模型
        BeanUtils.copyProperties(CommentLoveDto,CommentLove);

        //把传输模型返回给前端
        return CommentLoveDto;
    }



}
