package com.my.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author: Ma Xiangguang
 * date: 2020/1/7 14:59
 * version: 1.0
 */
class AirConditionTest{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();

    public void increment(){
        lock.lock();
        try{

            //
            while (number != 0){
                c1.await();
            }

            //干活
            number++;

            //通知唤醒
            System.out.println(Thread.currentThread().getName()+"\t" +number);
            c1.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void decrement(){
        lock.lock();
        try{

            //
            while (number == 0){
                c1.await();
            }

            //干活
            number--;

            //通知唤醒
            System.out.println(Thread.currentThread().getName()+"\t" +number);
            c1.signalAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}

public class ThreadLockDemo {
    public static void main(String[] args) {

        AirConditionTest air = new AirConditionTest();

        new Thread(() -> {for (int i = 0; i < 10; i++) air.increment(); },"A").start();
        new Thread(() -> {for (int i = 0; i < 10; i++) air.increment(); },"B").start();
        new Thread(() -> {for (int i = 0; i < 10; i++) air.decrement(); },"C").start();
        new Thread(() -> {for (int i = 0; i < 10; i++) air.decrement(); },"D").start();

    }
}
