package com.ren.TcpGateWayNetty;

import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Ren
 * */
public class BusinessExecutorService{
    private static volatile BusinessExecutorService businessExecutorService;

    /**
     * 构造的这个业务线程池使用了单例模式
     * */
    private BusinessExecutorService(){}

    public static BusinessExecutorService getInstance() {
        if (businessExecutorService == null) {
            synchronized (BusinessExecutorService.class) {
                if (businessExecutorService == null) {
                    return new BusinessExecutorService();
                }
            }
        }
        return businessExecutorService;
    }

    /**
     *
     * @param corePoolSize 核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime 线程保持激活时间
     * @param unit 空闲线程等待单元
     * @param workQueueSize 工作队列大小
     * @return 线程池
     */
    public ExecutorService getThreadPoolExecutor(int corePoolSize,int maximumPoolSize,
                                                 long keepAliveTime,TimeUnit unit,
                                                 int workQueueSize) {
        if (corePoolSize <= 0 || corePoolSize > maximumPoolSize ||
                workQueueSize <= 0) {
            throw new IllegalArgumentException("getThreadPoolExecutor argument illegal.");
        }
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, new LinkedBlockingQueue<>(workQueueSize), new ThreadPoolExecutor.AbortPolicy());
    }

    /*创建一个核心工作线程数量为5的业务线程池*/
//    static ExecutorService businessGroup = Executors.newFixedThreadPool(5);

}
