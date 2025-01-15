package com.lzh.sports.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzh.sports.entity.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员卡表对应的Mapper
 */
@Mapper
public interface CardMapper  extends BaseMapper<Card> {

}
