package com.lzh.sports.tools;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lzh.sports.SysConst;
import com.lzh.sports.tools.dto.CurrentUserDto;
import java.time.format.TextStyle;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.*;
/**
 * 扩展方法
 */
public class Extension {

    /**
     * 判断字符串是否为空或者空串
     */
    public static boolean isNotNullOrEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    /**
     * 判断字符串是否是空或者空串
     */
    public static boolean isNullOrEmpty(String str) {
        return !isNotNullOrEmpty(str);
    }
    /**
     * 判断Id是否存在
     */
    public static boolean isNullOrZero(Integer value) {
        return value==null||value==0;
    }
    /**
     * 判断Id是否存在
     */
    public static boolean isNotNullOrZero(Integer value) {
        return !(value==null||value==0);
    }
    /**
     *把Timestamp转换成LocalDateTime
     */
    public static LocalDateTime TimestampToLocalDateTime(Timestamp timestamp) {
        // 将Timestamp转换为Instant对象
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());

        // 指定转换格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 将Instant对象转换为ZonedDateTime对象，指定时区为系统默认时区
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());

        // 将ZonedDateTime对象转换为LocalDateTime对象
        return zonedDateTime.toLocalDateTime();
    }
    /**
     *把Timestamp转换成LocalDate
     */
    public static LocalDate TimestampToLocalDate(Timestamp timestamp) {
        if(timestamp==null)
        {
            return null;
        }
        // 将Timestamp转换为Instant对象
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());

        // 指定转换格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 将Instant对象转换为ZonedDateTime对象，指定时区为系统默认时区
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());

        // 将ZonedDateTime对象转换为LocalDateTime对象
        return zonedDateTime.toLocalDate();
    }
    /**
     *根据日期得到今天是星期几
     */
    public static String GetWeek(LocalDate localDate) {
      return localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINESE);
    }
    /**
     * 根据timestamp获取时间
     */
    public static String TimeStapConvertDateTimeString(Timestamp timestamp)
    {
        if(timestamp==null){
            return "";
        }
       String format=  format="yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(timestamp.getTime());
       return sdf.format(date);
    }
    /**
     * 获取当前的TimeStap
     */
    public static Timestamp GetCurrentTimeStap()
    {
        long milliseconds = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(milliseconds);
       return timestamp;
    }
    /**
     * LocalDateTime戳转换成字符串
     */
    public static String LocalDateTimeConvertString(LocalDateTime localDateTime,String format){
        if(localDateTime==null){
            return "";
        }
        if(format==null) {
            format = format = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return localDateTime.format(formatter);
    }

    /**
     * 时间戳转换成字符串
     */
    public static String TimestapConvertString(Timestamp timestamp,String format){

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(timestamp.getTime());
        return sdf.format(date);
    }
    /**
     * 根据timestamp获取时间
     */
    public static String TimeStapConvertDateString(Timestamp timestamp)
    {

        String   format="yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(timestamp.getTime());
        return sdf.format(date);
    }
    /**
     * 得到当前时间
     */
    public static String GetNowTimeFormat(String format)
    {
        if(format.isEmpty())
        {
            format="yyyy-MM-dd HH:mm:ss";
        }
        LocalDateTime currentTime = LocalDateTime.now();

        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        // 将当前时间格式化为字符串
        String currentTimeString = currentTime.format(formatter);
        return currentTimeString;
    }

    /**
     * 得到当前日期
     */
    public static String GetNowDateFormat()
    {
        return GetNowTimeFormat("yyyy-MM-dd");
    }


    /**
     * 获取当前的用户信息 根据token
     */
    public static String  getTokenInfo(String token,String key)
    {
        token=token.replace("Bearer ","");
        DecodedJWT jwt=JWTUtils.getTokenInfo(token);
        Map<String, Claim> v= jwt.getClaims();
        return   jwt.getClaims().get(key).asString();
    }


    /**
     * 根据token获取当前的用户id
     */
    public static Integer getTokenUserId(String token)
    {
        return  Integer.parseInt(getTokenInfo(token, SysConst.UserIdClaim));
    }
    /**
     * 如果存在登录token则获取请求的用户
     * @param token
     * @return
     */
    public static CurrentUserDto GetCurrentUserByToken(String token)
    {
        CurrentUserDto currentUser = new CurrentUserDto();
        currentUser.setUserId(getTokenUserId(token));
        return currentUser;
    }

    /**
     * 拷贝一个列表到另外一个列表
     */
    public static <T> List<T> copyBeanList(List<?> resourceList,Class<T> target) {
        List<T> targetList = new LinkedList<>();
        if (resourceList.isEmpty()) {
            return targetList;
        }
        resourceList.forEach(e -> {
            T o = null;
            try {
                o = target.newInstance();
            } catch (InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
            org.springframework.beans.BeanUtils.copyProperties(e, o);
            targetList.add(o);
        });
        return targetList;
    }
    // 打乱列表实现方法
    public static <T> void RadomList(List<T> list) {
        int size = list.size();
        Random random = new Random();

        for(int i = 0; i < size; i++) {
            // 获取随机位置
            int randomPos = random.nextInt(size);

            // 当前元素与随机元素交换
            T temp = list.get(i);
            list.set(i, list.get(randomPos));
            list.set(randomPos, temp);
        }
    }
     /**
     * 生成订单编号
     * @return
     */
    public static String GenerateOrderNumber() {
        // 获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());

        // 生成随机数
        Random random = new Random();
        int randomNum = random.nextInt(900000) + 100000;

        // 拼接订单编号
        return dateStr + randomNum;
    }
  /**
     * 计算2点的经纬度的距离(单位m)
     */
    public  static double HaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // 地球半径，单位为千米
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000; // 将结果转换为米
    }
  
    /**
     * List转换成ArrayList

     */
    public static ArrayList<?> AsArrayList(List<?>resourceList){
        ArrayList<?> targetList = new ArrayList<>();
        if (resourceList.isEmpty()) {
            return targetList;
        }
        return new ArrayList<>(resourceList);
    }

}
