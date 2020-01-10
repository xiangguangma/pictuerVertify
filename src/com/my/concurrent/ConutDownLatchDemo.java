package com.my.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * author: Ma Xiangguang
 * date: 2020/1/7 17:54
 * version: 1.0
 * CountDownLatch： 作减法
 */
public class ConutDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + "\t 离开教室" );
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 关门走人" );



    }

    private static void closeDoor() {
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {

                System.out.println(Thread.currentThread().getName() + "\t 离开教室" );
            },String.valueOf(i)).start();
        }
        System.out.println(Thread.currentThread().getName() + "\t 关门走人" );
    }
}
