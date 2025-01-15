package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer Id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime CreationTime;

    @TableField(fill = FieldFill.INSERT)
    private Integer CreatorId;
}
