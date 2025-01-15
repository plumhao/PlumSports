package com.lzh.sports.tools.amap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Regeocode {
    @JsonProperty("formatted_address")
    private String formatted_address;
    @JsonProperty("addressComponent")
    private AddressComponent addressComponent;
}
