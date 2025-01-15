package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;
import java.lang.reflect.InvocationTargetException;


/**
 * 用户功能的Service接口的定义清单
 */
public interface AppUserService extends IService<AppUser> {

    /**
     * 用户的分页查询方法接口定义
     */
    public PagedResult<AppUserDto> List( AppUserPagedInput input);
    /**
     * 用户的新增或者修改方法接口定义
     */
    public AppUserDto CreateOrEdit( AppUserDto input);


    /**
     * 获取用户信息
     */
    public AppUserDto Get(AppUserPagedInput input);

    /**
     * 用户登录
     */
    public String SignIn(AppUserDto input);

    /**
     * 注册
     */
    public AppUserDto Register(AppUserDto input);


    AppUserDto ForgetPassword(AppUserDto input) throws InvocationTargetException, IllegalAccessException;
}
