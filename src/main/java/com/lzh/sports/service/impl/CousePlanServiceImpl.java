package com.lzh.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzh.sports.dto.AppUserDto;
import com.lzh.sports.dto.CardDto;
import com.lzh.sports.dto.CouseDto;
import com.lzh.sports.dto.CousePlanDto;
import com.lzh.sports.dto.query.CousePlanPagedInput;
import com.lzh.sports.entity.*;
import com.lzh.sports.mapper.*;
import com.lzh.sports.service.CousePlanService;
import com.lzh.sports.tools.BaseContext;
import com.lzh.sports.tools.Extension;
import com.lzh.sports.tools.dto.CurrentUserDto;
import com.lzh.sports.tools.dto.PagedResult;
import com.lzh.sports.tools.exception.CustomException;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 课程安排功能实现类
 */
@Service
public class CousePlanServiceImpl extends ServiceImpl<CousePlanMapper, CousePlan> implements CousePlanService {

    /**
     * 操作数据库AppUser表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMapper;
    /**
     * 操作数据库的CousePlan表mapper对象
     */
    @Autowired
    private CousePlanMapper _CousePlanMpper;


    @Autowired
    private CardMapper _CardMapper;


    @Autowired
    private CouseMapper _CouseMapper;

    /**
     * 操作数据库的MoneyRecord表mapper对象
     */
    @Autowired
    private MoneyRecordMapper _MoneyRecordMpper;

