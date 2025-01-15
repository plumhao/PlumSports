package com.lzh.sports.tools.amap;

import com.lzh.sports.tools.HttpUtils;
import com.lzh.sports.tools.amap.dto.DrivingResultDto;
import com.lzh.sports.tools.amap.dto.RegeoInput;
import com.lzh.sports.tools.amap.dto.RegeoResultDto;
import com.lzh.sports.tools.amap.dto.RoutePlanningResultDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

/**
 * 高德地图工具类
 */
public class MapUtils {


     private static  String Key= "a5659a8c968c461996feb605a1c7d7a2";

    /**
     * 根据经纬度获取当前道路的导航
     */
    @SneakyThrows
    public static DrivingResultDto RoutePlanning (RoutePlanningResultDto input) {

       String url="https://restapi.amap.com/v5/direction/driving?origin="+input.getFromLongitude()+","+input.getFromLatitude()+"&destination="+input.getToLongitude()+","+input.getToLatitude()+"&show_fields=tmcs&key="+Key;

        String resultJsonString = HttpUtils.Get(url);
        ObjectMapper mapper = new ObjectMapper();

        DrivingResultDto result= mapper.readValue(resultJsonString, DrivingResultDto.class);
        return result;

    }

    /**
     * 根据经纬度去获取地址的信息
     */
    @SneakyThrows
    public static RegeoResultDto Regeo(RegeoInput input)
    {
        String url="https://restapi.amap.com/v3/geocode/regeo?output=json&location="+input.getLongitude()+","+input.getLatitude()+"&radius=1000&key="+Key;
        String resultJsonString = HttpUtils.Get(url);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //FAIL_ON_NULL_FOR_PRIMITIVES的，它定义了是否允许类的属性为空值：
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        RegeoResultDto result= mapper.readValue(resultJsonString, RegeoResultDto.class);
        return result;
    }

}
