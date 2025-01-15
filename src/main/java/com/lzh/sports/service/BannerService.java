package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;


/**
 * 门户封面功能的Service接口的定义清单
 */
public interface BannerService extends IService<Banner> {

    /**
     * 门户封面的分页查询方法接口定义
     */
    public PagedResult<BannerDto> List(BannerPagedInput input) ;
    /**
     * 门户封面的新增或者修改方法接口定义
     */
    public BannerDto CreateOrEdit(BannerDto input);

     /**
     * 获取门户封面信息
     */
    public BannerDto Get(BannerPagedInput input);


}
