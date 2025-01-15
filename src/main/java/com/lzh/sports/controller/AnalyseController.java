package com.lzh.sports.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lzh.sports.entity.*;
import com.lzh.sports.mapper.*;
import com.lzh.sports.tools.BaseContext;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 决策分析
 */
@RestController()
@RequestMapping("/Analyse")
public class AnalyseController {
    @Autowired()
    private AppUserMapper _AppUserMapper;
    @Autowired()
    private TopicMapper _TopicMapper;
    @Autowired()
    private TopicRecordMapper _TopicRecordMapper;
    @Autowired()
    private TopicTypeMapper _TopicTypeMapper;
    @Autowired()
    private TopicCollectMapper _TopicCollectMapper;
    @Autowired()
    private CommentMapper _CommentMapper;
    @Autowired()
    private EquipmentRentMapper _EquipmentRentMapper;
    @Autowired()
    private EquipmentMapper _EquipmentMapper;
    @Autowired()
    private CouseAppointRecordMapper _CouseAppointRecordMapper;
    @Autowired()
    private CouseMapper _CouseMapper;

    /**
     * 我的数据
     */
    @RequestMapping(value = "/MyData", method = RequestMethod.POST)
    @SneakyThrows
    public Map<String, Object> MyData() {
        Map<String, Object> data = new HashMap<>();
        Integer userId = BaseContext.getCurrentUserDto().getUserId();
        //查询我的收藏
        var topicCollects = _TopicCollectMapper.selectList(null).stream().filter(x -> x.getUserId().equals(userId)).toList();
        //得到我的收藏个数
        data.put("MyCollectCount", topicCollects.size());
        //查询我的评论数
        var comments = _CommentMapper.selectList(null).stream().filter(x -> x.getCreatorId().equals(userId)).toList();
        data.put("MyCommentCount", comments.size());
        //查询我的话题数
        var topics = _TopicMapper.selectList(null).stream().filter(x -> x.getCreatorId().equals(userId)).toList();
        data.put("MyTopicCount", topics.size());
        //查询我的被人浏览量
        List<Integer> topicIds = topics.stream().map(x -> x.getId()).distinct().toList();
        var topicRecords = _TopicRecordMapper.selectList(null).stream().filter(x -> topicIds.contains(x.getTopicId())).toList();
        data.put("MyBrowseCount", topicRecords.size());


        return data;

    }


