package com.liushuai.treasurechest.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 定时器
 * @Author ls
 * @Date 2019/8/1 20:47
 */
@Component
public class Scheduler {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 每隔2秒执行一次
     */
    @Scheduled(fixedRate = 2000)
    public void interval() {
        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
    }

    /**
     * 固定时间执行
     */
    @Scheduled(cron = "0 02 21 ? * *")
    public void testTasks() {
        System.out.println("固定时间执行定时任务执行时间：" + dateFormat.format(new Date()));
    }
}
