package com.init.mini.db.service.impl;

import com.init.mini.db.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Table1ServiceImpl implements TableService {

    private final static Logger log = LoggerFactory.getLogger(Table2ServiceImpl.class);

    @Override
    public void save() {
        String taskName = Table1ServiceImpl.class.toString();
        log.info("{}:任务开始", taskName);
        try {
            // 模拟具体任务
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("{}:任务异常",taskName,e);
        }
    }
}
