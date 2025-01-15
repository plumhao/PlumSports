package com.lzh.sports.tools.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户之间的相似度
 */
@AllArgsConstructor
@Data
public class UserCalculateCosineSimilarity {
    private Integer UserId1;

    private Integer UserId2;

    private Double Similarity;

}
