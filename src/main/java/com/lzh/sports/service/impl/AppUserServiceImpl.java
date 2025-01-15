package com.lzh.sports.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzh.sports.SysConst;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.mapper.*;
import com.lzh.sports.service.*;
import com.lzh.sports.tools.Extension;
import com.lzh.sports.tools.JWTUtils;
import com.lzh.sports.tools.dto.PagedResult;
import com.lzh.sports.tools.exception.CustomException;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户功能实现类
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements AppUserService {


    /**
     * 操作数据库的用户表mapper对象
     */
    @Autowired
    private AppUserMapper _AppUserMpper;

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
     * 用户分页查询
     */
    @SneakyThrows
    @Override
    public PagedResult<AppUserDto> List(AppUserPagedInput input) {


        //声明一个支持用户查询的(拉姆达)表达式
        LambdaQueryWrapper<AppUser> queryWrapper = Wrappers.<AppUser>lambdaQuery()
                .eq(input.getId() != null, AppUser::getId, input.getId())
                .eq(input.getCreatorId() != null, AppUser::getCreatorId, input.getCreatorId());

        //如果前端搜索传入Name不为空,则进行精确查询
        if (Extension.isNotNullOrEmpty(input.getName())) {
            queryWrapper = queryWrapper.eq(AppUser::getName, input.getName());
        }


        if (Extension.isNotNullOrEmpty(input.getCardNo())) {
            queryWrapper = queryWrapper.eq(AppUser::getCardNo, input.getCardNo());
        }

        //如果前端搜索传入getPhoneNumber不为空,则进行精确查询
        if (Extension.isNotNullOrEmpty(input.getPhoneNumber())) {
            queryWrapper = queryWrapper.eq(AppUser::getPhoneNumber, input.getPhoneNumber());
        }

        //如果前端搜索传入getRoleType不为空,则进行精确查询
        if (input.getRoleType() != null) {
            queryWrapper = queryWrapper.eq(AppUser::getRoleType, input.getRoleType());
        }


        //按创建时间从大到小排序 最新的显示在最前面
        queryWrapper = queryWrapper.orderByDesc(AppUser::getCreationTime);
        //构建一个分页查询的model
        Page<AppUser> page = new Page<>(input.getPage(), input.getLimit());

        //从数据库进行分页查询获取用户数据
        IPage<AppUser> pageRecords = _AppUserMpper.selectPage(page, queryWrapper);

        //获取所有满足条件的数据行数
        Long totalCount = _AppUserMpper.selectCount(queryWrapper);

        //把用户实体转换成用户传输模型
        List<AppUserDto> items = Extension.copyBeanList(pageRecords.getRecords(), AppUserDto.class);
        for (AppUserDto item : items) {
            //查询累计余额
            Double CumulativeMoney = GetUserMoney(item.getId());
            item.setCumulativeMoney(CumulativeMoney);

            if (Extension.isNotNullOrZero(item.getCardId())) {
                //查询出关联的会员卡信息
                CardDto cardDto = new CardDto();
                Card card = _CardMpper.selectById(item.getCardId());
                BeanUtils.copyProperties(cardDto, card);
                item.setCardDto(cardDto);
            }
            item.setNextGradeRechageMoney(GetTargetCard(item.getId()));


        }
        //返回一个分页结构给前端
        return PagedResult.GetInstance(items, totalCount);

    }

    /**
     * 用户创建或者修改
     */
    @SneakyThrows
    @Override
    public AppUserDto CreateOrEdit(AppUserDto input) {

        //如果是创建并且角色是用户
        if (Extension.isNullOrZero(input.getId()) && input.getRoleType().equals(Enums.RoleType.用户.index())) {
            input.setCardNo("C" + System.currentTimeMillis());
            input.setMoney(0.0);
        }
        //声明一个用户实体
        AppUser AppUser = new AppUser();

        //把前端传入的input参数拷贝到用户实体
        BeanUtils.copyProperties(AppUser, input);

        //如果前端没有给新密码,则把原始密码继续赋值
        if (Extension.isNotNullOrEmpty(input.getPassword())) {
            input.setPassword(input.getOrginPassword());
        }

        //调用数据库的增加或者修改方法
        saveOrUpdate(AppUser);

        //定义一个返回给前端的用户传输模型
        AppUserDto AppUserDto = new AppUserDto();

        //同理把操作的用户实体拷贝给用户传输模型
        BeanUtils.copyProperties(AppUserDto, AppUser);

        //把传输模型返回给前端
        return AppUserDto;
    }


    /**
     * 单个用户查询
     */
    @SneakyThrows
    @Override
    public AppUserDto Get(AppUserPagedInput input) {
        if (input.getId() == null) {
            return new AppUserDto();
        }
        PagedResult<AppUserDto> pagedResult = List(input);
        return pagedResult.getTotalCount() > 0 ? pagedResult.getItems().stream().findFirst().get() : new AppUserDto();
    }


    /**
     * 登录
     */
    public String SignIn(AppUserDto input) {
        LambdaQueryWrapper<AppUser> queryWrapper = Wrappers.<AppUser>lambdaQuery()
                .eq(Extension.isNotNullOrEmpty(input.getUserName()), AppUser::getUserName, input.getUserName())
                .eq(Extension.isNotNullOrEmpty(input.getPassword()), AppUser::getPassword, input.getPassword())
                .eq(input.getRoleType() != null, AppUser::getRoleType, input.getRoleType());

        List<AppUser> items = _AppUserMpper.selectList(queryWrapper);
        if (items.stream().count() == 0) {
            throw new CustomException("请检查登录的账号或者密码,角色是否都正确!");
        }
        Map<String, String> map = new HashMap<>();
        map.put(SysConst.UserIdClaim, items.get(0).getId().toString());
        map.put(SysConst.RoleTypeClaim, items.get(0).getRoleType().toString());
        String token = JWTUtils.getToken(map);
        return token;
    }

    /**
     * 注册
     */
    @Override
    public AppUserDto Register(AppUserDto input) {


        LambdaQueryWrapper<AppUser> queryWrapper = Wrappers.<AppUser>lambdaQuery()
                .eq(input.getUserName() != null, AppUser::getUserName, input.getUserName())
                .eq(input.getPhoneNumber() != null, AppUser::getPhoneNumber, input.getPhoneNumber());
        //根据用户名称或用户电话号码进行查询
        AppUser appUser = _AppUserMpper.selectOne(queryWrapper);
        if (appUser != null) {
            throw new CustomException("该用户名或手机号已经存在!");
        }
        return CreateOrEdit(input);
    }

    /**
     * 找回密码
     */
    @Override
    public AppUserDto ForgetPassword(AppUserDto input) throws InvocationTargetException, IllegalAccessException {


        LambdaQueryWrapper<AppUser> queryWrapper = Wrappers.<AppUser>lambdaQuery()
                .eq(input.getUserName() != null, AppUser::getUserName, input.getUserName())
                .eq(input.getPhoneNumber() != null, AppUser::getPhoneNumber, input.getPhoneNumber());
        //根据用户名称或用户电话号码进行查询
        AppUser appUser = _AppUserMpper.selectOne(queryWrapper);
        if (appUser == null) {
            throw new CustomException("请确认你输入的用户名或手机号是否正确");
        }
        //定义一个返回给前端的用户传输模型
        AppUserDto AppUserDto = new AppUserDto();

        //同理把操作的用户实体拷贝给用户传输模型
        BeanUtils.copyProperties(AppUserDto, appUser);

        //把传输模型返回给前端
        return AppUserDto;

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
        items = items.stream().filter(x -> x.getTransactionType().equals("充值") || x.getTransactionType().equals("退款")).toList();
        double sum = items.stream().mapToDouble(x -> x.getAmount()).sum();
        //只查询类型为1 3 的
        return sum;

    }

    /**
     * 得到这个用户到达目标卡还需要充值多少money
     */
    private Double GetTargetCard(Integer userId) {

        //得到用户目前累计的金额
        Double userMoney = GetUserMoney(userId);

        //得到所有卡
        List<Card> cards = _CardMpper.selectList(null);
        //得到金额比用户累计金额大的卡
        List<Card> list = cards.stream().filter(x -> userMoney < x.getMoney()).sorted((x, y) -> x.getMoney().compareTo(y.getMoney())).toList();
        if (list.stream().count() > 0) {
            //得到第一个卡
            Card card = list.stream().findFirst().get();
            return card.getMoney() - userMoney;
        }

        return 0.0;


    }

}
