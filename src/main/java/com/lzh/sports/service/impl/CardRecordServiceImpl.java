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
 * 会员变动记录功能实现类
 */
@Service
public class CardRecordServiceImpl extends ServiceImpl<CardRecordMapper, CardRecord> implements CardRecordService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的CardRecord表mapper对象
     */
    @Autowired
    private CardRecordMapper _CardRecordMpper;

 			
			

    /**
     * 会员变动记录分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<CardRecordDto> List(CardRecordPagedInput input) {

      
        //声明一个支持会员变动记录查询的(拉姆达)表达式
        LambdaQueryWrapper<CardRecord> queryWrapper = Wrappers.<CardRecord>lambdaQuery()
                .eq(input.getId()!=null&&input.getId()!=0,CardRecord::getId,input.getId())
                .eq(input.getCreatorId()!=null,CardRecord::getCreatorId,input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件
        if(input.getUserId()!=null) {
            queryWrapper=queryWrapper.eq(CardRecord::getUserId,input.getUserId());
       	 }

        if(Extension.isNotNullOrEmpty(input.getOperation())) {
            queryWrapper=queryWrapper.eq(CardRecord::getOperation,input.getOperation());
       	 }

        if(Extension.isNotNullOrEmpty(input.getCardNo())) {
            queryWrapper=queryWrapper.eq(CardRecord::getCardNo,input.getCardNo());
       	 }
        if(input.getOperateTimeRange()!=null&&input.getOperateTimeRange().stream().count()>1)
        {
            queryWrapper=queryWrapper.lt(CardRecord::getOperateTime,input.getOperateTimeRange().get(1));
            queryWrapper=queryWrapper.gt(CardRecord::getOperateTime,input.getOperateTimeRange().get(0));
        }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(CardRecord::getCreationTime);
        //构建一个分页查询的model
        Page<CardRecord> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取会员变动记录数据
        IPage<CardRecord> pageRecords= _CardRecordMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _CardRecordMpper.selectCount(queryWrapper);

        //把CardRecord实体转换成CardRecord传输模型
        List<CardRecordDto> items= Extension.copyBeanList(pageRecords.getRecords(),CardRecordDto.class);
        for (CardRecordDto item : items) {
          
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
          
        }
      
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items,totalCount);

    }
  
    /**
     * 单个会员变动记录查询
     */
    @SneakyThrows
    @Override
    public CardRecordDto Get(CardRecordPagedInput input) {
       if(input.getId()==null)
        {
            return new CardRecordDto();
        }
        PagedResult<CardRecordDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new CardRecordDto();
    }

    /**
     *会员变动记录创建或者修改
     */
    @SneakyThrows
    @Override
    public CardRecordDto CreateOrEdit(CardRecordDto input) {

        //声明一个会员变动记录实体
        CardRecord CardRecord = new CardRecord();

        //把前端传入的input参数拷贝到会员变动记录实体
        BeanUtils.copyProperties(CardRecord,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(CardRecord);

        //定义一个返回给前端的会员变动记录传输模型
        CardRecordDto CardRecordDto=new CardRecordDto();

        //同理把操作的会员变动记录实体拷贝给会员变动记录传输模型
        BeanUtils.copyProperties(CardRecordDto,CardRecord);

        //把传输模型返回给前端
        return CardRecordDto;
    }



}
