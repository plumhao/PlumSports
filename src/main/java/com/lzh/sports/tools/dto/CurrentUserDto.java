package com.lzh.sports.tools.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CurrentUserDto {

    @JsonProperty("UserId")
    private Integer UserId;
}
