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
 * 留言功能实现类
 */
@Service
public class LeaveMessageServiceImpl extends ServiceImpl<LeaveMessageMapper, LeaveMessage> implements LeaveMessageService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的LeaveMessage表mapper对象
     */
    @Autowired
    private LeaveMessageMapper _LeaveMessageMpper;

 			
			
 			
			

    /**
     * 留言分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<LeaveMessageDto> List(LeaveMessagePagedInput input) {

      
        //声明一个支持留言查询的(拉姆达)表达式
        LambdaQueryWrapper<LeaveMessage> queryWrapper = Wrappers.<LeaveMessage>lambdaQuery()
                .eq(input.getId()!=null&&input.getId()!=0,LeaveMessage::getId,input.getId())
                .eq(input.getCreatorId()!=null,LeaveMessage::getCreatorId,input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件
        if(input.getUserId()!=null) {
            queryWrapper=queryWrapper.eq(LeaveMessage::getUserId,input.getUserId());
       	 }
        if(input.getProcessingStatus()!=null) {
            queryWrapper=queryWrapper.eq(LeaveMessage::getProcessingStatus,input.getProcessingStatus());
       	 }

        if(Extension.isNotNullOrEmpty(input.getNotes())) {
            queryWrapper=queryWrapper.eq(LeaveMessage::getNotes,input.getNotes());
       	 }
        if(input.getPersonUserId()!=null) {
            queryWrapper=queryWrapper.eq(LeaveMessage::getPersonUserId,input.getPersonUserId());
       	 }

        if(Extension.isNotNullOrEmpty(input.getType())) {
            queryWrapper=queryWrapper.eq(LeaveMessage::getType,input.getType());
       	 }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(LeaveMessage::getCreationTime);
        //构建一个分页查询的model
        Page<LeaveMessage> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取留言数据
        IPage<LeaveMessage> pageRecords= _LeaveMessageMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _LeaveMessageMpper.selectCount(queryWrapper);

        //把LeaveMessage实体转换成LeaveMessage传输模型
        List<LeaveMessageDto> items= Extension.copyBeanList(pageRecords.getRecords(),LeaveMessageDto.class);
        for (LeaveMessageDto item : items) {
          
          //查询出关联的创建用户信息
            AppUserDto  CreatorAppUserDTO=new AppUserDto();
            AppUser  CreatorAppUserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getCreatorId()));
           if(CreatorAppUserEntity!=null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }  
         
           
          	            
           //查询出关联的AppUser表信息
           AppUserDto PersonUserDTO = new AppUserDto();        
            AppUser  PersonUserEntity= _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId,item.getPersonUserId()));
           if(PersonUserEntity!=null) {  
          BeanUtils.copyProperties(PersonUserDTO,PersonUserEntity);
            item.setPersonUserDto(PersonUserDTO); 
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
     * 单个留言查询
     */
    @SneakyThrows
    @Override
    public LeaveMessageDto Get(LeaveMessagePagedInput input) {
       if(input.getId()==null)
        {
            return new LeaveMessageDto();
        }
        PagedResult<LeaveMessageDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new LeaveMessageDto();
    }

    /**
     *留言创建或者修改
     */
    @SneakyThrows
    @Override
    public LeaveMessageDto CreateOrEdit(LeaveMessageDto input) {

        //声明一个留言实体
        LeaveMessage LeaveMessage = new LeaveMessage();

        //把前端传入的input参数拷贝到留言实体
        BeanUtils.copyProperties(LeaveMessage,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(LeaveMessage);

        //定义一个返回给前端的留言传输模型
        LeaveMessageDto LeaveMessageDto=new LeaveMessageDto();

        //同理把操作的留言实体拷贝给留言传输模型
        BeanUtils.copyProperties(LeaveMessageDto,LeaveMessage);

        //把传输模型返回给前端
        return LeaveMessageDto;
    }



}
