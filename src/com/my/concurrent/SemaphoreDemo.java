package com.my.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * author: Ma Xiangguang
 * date: 2020/1/8 9:43
 * version: 1.0
 * Semaphore: 信号量，目的：1、用于多个共享资源的互斥使用， 2、用于并发线程数的控制
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); //模拟资源类有3个空车位

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    //获取许可，要么获取成功，信号量减1，要么一直等待下去，直到资源释放，或超时
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢占到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t 离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放信号量 ，信号量加1，唤醒等待线程
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}
