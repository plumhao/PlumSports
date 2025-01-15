package com.lzh.sports.tools.amap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressComponent {
    @JsonProperty("city")
    private String city;
    @JsonProperty("province")
    private String province;
    @JsonProperty("adcode")
    private String adcode;
    @JsonProperty("district")
    private String district;
    @JsonProperty("towncode")
    private String towncode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("township")
    private String township;



}
