package com.my.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author: Ma Xiangguang
 * date: 2019/12/26 11:27
 * version: 1.0
 *
 * 题目：两个线程，可以操作初始值为零的一个变量
 * 实现一个线程对该变量加 1 ，一个线程对该变量减 1 ，
 * 实现交替，来10轮，变量初始值为0
 *
 * 1. 高内聚低耦合，线程操作资源类
 * 2. 判断/干活/通知
 * 3. 防止虚假唤醒  不能用if 判断， 只能用while
 *
 * 总结：线程编程套路 + while判断 + 新版写法
 */
public class ProConsumerDemo5 {

    public static void main(String[] args) {

        AirCondition airCondition = new AirCondition();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();








    }
}


class AirCondition{
    private int number = 0;

    // 旧版
    /*public synchronized void increment() throws Exception{
        //1.判断
        while (number != 0) {
            this.wait();
        }
        //2.干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        //3.通知
        this.notifyAll();
    }

    public synchronized void decrement() throws Exception{
        while (number == 0) {
            this.wait();
        }

        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        this.notifyAll();
    }*/

    //新版
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void decrement() throws Exception{

        lock.lock();
        try{
            while (number == 0) {
//                this.wait();  //要跟synchronized
                condition.await();
            }

            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);

//            this.notifyAll();
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }

    public void increment() throws Exception{
        lock.lock();

        try{
            while (number != 0) {
                condition.await();
            }

            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);

//            this.notifyAll();
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

}