package com.lzh.sports.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzh.sports.entity.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 话题表对应的Mapper
 */
@Mapper
public interface TopicMapper  extends BaseMapper<Topic> {

}
