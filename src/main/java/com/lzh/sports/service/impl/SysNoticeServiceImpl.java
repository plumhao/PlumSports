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
 * 系统通知功能实现类
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {

	 /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的SysNotice表mapper对象
     */
    @Autowired
    private SysNoticeMapper _SysNoticeMpper;


    /**
     * 系统通知分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<SysNoticeDto> List(SysNoticePagedInput input) {

      
        //声明一个支持系统通知查询的(拉姆达)表达式
        LambdaQueryWrapper<SysNotice> queryWrapper = Wrappers.<SysNotice>lambdaQuery()
                .eq(input.getId()!=null&&input.getId()!=0,SysNotice::getId,input.getId())
                .eq(input.getCreatorId()!=null,SysNotice::getCreatorId,input.getCreatorId());
      
     	 //如果前端搜索传入Name不为空,则进行精确查询
        if(Extension.isNotNullOrEmpty(input.getName())) {
            queryWrapper=queryWrapper.eq(SysNotice::getName,input.getName());
       	 }
     	 //如果前端搜索传入Author不为空,则进行精确查询
        if(Extension.isNotNullOrEmpty(input.getAuthor())) {
            queryWrapper=queryWrapper.eq(SysNotice::getAuthor,input.getAuthor());
       	 }
      
        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper=queryWrapper.orderByDesc(SysNotice::getCreationTime);
        //构建一个分页查询的model
        Page<SysNotice> page = new Page<>(input.getPage(), input.getLimit());
          
   
         //从数据库进行分页查询获取系统通知数据
        IPage<SysNotice> pageRecords= _SysNoticeMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount= _SysNoticeMpper.selectCount(queryWrapper);

        //把SysNotice实体转换成SysNotice传输模型
        List<SysNoticeDto> items= Extension.copyBeanList(pageRecords.getRecords(),SysNoticeDto.class);
        for (SysNoticeDto item : items) {
          
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
     * 单个系统通知查询
     */
    @SneakyThrows
    @Override
    public SysNoticeDto Get(SysNoticePagedInput input) {
       if(input.getId()==null)
        {
            return new SysNoticeDto();
        }
        PagedResult<SysNoticeDto>  pagedResult =List(input);
        return pagedResult.getTotalCount()>0?pagedResult.getItems().stream().findFirst().get():new SysNoticeDto();
    }

    /**
     *系统通知创建或者修改
     */
    @SneakyThrows
    @Override
    public SysNoticeDto CreateOrEdit(SysNoticeDto input) {

        //声明一个系统通知实体
        SysNotice SysNotice = new SysNotice();

        //把前端传入的input参数拷贝到系统通知实体
        BeanUtils.copyProperties(SysNotice,input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(SysNotice);

        //定义一个返回给前端的系统通知传输模型
        SysNoticeDto SysNoticeDto=new SysNoticeDto();

        //同理把操作的系统通知实体拷贝给系统通知传输模型
        BeanUtils.copyProperties(SysNoticeDto,SysNotice);

        //把传输模型返回给前端
        return SysNoticeDto;
    }



}
