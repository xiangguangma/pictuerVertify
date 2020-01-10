package com.my.concurrent;

import java.util.concurrent.*;

/**
 * author: Ma Xiangguang
 * date: 2020/1/8 15:25
 * version: 1.0
 * 池化技术：避免创建连接，释放连接的系统开销
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
//        initThreadPool();

        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        // 拒绝策略：
        //AbortPolicy： maxSize + capacity < 任务数，抛 RejectedExecutionException， 默认的策略
        //CallerRunsPolicy:  maxSize + capacity < 任务数 , 超过处理能力的任务，回退给调用者
        //DiscardPolicy： maxSize + capacity < 任务数， 10 进 8 ，其他丢弃， 如果允许任务丢失，是最好的一种策略

        try {

            // 10 个顾客在 窗口办理业务
            for (int i = 0; i < 10; i++) {
                pool.execute(() ->{
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            pool.shutdown();
        }


    }

    private static void initThreadPool() {
    /*
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}

public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}

 public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
 }


public ThreadPoolExecutor(int corePoolSize, //常驻线程数
                          int maximumPoolSize,//能容纳的最大线程数
                          long keepAliveTime,//空闲线程的存活时间
                          TimeUnit unit,//线程的存活时间单位  TimeUnit.MILLISECONDS
                          BlockingQueue<Runnable> workQueue,//工作队列 BlockingQueue 接口的实现类
                          ThreadFactory threadFactory,//线程工厂 Executors.defaultThreadFactory()
                          RejectedExecutionHandler handler) { //拒绝策略  new AbortPolicy()


     */

//        ExecutorService pool = Executors.newFixedThreadPool(5); //池 5 个线程
//        ExecutorService pool = Executors.newSingleThreadExecutor(); //单线程个线程
        ExecutorService pool = Executors.newCachedThreadPool(); //池 可扩展

        try {

            // 10 个顾客在 窗口办理业务
            for (int i = 0; i < 10; i++) {
                pool.execute(() ->{
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            pool.shutdown();
        }
    }
}
