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
 * 门户封面功能实现类
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的Banner表mapper对象
     */
    @Autowired
    private BannerMapper _BannerMpper;


    /**
     * 门户封面分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<BannerDto> List(BannerPagedInput input) {

      
        //声明一个支持门户封面查询的(拉姆达)表达式
        LambdaQueryWrapper<Banner> queryWrapper = Wrappers.<Banner>lambdaQuery()
                .eq(input.getId()!=null,Banner::getId,input.getId())
                .eq(input.getCreatorId()!=null,Banner::getCreatorId,input.getCreatorId());
      
     	 //如果前端搜索传入Title不为空,则进行精确查询
        if(Extension.isNotNullOrEmpty(input.getTitle())) {
            queryWrapper=queryWrapper.eq(Banner::getTitle,input.getTitle());
       	 }
     	 //如果前端搜索传入Remark不为空,则进行精确查询
        if(Extension.isNotNullOrEmpty(input.getRemark())) {
            queryWrapper=queryWrapper.eq(Banner::getRemark,input.getRemark());
       	 }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(Banner::getCreationTime);
        //构建一个分页查询的model
        Page<Banner> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取门户封面数据
        IPage<Banner> pageRecords= _BannerMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _BannerMpper.selectCount(queryWrapper);

        //把Banner实体转换成Banner传输模型
        List<BannerDto> items= Extension.copyBeanList(pageRecords.getRecords(),BannerDto.class);
        for (BannerDto item : items) {
          
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
     * 单个门户封面查询
     */
    @SneakyThrows
    @Override
    public BannerDto Get(BannerPagedInput input) {
       if(input.getId()==null)
        {
            return new BannerDto();
        }
        PagedResult<BannerDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new BannerDto();
    }

    /**
     *门户封面创建或者修改
     */
    @SneakyThrows
    @Override
    public BannerDto CreateOrEdit(BannerDto input) {

        //声明一个门户封面实体
        Banner Banner = new Banner();

        //把前端传入的input参数拷贝到门户封面实体
        BeanUtils.copyProperties(Banner,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(Banner);

        //定义一个返回给前端的门户封面传输模型
        BannerDto BannerDto=new BannerDto();

        //同理把操作的门户封面实体拷贝给门户封面传输模型
        BeanUtils.copyProperties(BannerDto,Banner);

        //把传输模型返回给前端
        return BannerDto;
    }



}
