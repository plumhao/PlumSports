package com.lzh.sports.tools.amap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Paths {
    @JsonProperty("distance")
    private String distance;
    @JsonProperty("restriction")
    private String restriction;
    @JsonProperty("steps")
    private List<Steps> steps;
}

