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
 * 器材设备功能实现类
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的Equipment表mapper对象
     */
    @Autowired
    private EquipmentMapper _EquipmentMpper;


    /**
     * 器材设备分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<EquipmentDto> List(EquipmentPagedInput input) {

      
        //声明一个支持器材设备查询的(拉姆达)表达式
        LambdaQueryWrapper<Equipment> queryWrapper = Wrappers.<Equipment>lambdaQuery()
                .eq(input.getId()!=null&&input.getId()!=0,Equipment::getId,input.getId())
                .eq(input.getCreatorId()!=null,Equipment::getCreatorId,input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件

        if(Extension.isNotNullOrEmpty(input.getNo())) {
            queryWrapper=queryWrapper.eq(Equipment::getNo,input.getNo());
       	 }

        if(Extension.isNotNullOrEmpty(input.getName())) {
            queryWrapper=queryWrapper.eq(Equipment::getName,input.getName());
       	 }

        if(Extension.isNotNullOrEmpty(input.getType())) {
            queryWrapper=queryWrapper.eq(Equipment::getType,input.getType());
       	 }

        if(Extension.isNotNullOrEmpty(input.getBrand())) {
            queryWrapper=queryWrapper.eq(Equipment::getBrand,input.getBrand());
       	 }


        if(input.getEquipmentStatus()!=null) {
            queryWrapper=queryWrapper.eq(Equipment::getEquipmentStatus,input.getEquipmentStatus());
       	 }

        if(Extension.isNotNullOrEmpty(input.getLocation())) {
            queryWrapper=queryWrapper.eq(Equipment::getLocation,input.getLocation());
       	 }

        if(Extension.isNotNullOrEmpty(input.getInstructions())) {
            queryWrapper=queryWrapper.eq(Equipment::getInstructions,input.getInstructions());
       	 }

        if(Extension.isNotNullOrEmpty(input.getNotes())) {
            queryWrapper=queryWrapper.eq(Equipment::getNotes,input.getNotes());
       	 }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(Equipment::getCreationTime);
        //构建一个分页查询的model
        Page<Equipment> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取器材设备数据
        IPage<Equipment> pageRecords= _EquipmentMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _EquipmentMpper.selectCount(queryWrapper);

        //把Equipment实体转换成Equipment传输模型
        List<EquipmentDto> items= Extension.copyBeanList(pageRecords.getRecords(),EquipmentDto.class);
        for (EquipmentDto item : items) {
          
          //查询出关联的创建用户信息
            AppUserDto  CreatorAppUserDTO=new AppUserDto();
            AppUser  CreatorAppUserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getCreatorId()));
           if(CreatorAppUserEntity!=null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }  
         
        }
      
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个器材设备查询
     */
    @SneakyThrows
    @Override
    public EquipmentDto Get(EquipmentPagedInput input) {
       if(input.getId()==null)
        {
            return new EquipmentDto();
        }
        PagedResult<EquipmentDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new EquipmentDto();
    }

    /**
     *器材设备创建或者修改
     */
    @SneakyThrows
    @Override
    public EquipmentDto CreateOrEdit(EquipmentDto input) {

        //声明一个器材设备实体
        Equipment Equipment = new Equipment();

        //把前端传入的input参数拷贝到器材设备实体
        BeanUtils.copyProperties(Equipment,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(Equipment);

        //定义一个返回给前端的器材设备传输模型
        EquipmentDto EquipmentDto=new EquipmentDto();

        //同理把操作的器材设备实体拷贝给器材设备传输模型
        BeanUtils.copyProperties(EquipmentDto,Equipment);

        //把传输模型返回给前端
        return EquipmentDto;
    }



}