    /**
     * 统计数据
     */
    @RequestMapping(value = "/DataCollect", method = RequestMethod.POST)
    @SneakyThrows
    public Map<String, Object> DataCollect() {
        Map<String, Object> data = new HashMap<>();
        Integer userId = BaseContext.getCurrentUserDto().getUserId();
        //教练人数
        long CoacheCount = _AppUserMapper.selectCount(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getRoleType, Enums.RoleType.教练.index()));
        data.put("CoacheCount", CoacheCount);
        //学生人数
        long UserCount = _AppUserMapper.selectCount(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getRoleType, Enums.RoleType.用户.index()));
        data.put("UserCount", UserCount);

        //所有话题数量
        long topicCounted = _TopicMapper.selectList(null).stream().count();
        data.put("TopicCounted", topicCounted);
        //所有待审核的话题数量
        long topicAuditCounted = _TopicMapper.selectList(null).stream().filter(x -> x.getAuditStatus() == Enums.AuditStatus.待审核.index()).count();
        data.put("topicAuditCounted", topicAuditCounted);
        //得到总器械数
        long EquipmentCounted = _EquipmentMapper.selectList(null).stream().count();
        data.put("EquipmentCounted", EquipmentCounted);

        //设备没有归还的数量
        long NoEquipmentRent = _EquipmentRentMapper.selectList(Wrappers.<EquipmentRent>lambdaQuery().eq(EquipmentRent::getReturnTime, null)).stream().count();
        data.put("NoEquipmentRent", NoEquipmentRent);

        //总课程数
        long CouseCount = _CouseMapper.selectList(null).stream().count();
        data.put("CouseCount", CouseCount);

        LocalDateTime nowBeginTime = LocalDate.now().atTime(0, 0, 0);
        LocalDateTime nowEndTime = nowBeginTime.plusHours(23).plusMinutes(59).plusSeconds(59);
        //今天预约课程的人数
        long todayAppointCount = _CouseAppointRecordMapper.selectList(Wrappers.<CouseAppointRecord>lambdaQuery()
                .ge(CouseAppointRecord::getBeginTime, nowBeginTime).le(CouseAppointRecord::getBeginTime, nowEndTime)).stream().count();
        data.put("todayAppointCount", todayAppointCount);
        return data;
    }


    /**
     * 得到最近12个所有机械租界次数
     */
    @RequestMapping(value = "/RentRecordEchart", method = RequestMethod.POST)
    @SneakyThrows
    public List<Map<String, Object>> RentRecordEchart() {
        List<Map<String, Object>> result = new ArrayList<>();


        //得到所有的设备
        List<Equipment> equipments = _EquipmentMapper.selectList(null);

        //得到最近12个月所有的租借记录
        LocalDateTime ago12 = LocalDate.now().minusMonths(12).atTime(0, 0, 0);
        List<EquipmentRent> equipmentRents = _EquipmentRentMapper.selectList(Wrappers.<EquipmentRent>lambdaQuery()
                .ge(EquipmentRent::getRentalTime, ago12));
        for (Equipment equipment : equipments) {
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("equipment", equipment);

            List<String> months = new ArrayList<>();
            List<Integer> monthValues = new ArrayList<>();
            //循环12次
            for (int i = 0; i < 12; i++) {
                LocalDateTime start = LocalDate.now().minusMonths(i).atTime(0, 0, 0);
                LocalDateTime end = LocalDate.now().minusMonths(i - 1).atTime(0, 0, 0);
                //得到这个月的租借记录
                List<EquipmentRent> rents = equipmentRents.stream().filter(x -> x.getEquipmentId().equals(equipment.getId()) && x.getRentalTime().isAfter(start) && x.getRentalTime().isBefore(end)).toList();
                //得到年月
                String format = format = "yyyy-MM ";

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
                months.add(start.format(formatter));
                monthValues.add(rents.size());
            }
            stringObjectHashMap.put("months", months);
            stringObjectHashMap.put("monthValues", monthValues);

            result.add(stringObjectHashMap);
        }
        return result;


    }

    /**
     * 老师预约的人次
     */
    @RequestMapping(value = "/CouseTeacherAnalyseEchart", method = RequestMethod.POST)
    @SneakyThrows
    public List<Map<String, Object>> CouseTeacherAnalyseEchart() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        //查询所有的课程评价了订单
        List<CouseAppointRecord> orderInfos = _CouseAppointRecordMapper.selectList(Wrappers.<CouseAppointRecord>lambdaQuery()
                .in(CouseAppointRecord::getCouseAppointStatus, Enums.CouseAppointStatus.完成.index()));
        //查询所有的老师
        List<AppUser> appUsers = _AppUserMapper.selectList(Wrappers.<AppUser>lambdaQuery().eq(AppUser::getRoleType, Enums.RoleType.教练.index()));
        //循环所有老师
        for (AppUser appUser : appUsers) {
            Map<String, Object> data = new HashMap<>();
            data.put("name", appUser.getUserName());
            long counted = orderInfos.stream().filter(x -> x.getCoachUserId() == appUser.getId()).count();
            data.put("value", counted);
            dataList.add(data);
        }
        //倒叙排列
        Collections.sort(dataList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return Double.valueOf(o2.get("value").toString()).compareTo(Double.valueOf(o1.get("value").toString()));
            }
        });

        return dataList;
    }

    /**
     * 课程预约的人次
     */
    @RequestMapping(value = "/CouseAnalyseEchart", method = RequestMethod.POST)
    @SneakyThrows
    public List<Map<String, Object>> CouseAnalyseEchart() {
        List<Map<String, Object>> dataList = new ArrayList<>();
        //查询所有的课程评价了订单
        List<CouseAppointRecord> orderInfos = _CouseAppointRecordMapper.selectList(Wrappers.<CouseAppointRecord>lambdaQuery()
                .in(CouseAppointRecord::getCouseAppointStatus, Enums.CouseAppointStatus.完成.index()));
        //查询所有的老师
        List<Couse> couses = _CouseMapper.selectList(null);
        //循环所有老师
        for (Couse couse : couses) {
            Map<String, Object> data = new HashMap<>();
            data.put("couse", couse);
            long counted = orderInfos.stream().filter(x -> x.getCouseId() == couse.getId()).count();
            data.put("value", counted);
            dataList.add(data);
        }
        //倒叙排列
        Collections.sort(dataList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return Double.valueOf(o2.get("value").toString()).compareTo(Double.valueOf(o1.get("value").toString()));
            }
        });

        return dataList;
    }

}
