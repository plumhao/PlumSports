package com.lzh.sports.tools.amap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Steps {
    @JsonProperty("instruction")
    private String instruction;
    @JsonProperty("orientation")
    private String orientation;
    @JsonProperty("road_name")
    private String road_name;

    @JsonProperty("step_distance")
    private String step_distance;
    @JsonProperty("tmcs")
    private List<Tmcs> tmcs;
}
