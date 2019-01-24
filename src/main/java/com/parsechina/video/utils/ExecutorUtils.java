/*
 * Copyright (c) 2018. eqxiu.com 北京中网易企秀科技有限公司  All rights reserved.
 */

package com.parsechina.video.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linfeng-eqxiu
 * @description 线程池创建工具
 * @date 2018/10/31
 */
public final class ExecutorUtils {

    /**
     * 生成线程池
     *
     * @param name            自定义线程前缀名称
     * @param corePoolSize    核心线程数
     * @param maximumPoolSize 最大线程数
     * @return 线程池
     */
    public static ExecutorService createThreadPool(final String name, final int corePoolSize, final int maximumPoolSize) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new SimpleThreadFactory(name));
    }

    /**
     * 生成线程池
     *
     * @param name     自定义线程前缀名称
     * @param poolSize 线程数
     * @return 线程池
     */
    public static ExecutorService createThreadPool(final String name, final int poolSize) {
        return new ThreadPoolExecutor(poolSize, poolSize,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new SimpleThreadFactory(name));
    }

    /**
     * 生成线程池
     *
     * @param name     自定义线程前缀名称
     * @param poolSize 线程数
     * @return 线程池
     */
    public static ExecutorService createThreadPoolAlive(final String name, final int poolSize) {
        return new ThreadPoolExecutor(poolSize, poolSize,
                0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new SimpleThreadFactory(name));
    }

    /**
     * 创建计划线程池
     *
     * @param name     线程名称
     * @param poolSize 线程池数量
     * @return 计划执行器服务
     */
    public static ScheduledExecutorService createThreadScheduledExecutor(final String name, final int poolSize) {
        return Executors.newScheduledThreadPool(poolSize, new SimpleThreadFactory(name));
    }

    static class SimpleThreadFactory implements ThreadFactory {

        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        SimpleThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = name + "-" +
                    POOL_NUMBER.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

}
