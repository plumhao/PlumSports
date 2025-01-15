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
 * 会员卡功能实现类
 */
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的Card表mapper对象
     */
    @Autowired
    private CardMapper _CardMpper;
    /**
     * 操作数据库的CardRecord表mapper对象
     */
    @Autowired
    private CardRecordMapper _CardRecordMpper;


    /**
     * 会员卡分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<CardDto> List(CardPagedInput input) {

      
        //声明一个支持会员卡查询的(拉姆达)表达式
        LambdaQueryWrapper<Card> queryWrapper = Wrappers.<Card>lambdaQuery()
                .eq(input.getId()!=null&&input.getId()!=0,Card::getId,input.getId())
                .eq(input.getCreatorId()!=null,Card::getCreatorId,input.getCreatorId());
   //如果前端搜索传入查询条件则拼接查询条件

        if(Extension.isNotNullOrEmpty(input.getName())) {
            queryWrapper=queryWrapper.eq(Card::getName,input.getName());
       	 }
         if(input.getRanksStartRange()!=null)
          {
            queryWrapper=queryWrapper.gt(Card::getRanks,input.getRanksStartRange());
          }
      		 if(input.getRanksEndRange()!=null)
          {
            queryWrapper=queryWrapper.lt(Card::getRanks,input.getRanksEndRange());
          }
         if(input.getMoneyStartRange()!=null)
          {
            queryWrapper=queryWrapper.gt(Card::getMoney,input.getMoneyStartRange());
          }
      		 if(input.getMoneyEndRange()!=null)
          {
            queryWrapper=queryWrapper.lt(Card::getMoney,input.getMoneyEndRange());
          }
         if(input.getDiscountsStartRange()!=null)
          {
            queryWrapper=queryWrapper.gt(Card::getDiscounts,input.getDiscountsStartRange());
          }
      		 if(input.getDiscountsEndRange()!=null)
          {
            queryWrapper=queryWrapper.lt(Card::getDiscounts,input.getDiscountsEndRange());
          }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(Card::getRanks);
        //构建一个分页查询的model
        Page<Card> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取会员卡数据
        IPage<Card> pageRecords= _CardMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _CardMpper.selectCount(queryWrapper);

        //把Card实体转换成Card传输模型
        List<CardDto> items= Extension.copyBeanList(pageRecords.getRecords(),CardDto.class);
        for (CardDto item : items) {
          
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
     * 单个会员卡查询
     */
    @SneakyThrows
    @Override
    public CardDto Get(CardPagedInput input) {
       if(input.getId()==null)
        {
            return new CardDto();
        }
        PagedResult<CardDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new CardDto();
    }

    /**
     *会员卡创建或者修改
     */
    @SneakyThrows
    @Override
    public CardDto CreateOrEdit(CardDto input) {

        //声明一个会员卡实体
        Card Card = new Card();

        //把前端传入的input参数拷贝到会员卡实体
        BeanUtils.copyProperties(Card,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(Card);

        //定义一个返回给前端的会员卡传输模型
        CardDto CardDto=new CardDto();

        //同理把操作的会员卡实体拷贝给会员卡传输模型
        BeanUtils.copyProperties(CardDto,Card);

        //把传输模型返回给前端
        return CardDto;
    }



}
