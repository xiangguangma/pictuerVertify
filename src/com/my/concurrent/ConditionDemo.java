package com.my.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author: Ma Xiangguang
 * date: 2019/12/26 16:10
 * version: 1.0
 */
class ShareData{

    //标志位
    private int number = 1; //A:1   B:2   C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
            //1.判断
            while (number != 1){
                //wait
                c1.await();
            }
            //2.干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() +"\t" + i);
            }
            //3.唤醒   先改标志位
            number = 2;
            //通知2
            c2.signal();

        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try{
            //1.判断
            while (number != 2){
                //wait
                c2.await();
            }
            //2.干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() +"\t" + i);
            }
            //3.唤醒   先改标志位
            number = 3;
            //通知3
            c3.signal();

        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try{
            //1.判断
            while (number != 3){
                //wait
                c3.await();
            }
            //2.干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() +"\t" + i);
            }
            //3.唤醒   先改标志位
            number = 1;
            //如何通知 下一个？？
            c1.signal();

        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 多线程之间按照顺序调用，实现A->B->C
 * 三个线程要求如下：
 *
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 求10轮
 */
public class ConditionDemo {
    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print5();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print10();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.print15();
            }
        },"C").start();

//        Thread.State

    }

}
