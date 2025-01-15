package com.lzh.sports.tools.amap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegeoInput {
    /**
     * 纬度
     */

    @JsonProperty("Latitude")
    private Double Latitude;

    /**
     * 经度
     */
    @JsonProperty("Longitude")
    private Double Longitude;
}
