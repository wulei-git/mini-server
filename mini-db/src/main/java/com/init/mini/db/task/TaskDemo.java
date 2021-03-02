package com.init.mini.db.task;

import com.init.mini.common.entity.RefreshTable;
import com.init.mini.common.util.RedisUtil;
import com.init.mini.db.mapper.RefreshTableMapper;
import com.init.mini.db.service.TableService;
import com.init.mini.db.util.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@EnableScheduling
@RefreshScope
public class TaskDemo {

    private final static Logger log = LoggerFactory.getLogger(TaskDemo.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RefreshTableMapper refreshTableMapper;

    /**
     * AtomicInteger是提供原子操作的，线程安全
     * 实现原理：value，使用了volatile关键字
     *      1.保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来
     *      说是立即可见的
     *      2.禁止进行指令重排序。
     *
     * 引出缓存不一致问题：
     *  1.如果一个变量在多个CPU中都存在缓存（一般在多线程编程时才会出现），那么就可能存在缓存不一致的问
     *  2.解决方案
     *      1）通过在总线加LOCK#锁的方式
     *          阻塞了其他CPU对其他部件访问（如内存），从而使得只能有一个CPU能使用这个变量的内存，
     *      使得只能有一个CPU能使用这个变量的内存，其他CPU无法访问内存，导致效率低下
     * 　　  2）通过缓存一致性协议（缓存中使用的共享变量的副本是一致的）
     *          CPU写数据时，如果发现操作的变量是共享变量，即在其他CPU中也存在该变量的副本，会发出
     *      信号通知其他CPU将该变量的缓存行置为无效状态，其他CPU需要读取这个变量时，发现自己缓存中
     *      缓存该变量的缓存行是无效的，那么它就会从内存重新读取。
     *
     * 引出并发编程三问题：
     * 1.原子性：即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。（转账问题）
     * 2.可见性：指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。（高速缓存）
     * 3.有序性：即程序执行的顺序按照代码的先后顺序执行。
     */
    AtomicBoolean restartExecuteOneTimeFlag = new AtomicBoolean(true);

    /**
     * 1.restartExecuteOneTimeFlag 保证系统重启执行一次
     *
     * 2.0/10 保证集群实例都重启完毕公平竞争 IP 锁，获得锁的实例执行任务
     *
     * 3.本方法可以有效避免因数据问题重新发版（数据修复后重启该系统刷新数据，分布式系统的优点）
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void task2() {
        if (restartExecuteOneTimeFlag.get()) {
            restartExecuteOneTimeFlag.set(false);
            task();
        }
    }

    @Scheduled(cron = "0 0 3 6 * ?")
    public void task() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("task1");
        try {
            if (!redisUtil.lockIp("SELF_LOCK")) {
                // 没有获得 IP 锁
                log.warn("本实例未获得任务锁，不执行定时任务");
                return;
            } else {
                log.info("本实例获得任务锁，开始定时任务");
                // 具体任务  todo
                // 任务 1
                stopWatch.stop();
                stopWatch.start("task2");
                // 任务 2
            }
        } catch (Exception e) {
            log.error("每月定时更新任务失败", e);
        } finally {
            redisUtil.remove("SELF_LOCK");
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
        }
    }

    @Scheduled(cron = "0 0 2 *  * ?")
    public void task3() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("task2");
        try {
            if (!redisUtil.lockIp("SELF_LOCK")) {
                // 没有获得 IP 锁
                log.warn("本实例未获得任务锁，不执行定时任务");
                return;
            } else {
                log.info("本实例获得任务锁，开始定时任务");
                // 具体任务  todo
                // 任务 1
                excuteTask3();
                stopWatch.stop();
                stopWatch.start("task2");
                // 任务 2
            }
        } catch (Exception e) {
            log.error("每月定时更新任务失败", e);
        } finally {
            redisUtil.remove("SELF_LOCK");
            stopWatch.stop();
            log.info(stopWatch.prettyPrint());
        }
    }

    private void excuteTask3() {
        List<RefreshTable> refreshTableList = refreshTableMapper.selectList("1");
        for (RefreshTable refreshTable : refreshTableList) {
            if (refreshTable.getServiceName() != null) {
                TableService tableService = SpringUtils.getBean(refreshTable.getServiceName());
                tableService.save();
            }
        }
    }

    private void executeTask4() {
        List<RefreshTable> refreshTableList = refreshTableMapper.selectList("1");
        for (RefreshTable refreshTable : refreshTableList) {
            if (refreshTable.getServiceName() != null) {
//                Class.forName("com.init.mini.db.service.impl"+refreshTable.getServiceName());
            }
        }
    }
}
