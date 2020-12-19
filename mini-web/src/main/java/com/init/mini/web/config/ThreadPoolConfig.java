package com.init.mini.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

// @Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
// 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类
// 进行扫描，并用于构建bean定义，初始化Spring容器。
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Value("${thread-pool.threadNamePrefix}")
    private String threadNamePrefix;

    @Value("${thread-pool.corePoolSize}")
    private Integer corePoolSize;

    @Value("${thread-pool.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${thread-pool.keepAliveSeconds}")
    private Integer keepAliveSeconds;

    @Value("${thread-pool.queueCapacity}")
    private Integer queueCapacity;

    @Value("${thread-pool.shutdown-wait-complete}")
    private Boolean waitComplete;

    @Value("${thread-pool.rejectedPolicy}")
    private String rejectedPolicy;

    @Bean("thread-pool-group01")
    public Executor threadPool() {
        return initThreadPool(this.threadNamePrefix,this.corePoolSize,
                                this.maxPoolSize, this.keepAliveSeconds,
                                this.queueCapacity, this.waitComplete,
                                this.rejectedPolicy);
    }

    private Executor initThreadPool(String threadNamePrefix, int corePoolSize, int maxPoolSize,
                                    int keepAliveSeconds, int queueCapacity, boolean waitComplete,
                                    String rejectedPolicy) {
        ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor();
        tpte.setCorePoolSize(corePoolSize);
        tpte.setMaxPoolSize(maxPoolSize);
        tpte.setKeepAliveSeconds(keepAliveSeconds);
        tpte.setQueueCapacity(queueCapacity);
        tpte.setThreadNamePrefix(threadNamePrefix);
        // 当调度器 shutdown 被调用时等待当前任务完成
        tpte.setWaitForTasksToCompleteOnShutdown(waitComplete);
        // AbortPolicy:直接抛出个RejectedExecutionException异常，也不执行这个任务
        // CallerRunsPolicy在任务被拒绝添加后，会用调用execute函数的上层线程去执行被拒绝的任务。
        // DiscardPolicy:会让被线程池拒绝的任务直接抛弃，不会抛异常也不会执行。
        // DiscardOldestPolicy:会抛弃任务队列中最旧的任务也就是最先加入队列的，再把这个新任务添加进去。
        // 自定义拒绝策略:MyRejectedExecutionHandler implements RejectedExecutionHandler
//        tpte.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        tpte.initialize();
        return tpte;
    }

}
