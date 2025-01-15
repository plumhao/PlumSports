package com.lzh.sports.dto;
import com.lzh.sports.entity.Enums;
import com.lzh.sports.tools.dto.BaseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
/**
 * 话题类
 */
@Data
public class TopicDto extends BaseDto
{

    /**
     * 标题
     */

    @JsonProperty("Title")
    private String Title;
    /**
     * 封面
     */

    @JsonProperty("Cover")
    private String Cover;
    /**
     * 主图
     */

    @JsonProperty("ImageUrls")
    private String ImageUrls;
    /**
     * 浏览数
     */

    @JsonProperty("ViewCount")
    private Integer ViewCount;
    /**
     * 内容
     */

    @JsonProperty("Content")
    private String Content;
    /**
     * 话题类型
     */

    @JsonProperty("TopicTypeId")
    private Integer TopicTypeId;

    @JsonProperty("TopicTypeDto")
    private TopicTypeDto TopicTypeDto;

    /**
     * 评论集合
     */
    @JsonProperty("CommentDtos")
    private List<CommentDto> CommentDtos;
    /**
     * 是否推荐
     */
    @JsonProperty("IsRecommand")
    private Boolean IsRecommand;
    /**
     * 审核状态
     */
    @JsonProperty("AuditStatus")
    private Integer AuditStatus;

    /**
     * 审核状态描述
     * @return
     */
    @JsonProperty("AuditStatusFormat")
    public String AuditStatusFormat() {
        return  Enums.AuditStatus.GetEnum(AuditStatus).toString();
    }

}
