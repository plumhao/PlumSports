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
 * 话题类型功能实现类
 */
@Service
public class TopicTypeServiceImpl extends ServiceImpl<TopicTypeMapper, TopicType> implements TopicTypeService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的TopicType表mapper对象
     */
    @Autowired
    private TopicTypeMapper _TopicTypeMpper;

 			
  	 @Autowired
    private TopicMapper _TopicMapper;   
			

    /**
     * 话题类型分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<TopicTypeDto> List(TopicTypePagedInput input) {

      
        //声明一个支持话题类型查询的(拉姆达)表达式
        LambdaQueryWrapper<TopicType> queryWrapper = Wrappers.<TopicType>lambdaQuery()
                .eq(input.getId()!=null,TopicType::getId,input.getId())
                .eq(input.getCreatorId()!=null,TopicType::getCreatorId,input.getCreatorId());
      
     	 //如果前端搜索传入Name不为空,则进行精确查询
        if(Extension.isNotNullOrEmpty(input.getName())) {
            queryWrapper=queryWrapper.eq(TopicType::getName,input.getName());
       	 }
     	 //如果前端搜索传入Code不为空,则进行精确查询
        if(Extension.isNotNullOrEmpty(input.getCode())) {
            queryWrapper=queryWrapper.eq(TopicType::getCode,input.getCode());
       	 }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(TopicType::getCreationTime);
        //构建一个分页查询的model
        Page<TopicType> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取话题类型数据
        IPage<TopicType> pageRecords= _TopicTypeMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _TopicTypeMpper.selectCount(queryWrapper);

        //把TopicType实体转换成TopicType传输模型
        List<TopicTypeDto> items= Extension.copyBeanList(pageRecords.getRecords(),TopicTypeDto.class);
        for (TopicTypeDto item : items) {
          
          //查询出关联的创建用户信息
            AppUserDto  CreatorAppUserDTO=new AppUserDto();
            AppUser  CreatorAppUserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getCreatorId()));
           if(CreatorAppUserEntity!=null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }  
         

            //查询关联的Topic表信息
            List<Topic>  TopicEntitys= _TopicMapper.selectList(Wrappers.<Topic>lambdaQuery().eq(Topic::getTopicTypeId,item.getId()));
            List<TopicDto> TopicDTOS= Extension.copyBeanList(TopicEntitys,TopicDto.class);
            item.setTopicDtos(TopicDTOS);
         
        }
      
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个话题类型查询
     */
    @SneakyThrows
    @Override
    public TopicTypeDto Get(TopicTypePagedInput input) {
       if(input.getId()==null)
        {
            return new TopicTypeDto();
        }
        PagedResult<TopicTypeDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new TopicTypeDto();
    }

    /**
     *话题类型创建或者修改
     */
    @SneakyThrows
    @Override
    public TopicTypeDto CreateOrEdit(TopicTypeDto input) {

        //声明一个话题类型实体
        TopicType TopicType = new TopicType();

        //把前端传入的input参数拷贝到话题类型实体
        BeanUtils.copyProperties(TopicType,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(TopicType);

        //定义一个返回给前端的话题类型传输模型
        TopicTypeDto TopicTypeDto=new TopicTypeDto();

        //同理把操作的话题类型实体拷贝给话题类型传输模型
        BeanUtils.copyProperties(TopicTypeDto,TopicType);

        //把传输模型返回给前端
        return TopicTypeDto;
    }



}
