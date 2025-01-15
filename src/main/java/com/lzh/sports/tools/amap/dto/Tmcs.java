package com.lzh.sports.tools.amap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Tmcs {
    @JsonProperty("tmc_status")
    private String tmc_status;
    @JsonProperty("tmc_distance")
    private String tmc_distance;
    @JsonProperty("tmc_polyline")
    private String tmc_polyline;
}