    /**
     * 课程安排分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<CousePlanDto> List(CousePlanPagedInput input) {


        //声明一个支持课程安排查询的(拉姆达)表达式
        LambdaQueryWrapper<CousePlan> queryWrapper = Wrappers.<CousePlan>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, CousePlan::getId, input.getId())
                .eq(input.getCreatorId() != null, CousePlan::getCreatorId, input.getCreatorId());
        //如果前端搜索传入查询条件则拼接查询条件
        if (input.getCouseId() != null) {
            queryWrapper = queryWrapper.eq(CousePlan::getCouseId, input.getCouseId());
        }
        if (input.getBeginTimeRange() != null && input.getBeginTimeRange().stream().count() > 1) {
            queryWrapper = queryWrapper.lt(CousePlan::getBeginTime, input.getBeginTimeRange().get(1));
            queryWrapper = queryWrapper.gt(CousePlan::getBeginTime, input.getBeginTimeRange().get(0));
        }
        if (input.getEndTimeRange() != null && input.getEndTimeRange().stream().count() > 1) {
            queryWrapper = queryWrapper.lt(CousePlan::getEndTime, input.getEndTimeRange().get(1));
            queryWrapper = queryWrapper.gt(CousePlan::getEndTime, input.getEndTimeRange().get(0));
        }
        if (input.getMaxParticipantsStartRange() != null) {
            queryWrapper = queryWrapper.gt(CousePlan::getMaxParticipants, input.getMaxParticipantsStartRange());
        }
        if (input.getMaxParticipantsEndRange() != null) {
            queryWrapper = queryWrapper.lt(CousePlan::getMaxParticipants, input.getMaxParticipantsEndRange());
        }
        if (input.getCurrentEnrollmentsStartRange() != null) {
            queryWrapper = queryWrapper.gt(CousePlan::getCurrentEnrollments, input.getCurrentEnrollmentsStartRange());
        }
        if (input.getCurrentEnrollmentsEndRange() != null) {
            queryWrapper = queryWrapper.lt(CousePlan::getCurrentEnrollments, input.getCurrentEnrollmentsEndRange());
        }


        if (Extension.isNotNullOrEmpty(input.getLocation())) {
            queryWrapper = queryWrapper.eq(CousePlan::getLocation, input.getLocation());
        }

        if (Extension.isNotNullOrEmpty(input.getNotes())) {
            queryWrapper = queryWrapper.eq(CousePlan::getNotes, input.getNotes());
        }
        if (input.getCoachUserId() != null) {
            queryWrapper = queryWrapper.eq(CousePlan::getCoachUserId, input.getCoachUserId());
        }
        if (input.getCardId() != null) {
            queryWrapper = queryWrapper.eq(CousePlan::getCardId, input.getCardId());
        }

        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(CousePlan::getCreationTime);
        //构建一个分页查询的model
        Page<CousePlan> page = new Page<>(input.getPage(), input.getLimit());


        //从数据库进行分页查询获取课程安排数据
        IPage<CousePlan> pageRecords = _CousePlanMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount = _CousePlanMpper.selectCount(queryWrapper);

        //把CousePlan实体转换成CousePlan传输模型
        List<CousePlanDto> items = Extension.copyBeanList(pageRecords.getRecords(), CousePlanDto.class);
        for (CousePlanDto item : items) {

            item.setBelongDate(item.getBeginTime().toLocalDate());
            item.setBeginHMS(Extension.LocalDateTimeConvertString(item.getBeginTime(), "HH:mm"));
            item.setEndHMS(Extension.LocalDateTimeConvertString(item.getEndTime(), "HH:mm"));
            if (item.getBelongDate().isBefore(LocalDate.now())) {
                item.setIsLoseEfficacy(Boolean.TRUE);
            }
            //查询出关联的创建用户信息
            AppUserDto CreatorAppUserDTO = new AppUserDto();
            AppUser CreatorAppUserEntity = _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCreatorId()));
            if (CreatorAppUserEntity != null) {
                BeanUtils.copyProperties(CreatorAppUserDTO, CreatorAppUserEntity);
                item.setCreatorAppUserDto(CreatorAppUserDTO);
            }


            //查询出关联的Card表信息
            CardDto CardDTO = new CardDto();
            Card CardEntity = _CardMapper.selectOne(Wrappers.<Card>lambdaQuery().eq(Card::getId, item.getCardId()));
            if (CardEntity != null) {
                BeanUtils.copyProperties(CardDTO, CardEntity);
                item.setCardDto(CardDTO);
            }


            //查询出关联的Couse表信息
            CouseDto CouseDTO = new CouseDto();
            Couse CouseEntity = _CouseMapper.selectOne(Wrappers.<Couse>lambdaQuery().eq(Couse::getId, item.getCouseId()));
            if (CouseEntity != null) {
                BeanUtils.copyProperties(CouseDTO, CouseEntity);
                item.setCouseDto(CouseDTO);
            }


            //查询出关联的AppUser表信息
            AppUserDto CoachUserDTO = new AppUserDto();
            AppUser CoachUserEntity = _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCoachUserId()));
            if (CoachUserEntity != null) {
                BeanUtils.copyProperties(CoachUserDTO, CoachUserEntity);
                item.setCoachUserDto(CoachUserDTO);
            }

        }

        //返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 查看排班信息
     *
     * @param input
     * @return
     */
    @SneakyThrows
    @Override
    public List<Map<String, Object>> ArrangeList(CousePlanPagedInput input) {
        List<Map<String, Object>> result = new ArrayList<>();

        //声明一个支持课程安排查询的(拉姆达)表达式
        LambdaQueryWrapper<CousePlan> queryWrapper = Wrappers.<CousePlan>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, CousePlan::getId, input.getId())
                .eq(input.getCreatorId() != null, CousePlan::getCreatorId, input.getCreatorId());
        //如果前端搜索传入查询条件则拼接查询条件
        if (input.getCouseId() != null) {
            queryWrapper = queryWrapper.eq(CousePlan::getCouseId, input.getCouseId());
        }

        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(CousePlan::getCreationTime);

        //从数据库进行分页查询获取课程安排数据
        List<CousePlan> cousePlans = _CousePlanMpper.selectList(queryWrapper);


        //按日期分组
        List<LocalDate> collect = cousePlans.stream().map(x -> x.getBeginTime().toLocalDate()).distinct().collect(Collectors.toList());

