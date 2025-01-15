package com.lzh.sports.job;


import com.lzh.sports.service.CouseAppointRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderJob {


    @Autowired()
    private CouseAppointRecordService _OrderService ;
    /**
     * 每5秒执行一次检测是否存在订单存在完成的记录
     */
    @Scheduled(cron = "*/5 * * * * ?")
    private void AutoComplete() {
        long nowDateTime = System.currentTimeMillis();
        System.out.println("固定定时任务执行:--->"+nowDateTime+"，此任务为每5秒执行一次,任务名称:每5秒执行一次检测是否存在订单存在完成的记录");
        _OrderService.AutoComplete();
    }
    /**
     * 每5秒执行一次检测是否存在订单存在未签到的记录
     */
    @Scheduled(cron = "*/5 * * * * ?")
    private void AutoCancel() {
        long nowDateTime = System.currentTimeMillis();
        System.out.println("固定定时任务执行:--->"+nowDateTime+"，此任务为每5秒执行一次,任务名称:每5秒执行一次检测是否存在订单存在未签到的记录");
        _OrderService.AutoCancel();
    }
}
