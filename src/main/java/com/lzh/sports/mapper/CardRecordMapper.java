package com.lzh.sports.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzh.sports.entity.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员变动记录表对应的Mapper
 */
@Mapper
public interface CardRecordMapper  extends BaseMapper<CardRecord> {

}
