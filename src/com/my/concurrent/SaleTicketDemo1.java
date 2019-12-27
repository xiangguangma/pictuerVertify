package com.my.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author: Ma Xiangguang
 * date: 2019/12/25 14:03
 * version: 1.0
 */
public class SaleTicketDemo1 {

    /**
     * 三个售票员    卖出     30 装票
     *
     * 如何编写企业级的多线程代码？
     * 固定的编程套路 + 模板
     *
     * 1. 在高内聚低耦合的前提下， 线程       操作      资源类
     *
     * @param args
     */
    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        //Thread(Runnable target, String name)
        //Allocates a new Thread object.

        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"A").start();
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"B").start();
        new Thread(()->{for (int i = 0; i < 40; i++) ticket.sale();},"C").start();

       /* new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"C").start();
*/


    }
}

/**
 * 资源类
 */
class Ticket{
    private int number = 30;
    //List list = new ArrayList();

    Lock lock = new ReentrantLock();

    public void sale(){

        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName() +"\t卖出第:"+ number-- +"\t还剩下：" + number);
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }
    }
}
