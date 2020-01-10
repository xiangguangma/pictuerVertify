package com.my.concurrent;

/**
 * author: Ma Xiangguang
 * date: 2019/12/30 15:14
 * version: 1.0
 */
class MyNumber{
    volatile int number = 10;

    public void addTo1205(){
        this.number = 1205;
    }
}

/**
 * JMM = 可见性（通知机制）
 */
public class JMMDemo {

    public static void main(String[] args) {

        MyNumber myNumber = new MyNumber();

        new Thread(() -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.addTo1205();
            System.out.println(Thread.currentThread().getName()+"\t Update number, number value "+myNumber.number);

        },"AAA").start();

        while (myNumber.number==10){
            //需要有直跟通知机制告诉main线程，number已经修改为1025，跳出while
        }
        System.out.println(Thread.currentThread().getName()+"\t Update number, number value "+myNumber.number);
    }
}
