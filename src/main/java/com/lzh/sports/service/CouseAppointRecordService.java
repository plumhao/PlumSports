package com.lzh.sports.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzh.sports.dto.*;
import com.lzh.sports.dto.query.*;
import com.lzh.sports.entity.*;
import com.lzh.sports.tools.dto.*;
import lombok.SneakyThrows;


/**
 * 课程预约记录功能的Service接口的定义清单
 */
public interface CouseAppointRecordService extends IService<CouseAppointRecord> {

    /**
     * 课程预约记录的分页查询方法接口定义
     */
    public PagedResult<CouseAppointRecordDto> List(CouseAppointRecordPagedInput input) ;
    /**
     * 课程预约记录的新增或者修改方法接口定义
     */
    public CouseAppointRecordDto CreateOrEdit(CouseAppointRecordDto input);

     /**
     * 获取课程预约记录信息
     */
    public CouseAppointRecordDto Get(CouseAppointRecordPagedInput input);


    @SneakyThrows
    void Appoint(CouseAppointRecordDto input);

    @SneakyThrows
    void Cancel(CouseAppointRecordDto input);

    @SneakyThrows
    void Check(CouseAppointRecordDto input);

    @SneakyThrows
    void AutoComplete();

    @SneakyThrows
    void AutoCancel();
}
