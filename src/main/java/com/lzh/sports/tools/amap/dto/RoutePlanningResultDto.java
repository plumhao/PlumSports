package com.lzh.sports.tools.amap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoutePlanningResultDto {

    /**
     * 结束纬度
     */

    @JsonProperty("ToLatitude")
    private Double ToLatitude;

    /**
     * 结束经度
     */
    @JsonProperty("ToLongitude")
    private Double ToLongitude;

    /**
     * 起始纬度
     */

    @JsonProperty("FromLatitude")
    private Double FromLatitude;

    /**
     * 起始经度
     */
    @JsonProperty("FromLongitude")
    private Double FromLongitude;
}
