package com.lzh.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzh.sports.dto.AppUserDto;
import com.lzh.sports.dto.TopicCollectDto;
import com.lzh.sports.dto.TopicDto;
import com.lzh.sports.dto.query.TopicCollectPagedInput;
import com.lzh.sports.entity.AppUser;
import com.lzh.sports.entity.Topic;
import com.lzh.sports.entity.TopicCollect;
import com.lzh.sports.mapper.AppUserMapper;
import com.lzh.sports.mapper.TopicCollectMapper;
import com.lzh.sports.mapper.TopicMapper;
import com.lzh.sports.service.TopicCollectService;
import com.lzh.sports.tools.Extension;
import com.lzh.sports.tools.dto.PagedResult;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 话题收藏功能实现类
 */
@Service
public class TopicCollectServiceImpl extends ServiceImpl<TopicCollectMapper, TopicCollect> implements TopicCollectService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的TopicCollect表mapper对象
     */
    @Autowired
    private TopicCollectMapper _TopicCollectMpper;

 			
    @Autowired
    private TopicMapper _TopicMapper;                        
			
 			
			

    /**
     * 话题收藏分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<TopicCollectDto> List(TopicCollectPagedInput input) {

      
        //声明一个支持话题收藏查询的(拉姆达)表达式
        LambdaQueryWrapper<TopicCollect> queryWrapper = Wrappers.<TopicCollect>lambdaQuery()
                .eq(input.getId()!=null&&input.getId()!=0,TopicCollect::getId,input.getId())
                .eq(input.getCreatorId()!=null,TopicCollect::getCreatorId,input.getCreatorId());
      
     	 //如果前端搜索传入TopicId不为空,则进行精确查询
        if(input.getTopicId()!=null) {
            queryWrapper=queryWrapper.eq(TopicCollect::getTopicId,input.getTopicId());
       	 }
     	 //如果前端搜索传入UserId不为空,则进行精确查询
        if(input.getUserId()!=null) {
            queryWrapper=queryWrapper.eq(TopicCollect::getUserId,input.getUserId());
       	 }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(TopicCollect::getCreationTime);
        //构建一个分页查询的model
        Page<TopicCollect> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取话题收藏数据
        IPage<TopicCollect> pageRecords= _TopicCollectMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _TopicCollectMpper.selectCount(queryWrapper);

        //把TopicCollect实体转换成TopicCollect传输模型
        List<TopicCollectDto> items= Extension.copyBeanList(pageRecords.getRecords(),TopicCollectDto.class);
        for (TopicCollectDto item : items) {
          
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
          
        }
      
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个话题收藏查询
     */
    @SneakyThrows
    @Override
    public TopicCollectDto Get(TopicCollectPagedInput input) {
       if(input.getId()==null)
        {
            return new TopicCollectDto();
        }
        PagedResult<TopicCollectDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new TopicCollectDto();
    }

    /**
     *话题收藏创建或者修改
     */
    @SneakyThrows
    @Override
    public TopicCollectDto CreateOrEdit(TopicCollectDto input) {

        //声明一个话题收藏实体
        TopicCollect TopicCollect = new TopicCollect();

        //把前端传入的input参数拷贝到话题收藏实体
        BeanUtils.copyProperties(TopicCollect,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(TopicCollect);

        //定义一个返回给前端的话题收藏传输模型
        TopicCollectDto TopicCollectDto=new TopicCollectDto();

        //同理把操作的话题收藏实体拷贝给话题收藏传输模型
        BeanUtils.copyProperties(TopicCollectDto,TopicCollect);

        //把传输模型返回给前端
        return TopicCollectDto;
    }



}
