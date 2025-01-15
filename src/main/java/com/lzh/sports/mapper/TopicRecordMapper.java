package com.lzh.sports.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzh.sports.entity.TopicRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 话题浏览记录表对应的Mapper
 */
@Mapper
public interface TopicRecordMapper  extends BaseMapper<TopicRecord> {

}
