package com.init.mini.web.service.task;

import com.init.mini.common.util.RedisUtil;
import com.init.mini.web.entity.Goods;
import com.init.mini.web.mapper.GoodsMapper;
import com.init.mini.web.service.impl.GoodsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@RefreshScope
public class BaseDataRefreshJob {

    Boolean flag = true;

    public final static Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private GoodsMapper goodsMapper;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void task() {
        if (flag) {
            try {
                if (!redisUtil.lockIp("task_mysql_job")) {
                    log.warn("未获得锁，结束同步");
                    return;
                }
                doTask();
            } catch (Exception e) {
                log.error("更新数据库到 Redis 异常");
            } finally {
                Boolean result = redisUtil.remove("task_mysql_job");
            }

        }

    }

    private void doTask() {
        List<String> deptList = new ArrayList<>();
        for (int i=1; i < 5; i++) {
            deptList.add(String.valueOf(i));
        }
        List<String> planList = new ArrayList<>();
        for (int i=1; i < 20; i++) {
            planList.add(new StringBuilder().append("plan").append(i).toString());
        }
        List<String> targetList = new ArrayList<>();
        for (int i=1; i < 5; i++) {
            targetList.add(new StringBuilder().append("target").append(i).toString());
        }

        List<String> hisDate = new ArrayList<>();

        for (int i=2020; i <=2021; i++) {
            for (int j=1; i <=12; j++) {
                hisDate.add(new StringBuilder().append(i).append("年").append(j).append("月").toString());
            }
        }

        for (String deptCode : deptList) {
            for (String planName : planList) {
                List<Goods> goodsList = goodsMapper.selectByDeptAndPlan(deptCode, planName);
            }
        }
    }


}
