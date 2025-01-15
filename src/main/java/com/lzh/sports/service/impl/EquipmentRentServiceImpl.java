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
import com.lzh.sports.tools.exception.CustomException;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.SneakyThrows;
import com.lzh.sports.tools.*;

/**
 * 器材租借功能实现类
 */
@Service
public class EquipmentRentServiceImpl extends ServiceImpl<EquipmentRentMapper, EquipmentRent> implements EquipmentRentService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的EquipmentRent表mapper对象
     */
    @Autowired
    private EquipmentRentMapper _EquipmentRentMpper;


    @Autowired
    private EquipmentMapper _EquipmentMapper;


    /**
     * 器材租借分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<EquipmentRentDto> List(EquipmentRentPagedInput input) {


        //声明一个支持器材租借查询的(拉姆达)表达式
        LambdaQueryWrapper<EquipmentRent> queryWrapper = Wrappers.<EquipmentRent>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, EquipmentRent::getId, input.getId())
                .eq(input.getCreatorId() != null, EquipmentRent::getCreatorId, input.getCreatorId());
        //如果前端搜索传入查询条件则拼接查询条件
        if (input.getEquipmentId() != null) {
            queryWrapper = queryWrapper.eq(EquipmentRent::getEquipmentId, input.getEquipmentId());
        }
        if (input.getUserId() != null) {
            queryWrapper = queryWrapper.eq(EquipmentRent::getUserId, input.getUserId());
        }
        if (input.getRentalTimeStartRange() != null) {
            queryWrapper = queryWrapper.gt(EquipmentRent::getRentalTime, input.getRentalTimeStartRange());
        }
        if (input.getRentalTimeEndRange() != null) {
            queryWrapper = queryWrapper.lt(EquipmentRent::getRentalTime, input.getRentalTimeEndRange());
        }
        if (input.getReturnTimeRange() != null && input.getReturnTimeRange().stream().count() > 1) {
            queryWrapper = queryWrapper.lt(EquipmentRent::getReturnTime, input.getReturnTimeRange().get(1));
            queryWrapper = queryWrapper.gt(EquipmentRent::getReturnTime, input.getReturnTimeRange().get(0));
        }
        if (input.getActualReturnTimeRange() != null && input.getActualReturnTimeRange().stream().count() > 1) {
            queryWrapper = queryWrapper.lt(EquipmentRent::getActualReturnTime, input.getActualReturnTimeRange().get(1));
            queryWrapper = queryWrapper.gt(EquipmentRent::getActualReturnTime, input.getActualReturnTimeRange().get(0));
        }
        if (input.getRentalFeeStartRange() != null) {
            queryWrapper = queryWrapper.gt(EquipmentRent::getRentalFee, input.getRentalFeeStartRange());
        }
        if (input.getRentalFeeEndRange() != null) {
            queryWrapper = queryWrapper.lt(EquipmentRent::getRentalFee, input.getRentalFeeEndRange());
        }
        if (input.getDamageMoneyStartRange() != null) {
            queryWrapper = queryWrapper.gt(EquipmentRent::getDamageMoney, input.getDamageMoneyStartRange());
        }
        if (input.getDamageMoneyEndRange() != null) {
            queryWrapper = queryWrapper.lt(EquipmentRent::getDamageMoney, input.getDamageMoneyEndRange());
        }

        if (Extension.isNotNullOrEmpty(input.getNotes())) {
            queryWrapper = queryWrapper.eq(EquipmentRent::getNotes, input.getNotes());
        }

        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(EquipmentRent::getCreationTime);
        //构建一个分页查询的model
        Page<EquipmentRent> page = new Page<>(input.getPage(), input.getLimit());


        //从数据库进行分页查询获取器材租借数据
        IPage<EquipmentRent> pageRecords = _EquipmentRentMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount = _EquipmentRentMpper.selectCount(queryWrapper);

        //把EquipmentRent实体转换成EquipmentRent传输模型
        List<EquipmentRentDto> items = Extension.copyBeanList(pageRecords.getRecords(), EquipmentRentDto.class);
        for (EquipmentRentDto item : items) {

            //查询出关联的创建用户信息
            AppUserDto CreatorAppUserDTO = new AppUserDto();
            AppUser CreatorAppUserEntity = _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCreatorId()));
            if (CreatorAppUserEntity != null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }


            //查询出关联的Equipment表信息
            EquipmentDto EquipmentDTO = new EquipmentDto();
            Equipment EquipmentEntity = _EquipmentMapper.selectOne(Wrappers.<Equipment>lambdaQuery().eq(Equipment::getId, item.getEquipmentId()));
            if (EquipmentEntity != null) {
                BeanUtils.copyProperties(EquipmentDTO, EquipmentEntity);
                item.setEquipmentDto(EquipmentDTO);
            }


            //查询出关联的AppUser表信息
            AppUserDto UserDTO = new AppUserDto();
            AppUser UserEntity = _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getUserId()));
            if (UserEntity != null) {
                BeanUtils.copyProperties(UserDTO, UserEntity);
                item.setUserDto(UserDTO);
            }

        }

        //返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 单个器材租借查询
     */
    @SneakyThrows
    @Override
    public EquipmentRentDto Get(EquipmentRentPagedInput input) {
        if (input.getId() == null) {
            return new EquipmentRentDto();
        }
        PagedResult<EquipmentRentDto> pagedResult = List(input);
        return pagedResult.getTotalCount() > 0 ? pagedResult.getItems().stream().findFirst().get() : new EquipmentRentDto();
    }

    /**
     * 器材租借创建或者修改
     */
    @SneakyThrows
    @Override
    public EquipmentRentDto CreateOrEdit(EquipmentRentDto input) {

        //声明一个器材租借实体
        EquipmentRent EquipmentRent = new EquipmentRent();

        //把前端传入的input参数拷贝到器材租借实体
        BeanUtils.copyProperties(EquipmentRent, input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(EquipmentRent);

        //定义一个返回给前端的器材租借传输模型
        EquipmentRentDto EquipmentRentDto = new EquipmentRentDto();

        //同理把操作的器材租借实体拷贝给器材租借传输模型
        BeanUtils.copyProperties(EquipmentRentDto, EquipmentRent);

        //把传输模型返回给前端
        return EquipmentRentDto;
    }

    /**
     * 租借
     * @param input
     */
    @SneakyThrows
    @Override
    public void Rent(EquipmentRentDto input) {
        //查询用户
        AppUser UserEntity = _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, input.getUserId()));
        //判断用户是否开会员卡了
        if (UserEntity.getCardId() == null) {
            throw new CustomException("用户未开通会员卡");
        }
        //查询器材
        Equipment EquipmentEntity = _EquipmentMapper.selectOne(Wrappers.<Equipment>lambdaQuery().eq(Equipment::getId, input.getEquipmentId()));
        //判断器材是否可租借
        if (EquipmentEntity.getEquipmentStatus() != Enums.EquipmentStatus.可用.index()) {
            throw new CustomException("器材不可租借");
        }
        //判断用户是否有未归还的器材
        List<EquipmentRent> EquipmentRentEntity = _EquipmentRentMpper.selectList(Wrappers.<EquipmentRent>lambdaQuery().eq(EquipmentRent::getUserId, input.getUserId()).eq(EquipmentRent::getEquipmentId, input.getEquipmentId()).eq(EquipmentRent::getActualReturnTime, null));
        if (EquipmentRentEntity.size() > 0) {
            throw new CustomException("用户有未归还的器材");
        }
        //修改器材状态为租借中
        EquipmentEntity.setEquipmentStatus(Enums.EquipmentStatus.租借中.index());
        _EquipmentMapper.updateById(EquipmentEntity);
        //声明一个器材租借实体
        EquipmentRent EquipmentRent = new EquipmentRent();

        //把前端传入的input参数拷贝到器材租借实体
        BeanUtils.copyProperties(EquipmentRent, input);
        //调用数据库的增加或者修改方法
        saveOrUpdate(EquipmentRent);


    }
    /**
     * 归还
     * @param input
     */
    @SneakyThrows
    @Override
    public void ReturnEquipment(EquipmentRentDto input){
        //查询器材租借
        EquipmentRent EquipmentRentEntity = _EquipmentRentMpper.selectOne(Wrappers.<EquipmentRent>lambdaQuery().eq(EquipmentRent::getId, input.getId()));
        //判断器材租借是否存在
        if (EquipmentRentEntity == null) {
            throw new CustomException("器材租借不存在");
        }
        //判断器材租借是否已归还
        if (EquipmentRentEntity.getActualReturnTime() != null) {
            throw new CustomException("器材已归还");
        }
        //修改器材租借状态为已归还
        EquipmentRentEntity.setActualReturnTime(input.getActualReturnTime());
        EquipmentRentEntity.setDamageMoney(input.getDamageMoney());
        EquipmentRentEntity.setNotes(input.getNotes());
        EquipmentRentEntity.setRentalFee(input.getRentalFee());
        _EquipmentRentMpper.updateById(EquipmentRentEntity);
        //查询器材
        Equipment EquipmentEntity = _EquipmentMapper.selectOne(Wrappers.<Equipment>lambdaQuery().eq(Equipment::getId, EquipmentRentEntity.getEquipmentId()));
        //修改器材状态为可用
        EquipmentEntity.setEquipmentStatus(Enums.EquipmentStatus.可用.index());
        _EquipmentMapper.updateById(EquipmentEntity);

    }
}
