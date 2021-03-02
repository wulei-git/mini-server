package com.init.mini.web.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@EnableScheduling

/**
 * 1.使用 Quartz、elastic-job、xxl-job 等开源第三方定时任务框架，适合分布式项目应用
 * 2.基于 java.util.Timer 定时器，实现类似闹钟的定时任务
 * 3.使用 Spring 提供的一个注解： @Schedule，开发简单，使用比较方便
 */
@Component
public class ScheduledTask {

    /**
     * fixedRate 是 long 类型，表示任务执行的间隔毫秒数，以上代码中的定时任务每 3 秒执行一次。
     */
    @Scheduled(initialDelay = 1000, fixedRate = 3000)
    public void scheduledTask() {
        System.out.println("任务执行时间：" + LocalDateTime.now());
    }

    /**
     * 每天凌晨 2 点执行
     */
    @Scheduled(cron = "0 0 2 * * ? ")
    public void scheduledTask2() {
        System.out.println("任务执行时间cron2：" + LocalDateTime.now());
    }

    /**
     * 每月凌晨 3 点执行
     */
    @Scheduled(cron = "0 0 3 6 * ? ")
    public void scheduledTask3() {
        System.out.println("任务执行时间cron3：" + LocalDateTime.now());
    }

    /**
     * 启动最近跨分钟执行 0/10最近 整 10 分钟倍数执行 0 10 20 30 40 50 分
     */
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void scheduledTask4() {
        System.out.println("任务执行时间cron4：" + LocalDateTime.now());
    }

    /**
     * 0 15 10 ? * MON-FRI 表示周一到周五每天上午 10:15 执行
     */
    @Scheduled(cron = "0 15 10 ? * MON-FRI")
    public void scheduledTask5() {
        System.out.println("任务执行时间cron5：" + LocalDateTime.now());
    }

    /**
     * 0 15 10 ? 6L 2019-2020 ：表示 2019-2020 年的每个月的最后一个星期五上午 10:15 执行
     *
     * 0 0 10,14,16 * * ? ：每天上午 10 点，下午 2 点，4 点执行
     *
     * 0 0/30 9-17 * * ? ：朝九晚五工作时间内每半小时执行
     */
}
