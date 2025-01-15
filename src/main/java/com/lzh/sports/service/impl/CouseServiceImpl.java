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
 * 课程功能实现类
 */
@Service
public class CouseServiceImpl extends ServiceImpl<CouseMapper, Couse> implements CouseService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的Couse表mapper对象
     */
    @Autowired
    private CouseMapper _CouseMpper;
    /**
     * 操作数据库的CouseType表mapper对象
     */
    @Autowired
    private CouseTypeMapper _CouseTypeMpper;


    /**
     * 课程分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<CouseDto> List(CousePagedInput input) {

      
        //声明一个支持课程查询的(拉姆达)表达式
        LambdaQueryWrapper<Couse> queryWrapper = Wrappers.<Couse>lambdaQuery()
                .eq(input.getId()!=null&&input.getId()!=0,Couse::getId,input.getId())
                .eq(input.getCreatorId()!=null,Couse::getCreatorId,input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件

        if(Extension.isNotNullOrEmpty(input.getName())) {
            queryWrapper=queryWrapper.eq(Couse::getName,input.getName());
       	 }
        if(input.getCouseTypeId()!=null) {
            queryWrapper=queryWrapper.eq(Couse::getCouseTypeId,input.getCouseTypeId());
       	 }

        if(Extension.isNotNullOrEmpty(input.getNotes())) {
            queryWrapper=queryWrapper.eq(Couse::getNotes,input.getNotes());
       	 }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(Couse::getCreationTime);
        //构建一个分页查询的model
        Page<Couse> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取课程数据
        IPage<Couse> pageRecords= _CouseMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _CouseMpper.selectCount(queryWrapper);

        //把Couse实体转换成Couse传输模型
        List<CouseDto> items= Extension.copyBeanList(pageRecords.getRecords(),CouseDto.class);
        for (CouseDto item : items) {
          
          //查询出关联的创建用户信息
            AppUserDto  CreatorAppUserDTO=new AppUserDto();
            AppUser  CreatorAppUserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getCreatorId()));
           if(CreatorAppUserEntity!=null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }  

           //查询课程类型
            CouseTypeDto  CouseTypeDTO=new CouseTypeDto();
            CouseType  CouseTypeEntity= _CouseTypeMpper.selectOne(Wrappers.<CouseType>lambdaQuery().eq(CouseType::getId,item.getCouseTypeId()));
              if(CouseTypeEntity!=null) {
                 BeanUtils.copyProperties(CouseTypeDTO, CouseTypeEntity);
                 item.setCouseTypeDto(CouseTypeDTO);
                }
        }
      
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个课程查询
     */
    @SneakyThrows
    @Override
    public CouseDto Get(CousePagedInput input) {
       if(input.getId()==null)
        {
            return new CouseDto();
        }
        PagedResult<CouseDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new CouseDto();
    }

    /**
     *课程创建或者修改
     */
    @SneakyThrows
    @Override
    public CouseDto CreateOrEdit(CouseDto input) {

        //声明一个课程实体
        Couse Couse = new Couse();

        //把前端传入的input参数拷贝到课程实体
        BeanUtils.copyProperties(Couse,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(Couse);

        //定义一个返回给前端的课程传输模型
        CouseDto CouseDto=new CouseDto();

        //同理把操作的课程实体拷贝给课程传输模型
        BeanUtils.copyProperties(CouseDto,Couse);

        //把传输模型返回给前端
        return CouseDto;
    }



}
