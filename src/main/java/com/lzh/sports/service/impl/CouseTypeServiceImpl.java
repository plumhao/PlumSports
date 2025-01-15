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
 * 课程类型功能实现类
 */
@Service
public class CouseTypeServiceImpl extends ServiceImpl<CouseTypeMapper, CouseType> implements CouseTypeService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的CouseType表mapper对象
     */
    @Autowired
    private CouseTypeMapper _CouseTypeMpper;

 			
  	 @Autowired
    private CouseMapper _CouseMapper;   
			

    /**
     * 课程类型分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<CouseTypeDto> List(CouseTypePagedInput input) {

      
        //声明一个支持课程类型查询的(拉姆达)表达式
        LambdaQueryWrapper<CouseType> queryWrapper = Wrappers.<CouseType>lambdaQuery()
                .eq(input.getId()!=null&&input.getId()!=0,CouseType::getId,input.getId())
                .eq(input.getCreatorId()!=null,CouseType::getCreatorId,input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件

        if(Extension.isNotNullOrEmpty(input.getName())) {
            queryWrapper=queryWrapper.eq(CouseType::getName,input.getName());
       	 }
         if(input.getSortStartRange()!=null)
          {
            queryWrapper=queryWrapper.gt(CouseType::getSort,input.getSortStartRange());
          }
      		 if(input.getSortEndRange()!=null)
          {
            queryWrapper=queryWrapper.lt(CouseType::getSort,input.getSortEndRange());
          }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(CouseType::getCreationTime);
        //构建一个分页查询的model
        Page<CouseType> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取课程类型数据
        IPage<CouseType> pageRecords= _CouseTypeMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _CouseTypeMpper.selectCount(queryWrapper);

        //把CouseType实体转换成CouseType传输模型
        List<CouseTypeDto> items= Extension.copyBeanList(pageRecords.getRecords(),CouseTypeDto.class);
        for (CouseTypeDto item : items) {
          
          //查询出关联的创建用户信息
            AppUserDto  CreatorAppUserDTO=new AppUserDto();
            AppUser  CreatorAppUserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getCreatorId()));
           if(CreatorAppUserEntity!=null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }  
         

            //查询关联的Couse表信息
            List<Couse>  CouseEntitys= _CouseMapper.selectList(Wrappers.<Couse>lambdaQuery().eq(Couse::getCouseTypeId,item.getId()));
            List<CouseDto> CouseDTOS= Extension.copyBeanList(CouseEntitys,CouseDto.class);
            item.setCouseDtos(CouseDTOS);
         
        }
      
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个课程类型查询
     */
    @SneakyThrows
    @Override
    public CouseTypeDto Get(CouseTypePagedInput input) {
       if(input.getId()==null)
        {
            return new CouseTypeDto();
        }
        PagedResult<CouseTypeDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new CouseTypeDto();
    }

    /**
     *课程类型创建或者修改
     */
    @SneakyThrows
    @Override
    public CouseTypeDto CreateOrEdit(CouseTypeDto input) {

        //声明一个课程类型实体
        CouseType CouseType = new CouseType();

        //把前端传入的input参数拷贝到课程类型实体
        BeanUtils.copyProperties(CouseType,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(CouseType);

        //定义一个返回给前端的课程类型传输模型
        CouseTypeDto CouseTypeDto=new CouseTypeDto();

        //同理把操作的课程类型实体拷贝给课程类型传输模型
        BeanUtils.copyProperties(CouseTypeDto,CouseType);

        //把传输模型返回给前端
        return CouseTypeDto;
    }



}
