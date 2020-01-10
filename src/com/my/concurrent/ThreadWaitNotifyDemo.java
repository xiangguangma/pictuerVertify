package com.my.concurrent;

/**
 * author: Ma Xiangguang
 * date: 2020/1/7 14:21
 * version: 1.0
 */

class AirConditioner{

    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        //1.判断
        while (number != 0){
            this.wait();
        }
        //2.干活
        number++;

        //3.通知
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //1.判断
        while (number == 0){
            this.wait();
        }

        //2.干活
        number--;

        //3.通知
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }
}

/**
 * 线程间的通信：
 * 题目：现在两个线程，可以操做初始值为一个变量
 * 实现一个线程对该变量加1，一个线程对变量减1
 * 实现交替10轮，
 */
public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {

        AirConditioner airConditioner = new AirConditioner();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
