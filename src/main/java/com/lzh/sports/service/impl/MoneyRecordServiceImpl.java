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

import java.time.LocalDateTime;
import java.util.List;

import lombok.SneakyThrows;
import com.lzh.sports.tools.*;

/**
 * 余额变动记录功能实现类
 */
@Service
public class MoneyRecordServiceImpl extends ServiceImpl<MoneyRecordMapper, MoneyRecord> implements MoneyRecordService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的MoneyRecord表mapper对象
     */
    @Autowired
    private MoneyRecordMapper _MoneyRecordMpper;

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
     * 余额变动记录分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<MoneyRecordDto> List(MoneyRecordPagedInput input) {


        //声明一个支持余额变动记录查询的(拉姆达)表达式
        LambdaQueryWrapper<MoneyRecord> queryWrapper = Wrappers.<MoneyRecord>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, MoneyRecord::getId, input.getId())
                .eq(input.getCreatorId() != null, MoneyRecord::getCreatorId, input.getCreatorId());
        //如果前端搜索传入查询条件则拼接查询条件
        if (input.getUserId() != null) {
            queryWrapper = queryWrapper.eq(MoneyRecord::getUserId, input.getUserId());
        }
        if (input.getAmountStartRange() != null) {
            queryWrapper = queryWrapper.gt(MoneyRecord::getAmount, input.getAmountStartRange());
        }
        if (input.getAmountEndRange() != null) {
            queryWrapper = queryWrapper.lt(MoneyRecord::getAmount, input.getAmountEndRange());
        }
        if (input.getBalanceStartRange() != null) {
            queryWrapper = queryWrapper.gt(MoneyRecord::getBalance, input.getBalanceStartRange());
        }
        if (input.getBalanceEndRange() != null) {
            queryWrapper = queryWrapper.lt(MoneyRecord::getBalance, input.getBalanceEndRange());
        }

        if (Extension.isNotNullOrEmpty(input.getTransactionType())) {
            queryWrapper = queryWrapper.eq(MoneyRecord::getTransactionType, input.getTransactionType());
        }
        if (input.getTransactionTimeRange() != null && input.getTransactionTimeRange().stream().count() > 1) {
            queryWrapper = queryWrapper.lt(MoneyRecord::getTransactionTime, input.getTransactionTimeRange().get(1));
            queryWrapper = queryWrapper.gt(MoneyRecord::getTransactionTime, input.getTransactionTimeRange().get(0));
        }

        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(MoneyRecord::getCreationTime);
        //构建一个分页查询的model
        Page<MoneyRecord> page = new Page<>(input.getPage(), input.getLimit());


        //从数据库进行分页查询获取余额变动记录数据
        IPage<MoneyRecord> pageRecords = _MoneyRecordMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount = _MoneyRecordMpper.selectCount(queryWrapper);

        //把MoneyRecord实体转换成MoneyRecord传输模型
        List<MoneyRecordDto> items = Extension.copyBeanList(pageRecords.getRecords(), MoneyRecordDto.class);
        for (MoneyRecordDto item : items) {

            //查询出关联的创建用户信息
            AppUserDto CreatorAppUserDTO = new AppUserDto();
            AppUser CreatorAppUserEntity = _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCreatorId()));
            if (CreatorAppUserEntity != null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
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
     * 单个余额变动记录查询
     */
    @SneakyThrows
    @Override
    public MoneyRecordDto Get(MoneyRecordPagedInput input) {
        if (input.getId() == null) {
            return new MoneyRecordDto();
        }
        PagedResult<MoneyRecordDto> pagedResult = List(input);
        return pagedResult.getTotalCount() > 0 ? pagedResult.getItems().stream().findFirst().get() : new MoneyRecordDto();
    }

    /**
     * 余额变动记录创建或者修改
     */
    @SneakyThrows
    @Override
    public MoneyRecordDto CreateOrEdit(MoneyRecordDto input) {

        //声明一个余额变动记录实体
        MoneyRecord MoneyRecord = new MoneyRecord();

        //把前端传入的input参数拷贝到余额变动记录实体
        BeanUtils.copyProperties(MoneyRecord, input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(MoneyRecord);

        //定义一个返回给前端的余额变动记录传输模型
        MoneyRecordDto MoneyRecordDto = new MoneyRecordDto();

        //同理把操作的余额变动记录实体拷贝给余额变动记录传输模型
        BeanUtils.copyProperties(MoneyRecordDto, MoneyRecord);

        //把传输模型返回给前端
        return MoneyRecordDto;
    }


    /**
     * 充值
     */
    @SneakyThrows
    @Override
    public void Recharge(MoneyRecordDto input) {

        //查询用户
        AppUser appUser = _AppUserMapper.selectById(input.getUserId());
        if (appUser == null) {
            throw new Exception("用户不存在");
        }
        if (input.getAmount() <= 0) {
            throw new Exception("充值金额必须大于0");
        }
        appUser.setMoney(appUser.getMoney() + input.getAmount());
        _AppUserMapper.updateById(appUser);

        input.setTransactionTime(LocalDateTime.now());
        input.setBalance(appUser.getMoney());
        input.setTransactionType("充值");

        //声明一个余额变动记录实体
        MoneyRecord MoneyRecord = new MoneyRecord();

        //把前端传入的input参数拷贝到余额变动记录实体
        BeanUtils.copyProperties(MoneyRecord, input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(MoneyRecord);

        SetUserCard(input.getUserId());

    }

    /**
     * 设置会员卡等级
     * @param userId
     */
    private void SetUserCard(Integer userId){
        //得到用户目前累计的金额
        Double userMoney = GetUserMoney(userId);
        //查询出所有卡
        List<Card> cards = _CardMpper.selectList(null);

        //得到用户累计金额大于卡的最小金额的卡 并且按卡的金额从大到小排
        List<Card> list = cards.stream().filter(x -> userMoney >= x.getMoney()).sorted((x, y) -> y.getMoney().compareTo(x.getMoney())).toList();
        if(list.stream().count()>0) {

            //得到第一个卡
            Card card = list.stream().findFirst().get();
            //设置给用户
            AppUser appUser = _AppUserMapper.selectById(userId);
            appUser.setCardId(card.getId());
            if(appUser.getOpenCardTime()==null){
                appUser.setOpenCardTime(LocalDateTime.now());
            }
            _AppUserMapper.updateById(appUser);

            //写入会员卡等级变动记录
            CardRecord cardRecord = new CardRecord();
            cardRecord.setUserId(userId);
            cardRecord.setCardNo(appUser.getCardNo());
            cardRecord.setOperateTime(LocalDateTime.now());
            cardRecord.setOperation("会员"+appUser.getCardNo()+"会员升级为:"+card.getName());
            _CardRecordMpper.insert(cardRecord);
        }


    }
    /**
     * 得到用户累计余额 充值+退款的累计金额
     *
     * @param userId
     * @return
     */
    private Double GetUserMoney(Integer userId) {
        LambdaQueryWrapper<MoneyRecord> queryWrapper = Wrappers.<MoneyRecord>lambdaQuery()
                .eq(userId != null, MoneyRecord::getUserId, userId);
        List<MoneyRecord> items = _MoneyRecordMpper.selectList(queryWrapper);
        //只查询类型为1 3 的
        return items.stream().filter(x -> x.getTransactionType().equals("充值") || x.getTransactionType().equals("退款")).mapToDouble(MoneyRecord::getAmount).sum();

    }

}
