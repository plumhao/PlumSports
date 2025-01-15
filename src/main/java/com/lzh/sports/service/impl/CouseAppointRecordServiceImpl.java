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

import java.time.LocalDateTime;
import java.util.List;

import lombok.SneakyThrows;
import com.lzh.sports.tools.*;

/**
 * 课程预约记录功能实现类
 */
@Service
public class CouseAppointRecordServiceImpl extends ServiceImpl<CouseAppointRecordMapper, CouseAppointRecord> implements CouseAppointRecordService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的CouseAppointRecord表mapper对象
     */
    @Autowired
    private CouseAppointRecordMapper _CouseAppointRecordMpper;


    @Autowired
    private CousePlanMapper _CousePlanMapper;


    /**
     * 操作数据库的Card表mapper对象
     */
    @Autowired
    private CardMapper _CardMpper;


    @Autowired
    private CouseMapper _CouseMapper;
    /**
     * 操作数据库的MoneyRecord表mapper对象
     */
    @Autowired
    private MoneyRecordMapper _MoneyRecordMpper;


    /**
     * 课程预约记录分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<CouseAppointRecordDto> List(CouseAppointRecordPagedInput input) {


        //声明一个支持课程预约记录查询的(拉姆达)表达式
        LambdaQueryWrapper<CouseAppointRecord> queryWrapper = Wrappers.<CouseAppointRecord>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, CouseAppointRecord::getId, input.getId())
                .eq(input.getCreatorId() != null, CouseAppointRecord::getCreatorId, input.getCreatorId());
        //如果前端搜索传入查询条件则拼接查询条件
        if (input.getCousePlanId() != null) {
            queryWrapper = queryWrapper.eq(CouseAppointRecord::getCousePlanId, input.getCousePlanId());
        }
        if (input.getBeginTimeRange() != null && input.getBeginTimeRange().stream().count() > 1) {
            queryWrapper = queryWrapper.lt(CouseAppointRecord::getBeginTime, input.getBeginTimeRange().get(1));
            queryWrapper = queryWrapper.gt(CouseAppointRecord::getBeginTime, input.getBeginTimeRange().get(0));
        }
        if (input.getEndTimeRange() != null && input.getEndTimeRange().stream().count() > 1) {
            queryWrapper = queryWrapper.lt(CouseAppointRecord::getEndTime, input.getEndTimeRange().get(1));
            queryWrapper = queryWrapper.gt(CouseAppointRecord::getEndTime, input.getEndTimeRange().get(0));
        }
        if (input.getUserId() != null) {
            queryWrapper = queryWrapper.eq(CouseAppointRecord::getUserId, input.getUserId());
        }
        if (input.getCouseId() != null) {
            queryWrapper = queryWrapper.eq(CouseAppointRecord::getCouseId, input.getCouseId());
        }
        if (input.getCoachUserId() != null) {
            queryWrapper = queryWrapper.eq(CouseAppointRecord::getCoachUserId, input.getCoachUserId());
        }
        if (input.getCouseAppointStatus() != null) {
            queryWrapper = queryWrapper.eq(CouseAppointRecord::getCouseAppointStatus, input.getCouseAppointStatus());
        }
        if (input.getCheckTimeRange() != null && input.getCheckTimeRange().stream().count() > 1) {
            queryWrapper = queryWrapper.lt(CouseAppointRecord::getCheckTime, input.getCheckTimeRange().get(1));
            queryWrapper = queryWrapper.gt(CouseAppointRecord::getCheckTime, input.getCheckTimeRange().get(0));
        }

        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(CouseAppointRecord::getCreationTime);
        //构建一个分页查询的model
        Page<CouseAppointRecord> page = new Page<>(input.getPage(), input.getLimit());


        //从数据库进行分页查询获取课程预约记录数据
        IPage<CouseAppointRecord> pageRecords = _CouseAppointRecordMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount = _CouseAppointRecordMpper.selectCount(queryWrapper);

        //把CouseAppointRecord实体转换成CouseAppointRecord传输模型
        List<CouseAppointRecordDto> items = Extension.copyBeanList(pageRecords.getRecords(), CouseAppointRecordDto.class);
        for (CouseAppointRecordDto item : items) {

            //查询出关联的创建用户信息
            AppUserDto CreatorAppUserDTO = new AppUserDto();
            AppUser CreatorAppUserEntity = _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCreatorId()));
            if (CreatorAppUserEntity != null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }


            //查询出关联的CousePlan表信息
            CousePlanDto CousePlanDTO = new CousePlanDto();
            CousePlan CousePlanEntity = _CousePlanMapper.selectOne(Wrappers.<CousePlan>lambdaQuery().eq(CousePlan::getId, item.getCousePlanId()));
            if (CousePlanEntity != null) {
                BeanUtils.copyProperties(CousePlanDTO, CousePlanEntity);
                item.setCousePlanDto(CousePlanDTO);
            }


            //查询出关联的AppUser表信息
            AppUserDto UserDTO = new AppUserDto();
            AppUser UserEntity = _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getUserId()));
            if (UserEntity != null) {
                BeanUtils.copyProperties(UserDTO, UserEntity);
                item.setUserDto(UserDTO);
            }


            //查询出关联的AppUser表信息
            AppUserDto CoachUserDTO = new AppUserDto();
            AppUser CoachUserEntity = _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCoachUserId()));
            if (CoachUserEntity != null) {
                BeanUtils.copyProperties(CoachUserDTO, CoachUserEntity);
                item.setCoachUserDto(CoachUserDTO);
            }


            //查询出关联的Couse表信息
            CouseDto CouseDTO = new CouseDto();
            Couse CouseEntity = _CouseMapper.selectOne(Wrappers.<Couse>lambdaQuery().eq(Couse::getId, item.getCouseId()));
            if (CouseEntity != null) {
                BeanUtils.copyProperties(CouseDTO, CouseEntity);
                item.setCouseDto(CouseDTO);
            }

        }

        //返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 单个课程预约记录查询
     */
    @SneakyThrows
    @Override
    public CouseAppointRecordDto Get(CouseAppointRecordPagedInput input) {
        if (input.getId() == null) {
            return new CouseAppointRecordDto();
        }
        PagedResult<CouseAppointRecordDto> pagedResult = List(input);
        return pagedResult.getTotalCount() > 0 ? pagedResult.getItems().stream().findFirst().get() : new CouseAppointRecordDto();
    }

    /**
     * 课程预约记录创建或者修改
     */
    @SneakyThrows
    @Override
    public CouseAppointRecordDto CreateOrEdit(CouseAppointRecordDto input) {

        //声明一个课程预约记录实体
        CouseAppointRecord CouseAppointRecord = new CouseAppointRecord();

        //把前端传入的input参数拷贝到课程预约记录实体
        BeanUtils.copyProperties(CouseAppointRecord, input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(CouseAppointRecord);

        //定义一个返回给前端的课程预约记录传输模型
        CouseAppointRecordDto CouseAppointRecordDto = new CouseAppointRecordDto();

        //同理把操作的课程预约记录实体拷贝给课程预约记录传输模型
        BeanUtils.copyProperties(CouseAppointRecordDto, CouseAppointRecord);

        //把传输模型返回给前端
        return CouseAppointRecordDto;
    }

    /**
     * 课程预约
     */
    @SneakyThrows
    @Override
    public void Appoint(CouseAppointRecordDto input) {
        //查询一下这个预约是否还有名额
        CousePlan cousePlan = _CousePlanMapper.selectById(input.getCousePlanId());

        //对比一下当前时间 课程开始时间 课程结束时间
        if (cousePlan.getBeginTime().isBefore(LocalDateTime.now())) {
            throw new CustomException("课程已经开始了");
        }

        if (cousePlan.getCurrentEnrollments() == cousePlan.getMaxParticipants()) {
            throw new CustomException("已经没有空余的名额了");
        }
        //判断一下自己是否预约过了并且不等与取消

        CouseAppointRecord couseAppointRecord = _CouseAppointRecordMpper
                .selectOne(Wrappers.<CouseAppointRecord>lambdaQuery()
                        .eq(CouseAppointRecord::getUserId, input.getUserId())
                        .eq(CouseAppointRecord::getCousePlanId, input.getCousePlanId()));
        if (couseAppointRecord != null && couseAppointRecord.getCouseAppointStatus() == Enums.CouseAppointStatus.用户取消.index()) {
            throw new CustomException("你已经取消过了");
        }

        if (couseAppointRecord != null) {
            throw new CustomException("你已经预约过了");
        }
        //查询一下自己的余额
        AppUser appUser = _AppUserMapper.selectById(input.getUserId());
        if (appUser.getCardId() == null) {
            throw new CustomException("你还没有办理会员卡");
        }
        //查询一下用户会员卡的信息
        Card card = _CardMpper.selectById(appUser.getCardId());
        //查询课程所需要的会员等级
        Card couseCard = _CardMpper.selectById(cousePlan.getCardId());
        if (card.getRanks() < couseCard.getRanks()) {
            throw new CustomException("你的会员卡等级不够");
        }


        //修改课程的人数
        cousePlan.setCurrentEnrollments(cousePlan.getCurrentEnrollments() + 1);
        _CousePlanMapper.updateById(cousePlan);

        //声明一个课程预约记录实体
        CouseAppointRecord CouseAppointRecord = new CouseAppointRecord();

        //把前端传入的input参数拷贝到课程预约记录实体
        BeanUtils.copyProperties(CouseAppointRecord, input);

        //调用数据库的增加或者修改方法
        saveOrUpdate(CouseAppointRecord);

        //查询课程
        Couse couse = _CouseMapper.selectById(cousePlan.getCouseId());

        //判断用户的余额是否足够
        if (couse.getFee() != null) {
            if (appUser.getMoney() < couse.getFee() * couseCard.getDiscounts()) {
                throw new CustomException("你的余额不足");
            }

            //生成一条扣款记录
            var moneyRecord = new MoneyRecord();
            moneyRecord.setAmount(couse.getFee() * couseCard.getDiscounts());
            moneyRecord.setBalance(appUser.getMoney() - couse.getFee() * couseCard.getDiscounts());
            moneyRecord.setContent("你预约了" + couse.getName() + "课程" + "花费金额" + couse.getFee() * couseCard.getDiscounts());
            moneyRecord.setTransactionType("消费");
            moneyRecord.setRelativeId(CouseAppointRecord.getId());
            moneyRecord.setUserId(appUser.getId());
            moneyRecord.setTransactionTime(LocalDateTime.now());
            _MoneyRecordMpper.insert(moneyRecord);

            //修改用户的余额
            appUser.setMoney(appUser.getMoney() - couse.getFee() * couseCard.getDiscounts());
            _AppUserMapper.updateById(appUser);
        }


    }

    /**
     * 课程取消
     */
    @SneakyThrows
    @Override
    public void Cancel(CouseAppointRecordDto input) {

        CouseAppointRecord couseAppointRecord = _CouseAppointRecordMpper.selectById(input.getId());

        if (couseAppointRecord == null) {
            throw new CustomException("预约记录不存在");
        }
        if (couseAppointRecord.getCouseAppointStatus() == Enums.CouseAppointStatus.用户取消.index()) {
            throw new CustomException("你已经取消过了");
        }
        if (couseAppointRecord.getCouseAppointStatus() == Enums.CouseAppointStatus.已签到.index()) {
            throw new CustomException("你已经签到了");
        }
        //如果当前时间大于开始时间了
        CousePlan cousePlan = _CousePlanMapper.selectById(couseAppointRecord.getCousePlanId());
        if (cousePlan.getBeginTime().isBefore(LocalDateTime.now())) {
            throw new CustomException("课程已经开始了,不可以取消");
        }
        //修改课程的人数
        cousePlan.setCurrentEnrollments(cousePlan.getCurrentEnrollments() - 1);
        _CousePlanMapper.updateById(cousePlan);
        couseAppointRecord.setCouseAppointStatus(Enums.CouseAppointStatus.用户取消.index());
        _CouseAppointRecordMpper.updateById(couseAppointRecord);

        //查询是否存在扣款记录
        MoneyRecord moneyRecord = _MoneyRecordMpper.selectOne(Wrappers.<MoneyRecord>lambdaQuery().eq(MoneyRecord::getRelativeId, couseAppointRecord.getId()));
        if(moneyRecord!=null)
        {
            //查询课程
            Couse couse = _CouseMapper.selectById(cousePlan.getCouseId());
            //添加一条流水
            MoneyRecord cancelmoneyRecord = new MoneyRecord();
            cancelmoneyRecord.setRelativeId(couseAppointRecord.getId());
            cancelmoneyRecord.setUserId(couseAppointRecord.getUserId());
            cancelmoneyRecord.setTransactionType("退款");
            cancelmoneyRecord.setAmount(moneyRecord.getAmount());
            cancelmoneyRecord.setBalance(moneyRecord.getBalance() + moneyRecord.getAmount());
            cancelmoneyRecord.setContent("你取消了" + couse.getName() + "课程" + "退款金额" + moneyRecord.getAmount());
            cancelmoneyRecord.setTransactionTime(LocalDateTime.now());
            _MoneyRecordMpper.insert(cancelmoneyRecord);
            //查询用户
            AppUser appUser = _AppUserMapper.selectById(couseAppointRecord.getUserId());
            //修改用户的余额
            appUser.setMoney(appUser.getMoney() + moneyRecord.getAmount());
            _AppUserMapper.updateById(appUser);
        }



    }

    /**
     * 课程签到
     */
    @SneakyThrows
    @Override
    public void Check(CouseAppointRecordDto input){
        CouseAppointRecord couseAppointRecord = _CouseAppointRecordMpper.selectById(input.getId());
        couseAppointRecord.setCheckTime(LocalDateTime.now());
        couseAppointRecord.setCouseAppointStatus(Enums.CouseAppointStatus.已签到.index());
        _CouseAppointRecordMpper.updateById(couseAppointRecord);

    }
    /**
     * 课程记录自动完成
     */
    @SneakyThrows
    @Override
    public void AutoComplete(){
        //查询所有状态为进行中的课程记录
        List<CouseAppointRecord> couseAppointRecords = _CouseAppointRecordMpper.selectList(
                Wrappers.<CouseAppointRecord>lambdaQuery().eq(CouseAppointRecord::getCouseAppointStatus, Enums.CouseAppointStatus.已签到.index()));

        for (CouseAppointRecord couseAppointRecord : couseAppointRecords) {
            //如果当前时间大于课程记录的结束时间
            if (couseAppointRecord.getEndTime().isBefore(LocalDateTime.now())) {
                couseAppointRecord.setCouseAppointStatus(Enums.CouseAppointStatus.完成.index());
                _CouseAppointRecordMpper.updateById(couseAppointRecord);
            }
        }
    }
    /**
     * 课程记录自动取消
     */
    @SneakyThrows
    @Override
    public void AutoCancel(){
        //查询所有状态为进行中的课程记录
        List<CouseAppointRecord> couseAppointRecords = _CouseAppointRecordMpper.selectList(
                Wrappers.<CouseAppointRecord>lambdaQuery().eq(CouseAppointRecord::getCouseAppointStatus, Enums.CouseAppointStatus.待进行.index()));

        for (CouseAppointRecord couseAppointRecord : couseAppointRecords) {
            //如果当前时间大于课程记录的结束时间
            if (couseAppointRecord.getEndTime().isBefore(LocalDateTime.now())) {
                couseAppointRecord.setCouseAppointStatus(Enums.CouseAppointStatus.违约.index());
                _CouseAppointRecordMpper.updateById(couseAppointRecord);
            }
        }
    }
}
