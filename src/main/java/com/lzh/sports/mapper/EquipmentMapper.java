package com.lzh.sports.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzh.sports.entity.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * 器材设备表对应的Mapper
 */
@Mapper
public interface EquipmentMapper  extends BaseMapper<Equipment> {

}
