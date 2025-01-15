package com.lzh.sports.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 系统通知表
 */
@Data
@TableName("`SysNotice`")
public class SysNotice extends BaseEntity {

    /**
     * 通知标题
     */
    @JsonProperty("Name")
    @TableField("Name")
    private String Name;
    /**
     * 作者
     */
    @JsonProperty("Author")
    @TableField("Author")
    private String Author;
    /**
     * 通知内容
     */
    @JsonProperty("Content")
    @TableField("Content")
    private String Content;
    /**
     * 附件
     */
    @JsonProperty("File")
    @TableField("File")
    private String File;

}
