package com.lzh.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzh.sports.dto.AppUserDto;
import com.lzh.sports.dto.TopicDto;
import com.lzh.sports.dto.TopicRecordDto;
import com.lzh.sports.dto.TopicTypeDto;
import com.lzh.sports.dto.query.TopicRecordPagedInput;
import com.lzh.sports.entity.AppUser;
import com.lzh.sports.entity.Topic;
import com.lzh.sports.entity.TopicRecord;
import com.lzh.sports.entity.TopicType;
import com.lzh.sports.mapper.AppUserMapper;
import com.lzh.sports.mapper.TopicMapper;
import com.lzh.sports.mapper.TopicRecordMapper;
import com.lzh.sports.mapper.TopicTypeMapper;
import com.lzh.sports.service.TopicRecordService;
import com.lzh.sports.tools.Extension;
import com.lzh.sports.tools.dto.PagedResult;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * 话题浏览记录功能实现类
 */
@Service
public class TopicRecordServiceImpl extends ServiceImpl<TopicRecordMapper, TopicRecord> implements TopicRecordService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的TopicRecord表mapper对象
     */
    @Autowired
    private TopicRecordMapper _TopicRecordMpper;

 			
			
 			
    @Autowired
    private TopicTypeMapper _TopicTypeMapper;                        
			
 			
    @Autowired
    private TopicMapper _TopicMapper;                        
			

    /**
     * 话题浏览记录分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<TopicRecordDto> List(TopicRecordPagedInput input) {

      
        //声明一个支持话题浏览记录查询的(拉姆达)表达式
        LambdaQueryWrapper<TopicRecord> queryWrapper = Wrappers.<TopicRecord>lambdaQuery()
                .eq(input.getId()!=null&&input.getId()!=0,TopicRecord::getId,input.getId())
                .eq(input.getCreatorId()!=null,TopicRecord::getCreatorId,input.getCreatorId());
      
     	 //如果前端搜索传入TopicTypeId不为空,则进行精确查询
        if(input.getTopicTypeId()!=null) {
            queryWrapper=queryWrapper.eq(TopicRecord::getTopicTypeId,input.getTopicTypeId());
       	 }
     	 //如果前端搜索传入TopicId不为空,则进行精确查询
        if(input.getTopicId()!=null) {
            queryWrapper=queryWrapper.eq(TopicRecord::getTopicId,input.getTopicId());
       	 }
     	 //如果前端搜索传入UserId不为空,则进行精确查询
        if(input.getUserId()!=null) {
            queryWrapper=queryWrapper.eq(TopicRecord::getUserId,input.getUserId());
       	 }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(TopicRecord::getCreationTime);
        //构建一个分页查询的model
        Page<TopicRecord> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取话题浏览记录数据
        IPage<TopicRecord> pageRecords= _TopicRecordMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _TopicRecordMpper.selectCount(queryWrapper);

        //把TopicRecord实体转换成TopicRecord传输模型
        List<TopicRecordDto> items= Extension.copyBeanList(pageRecords.getRecords(),TopicRecordDto.class);
        for (TopicRecordDto item : items) {
          
          //查询出关联的创建用户信息
            AppUserDto  CreatorAppUserDTO=new AppUserDto();
            AppUser  CreatorAppUserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getCreatorId()));
           if(CreatorAppUserEntity!=null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }  
         
           
          	            
           //查询出关联的AppUser表信息
           AppUserDto UserDTO = new AppUserDto();        
            AppUser  UserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getUserId()));
           if(UserEntity!=null) {  
          BeanUtils.copyProperties(UserDTO,UserEntity);
            item.setUserDto(UserDTO); 
           }
          
           
          	            
           //查询出关联的TopicType表信息
           TopicTypeDto TopicTypeDTO = new TopicTypeDto();        
            TopicType  TopicTypeEntity= _TopicTypeMapper.selectOne(Wrappers.<TopicType>lambdaQuery().eq(TopicType::getId,item.getTopicTypeId()));
           if(TopicTypeEntity!=null) {  
          BeanUtils.copyProperties(TopicTypeDTO,TopicTypeEntity);
            item.setTopicTypeDto(TopicTypeDTO); 
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
     * 单个话题浏览记录查询
     */
    @SneakyThrows
    @Override
    public TopicRecordDto Get(TopicRecordPagedInput input) {
       if(input.getId()==null)
        {
            return new TopicRecordDto();
        }
        PagedResult<TopicRecordDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new TopicRecordDto();
    }

    /**
     *话题浏览记录创建或者修改
     */
    @SneakyThrows
    @Override
    public TopicRecordDto CreateOrEdit(TopicRecordDto input) {

        //声明一个话题浏览记录实体
        TopicRecord TopicRecord = new TopicRecord();

        //把前端传入的input参数拷贝到话题浏览记录实体
        BeanUtils.copyProperties(TopicRecord,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(TopicRecord);

        //定义一个返回给前端的话题浏览记录传输模型
        TopicRecordDto TopicRecordDto=new TopicRecordDto();

        //同理把操作的话题浏览记录实体拷贝给话题浏览记录传输模型
        BeanUtils.copyProperties(TopicRecordDto,TopicRecord);

        //把传输模型返回给前端
        return TopicRecordDto;
    }



}