        for (LocalDate localDate : collect) {
            Map<String, Object> map = new HashMap<>();
            map.put("date", localDate);
            //得到当天的课程的所有教练id并去重
            List<Integer> coachUserIds = cousePlans.stream().filter(x -> x.getBeginTime().toLocalDate().equals(localDate)).map(x -> x.getCoachUserId()).distinct().collect(Collectors.toList());
            //查询出对应的教练信息
            List<AppUser> appUsers = _AppUserMapper.selectList(Wrappers.<AppUser>lambdaQuery().in(AppUser::getId, coachUserIds));
            map.put("coache", appUsers);
            result.add(map);
        }
        return result;
    }

    /**
     * 得到排班中所有的教练信息
     */
    @Override
    public List<AppUserDto> ArrangeDetailAllCoach(CousePlanPagedInput input) {
        //声明一个支持课程安排查询的(拉姆达)表达式
        LambdaQueryWrapper<CousePlan> queryWrapper = Wrappers.<CousePlan>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, CousePlan::getId, input.getId())
                .eq(input.getCreatorId() != null, CousePlan::getCreatorId, input.getCreatorId());
        //如果前端搜索传入查询条件则拼接查询条件
        if (input.getCouseId() != null) {
            queryWrapper = queryWrapper.eq(CousePlan::getCouseId, input.getCouseId());
        }

        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(CousePlan::getCreationTime);

        //从数据库进行分页查询获取课程安排数据
        List<CousePlan> cousePlans = _CousePlanMpper.selectList(queryWrapper);

        //得到所有教练并且去重
        List<Integer> coachUserIds = cousePlans.stream().map(x -> x.getCoachUserId()).distinct().collect(Collectors.toList());
        if (coachUserIds.stream().count() == 0) {
            return new ArrayList<>();
        }
        //查询出对应的教练信息
        List<AppUser> appUsers = _AppUserMapper.selectList(Wrappers.<AppUser>lambdaQuery().in(AppUser::getId, coachUserIds));
        //把AppUser实体转换成AppUserDto传输模型
        List<AppUserDto> items = Extension.copyBeanList(appUsers, AppUserDto.class);
        return items;
    }

    /**
     * 排班明细列表
     */
    @SneakyThrows
    @Override
    public List<CousePlanDto> ArrangeDetailList(CousePlanPagedInput input) {


        CurrentUserDto currentUserDto = BaseContext.getCurrentUserDto();

        //声明一个支持课程安排查询的(拉姆达)表达式
        LambdaQueryWrapper<CousePlan> queryWrapper = Wrappers.<CousePlan>lambdaQuery()
                .eq(input.getId() != null && input.getId() != 0, CousePlan::getId, input.getId())
                .eq(input.getCreatorId() != null, CousePlan::getCreatorId, input.getCreatorId());
        //如果前端搜索传入查询条件则拼接查询条件
        if (input.getCouseId() != null) {
            queryWrapper = queryWrapper.eq(CousePlan::getCouseId, input.getCouseId());
        }

        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(CousePlan::getCreationTime);


        //从数据库进行分页查询获取课程安排数据
        List<CousePlan> cousePlans = _CousePlanMpper.selectList(queryWrapper);


        //把CousePlan实体转换成CousePlan传输模型
        List<CousePlanDto> items = Extension.copyBeanList(cousePlans, CousePlanDto.class);
        for (CousePlanDto item : items) {

            item.setBelongDate(item.getBeginTime().toLocalDate());
            item.setBeginHMS(Extension.LocalDateTimeConvertString(item.getBeginTime(), "HH:mm"));
            item.setEndHMS(Extension.LocalDateTimeConvertString(item.getEndTime(), "HH:mm"));
            if (item.getBelongDate().isBefore(LocalDate.now())) {
                item.setIsLoseEfficacy(Boolean.TRUE);
            }


            //查询出关联的Card表信息
            CardDto CardDTO = new CardDto();
            Card CardEntity = _CardMapper.selectOne(Wrappers.<Card>lambdaQuery().eq(Card::getId, item.getCardId()));
            if (CardEntity != null) {
                BeanUtils.copyProperties(CardDTO, CardEntity);
                item.setCardDto(CardDTO);
            }
            Integer userId = currentUserDto.getUserId();
            if (Extension.isNotNullOrZero(userId)) {
                Double v = GetTargetCard(userId, item.getCardId());
                item.setRemindRechargeMoney(v);
            }

            //查询出关联的Couse表信息
            CouseDto CouseDTO = new CouseDto();
            Couse CouseEntity = _CouseMapper.selectOne(Wrappers.<Couse>lambdaQuery().eq(Couse::getId, item.getCouseId()));
            if (CouseEntity != null) {
                BeanUtils.copyProperties(CouseDTO, CouseEntity);
                item.setCouseDto(CouseDTO);

            }


            //查询出关联的AppUser表信息
            AppUserDto CoachUserDTO = new AppUserDto();
            AppUser CoachUserEntity = _AppUserMapper.selectOne(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getId, item.getCoachUserId()));
            if (CoachUserEntity != null) {
                BeanUtils.copyProperties(CoachUserDTO, CoachUserEntity);
                item.setCoachUserDto(CoachUserDTO);
            }

        }

        //只要传入belong的数据
        if (input.getBelongDate() != null) {
            items = items.stream().filter(x -> x.getBelongDate().equals(input.getBelongDate())).collect(Collectors.toList());
        }

        return items;

    }

    /**
     * 单个课程安排查询
     */
    @SneakyThrows
    @Override
    public CousePlanDto Get(CousePlanPagedInput input) {
        if (input.getId() == null) {
            return new CousePlanDto();
        }
        PagedResult<CousePlanDto> pagedResult = List(input);
        return pagedResult.getTotalCount() > 0 ? pagedResult.getItems().stream().findFirst().get() : new CousePlanDto();
    }

    /**
     * 课程安排创建或者修改
     */
    @SneakyThrows
    @Override
    public CousePlanDto CreateOrEdit(CousePlanDto input) {

        if ("Batch".equals(input.getBehavior())) {
            //如果是批量创建
            List<CousePlanDto> items = new ArrayList<>();
            LocalDate endTime = input.getDateRange().get(1).toLocalDate();
            LocalDate beginTime = input.getDateRange().get(0).toLocalDate();


            LocalDateTime startHour = input.getTimeRange().get(0);
            LocalDateTime endsHour = input.getTimeRange().get(1);


            //得到开始时间和结束时间的总天数
            int days = endTime.getDayOfYear() - beginTime.getDayOfYear();
            if (days > 30) {
                throw new CustomException("批量创建的天数不能超过30天");
            }
            //循环days
            for (int i = 0; i <= days; i++) {
                //声明一个配置实体
                CousePlanDto item = new CousePlanDto();

                item.setCouseId(input.getCouseId());
                item.setCardId(input.getCardId());
                item.setCoachUserId(input.getCoachUserId());
                item.setMaxParticipants(input.getMaxParticipants());
                item.setNotes(input.getNotes());
                item.setCurrentEnrollments(0);
                item.setLocation(input.getLocation());

                LocalDateTime localDateTime = input.getDateRange().get(0).plusDays(i).plusHours(startHour.getHour()).plusMinutes(startHour.getMinute());
                item.setBeginTime(localDateTime);

                LocalDateTime localDateTime2 = input.getDateRange().get(0).plusDays(i).plusHours(endsHour.getHour()).plusMinutes(endsHour.getMinute());
                item.setEndTime(localDateTime2);

                //把配置实体添加到集合中
                items.add(item);
            }
            //查询该教练的配置
            List<CousePlan> CousePlanEntitys = _CousePlanMpper.selectList(Wrappers.<CousePlan>lambdaQuery().eq(CousePlan::getCoachUserId, input.getCoachUserId()));

            //判断items在数据库中是否存在开始时间和结束时间之间的交集数据
            for (CousePlanDto item : items) {

                for (CousePlan cousePlan : CousePlanEntitys) {

                    if (item.getBeginTime().equals(cousePlan.getBeginTime())) {
                        throw new CustomException("批量创建的时间段与已有的时间段有交集" + item.getBeginTime() + "-" + item.getEndTime());

                    }
                    if (item.getEndTime().equals(cousePlan.getEndTime())) {
                        throw new CustomException("批量创建的时间段与已有的时间段有交集" + item.getBeginTime() + "-" + item.getEndTime());
                    }


                    if (item.getBeginTime().isAfter(cousePlan.getBeginTime()) && item.getBeginTime().isBefore(cousePlan.getEndTime())) {
                        throw new CustomException("批量创建的时间段与已有的时间段有交集" + item.getBeginTime() + "-" + item.getEndTime());
                    }
                    if (item.getEndTime().isAfter(cousePlan.getBeginTime()) && item.getEndTime().isBefore(cousePlan.getEndTime())) {
                        throw new CustomException("批量创建的时间段与已有的时间段有交集" + item.getBeginTime() + "-" + item.getEndTime());
                    }

                }
            }
            //批量添加
            saveBatch(Extension.copyBeanList(items, CousePlan.class));

            return null;
        } else {

            //查询该教练的配置
            List<CousePlan> CousePlanEntitys = _CousePlanMpper.selectList(Wrappers.<CousePlan>lambdaQuery().eq(CousePlan::getCoachUserId, input.getCoachUserId()));


            for (CousePlan cousePlan : CousePlanEntitys) {

                if (input.getId() != cousePlan.getId()) {
                    if (input.getBeginTime().isAfter(cousePlan.getBeginTime()) && input.getBeginTime().isBefore(cousePlan.getEndTime())) {
                        throw new CustomException("编辑的时间段与已有的时间段有交集" + input.getBeginTime() + "-" + input.getEndTime());
                    }
                    if (input.getEndTime().isAfter(cousePlan.getBeginTime()) && input.getEndTime().isBefore(cousePlan.getEndTime())) {
                        throw new CustomException("编辑的时间段与已有的时间段有交集" + input.getBeginTime() + "-" + input.getEndTime());
                    }
                    if (input.getBeginTime().equals(cousePlan.getBeginTime())) {
                        throw new CustomException("批量创建的时间段与已有的时间段有交集" + input.getBeginTime() + "-" + input.getEndTime());

                    }
                    if (input.getEndTime().equals(cousePlan.getEndTime())) {
                        throw new CustomException("批量创建的时间段与已有的时间段有交集" + input.getBeginTime() + "-" + input.getEndTime());
                    }
                }

            }
            if (Extension.isNullOrZero(input.getCurrentEnrollments())) {
                input.setCurrentEnrollments(0);
            }


            //声明一个课程安排实体
            CousePlan CousePlan = new CousePlan();

            //把前端传入的input参数拷贝到课程安排实体
            BeanUtils.copyProperties(CousePlan, input);

            //调用数据库的增加或者修改方法
            saveOrUpdate(CousePlan);

            //定义一个返回给前端的课程安排传输模型
            CousePlanDto CousePlanDto = new CousePlanDto();

            //同理把操作的课程安排实体拷贝给课程安排传输模型
            BeanUtils.copyProperties(CousePlanDto, CousePlan);

            //把传输模型返回给前端
            return CousePlanDto;
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

    /**
     * 得到这个用户到达目标卡还需要充值多少money
     */
    private Double GetTargetCard(Integer userId, Integer cardId) {
        //得到用户目前累计的金额
        Double userMoney = GetUserMoney(userId);

        Card card = _CardMapper.selectById(cardId);
        //如果充值卡的money大于用户的money
        if (card.getMoney() > userMoney) {
            return card.getMoney() - userMoney;
        }
        return 0.0;


    }


}
