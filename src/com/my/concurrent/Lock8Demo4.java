package com.my.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * author: Ma Xiangguang
 * date: 2019/12/25 16:21
 * version: 1.0
 *
 * 8 Lock
 * 1.标准访问， sendEmail,sendSMS
 *      一个 对象里面如果有多个synchronized方法，某一个时刻内，只有一个线程去调用其中的一个synchronized方法，
 *      其他线程只能等待，换句话说，某个时刻内，只能有唯一一个线程去访问这些synchronized方法
 *
 *      锁的是当前对象this，被锁定后，其他线程不能进入到当前对象的其他synchronized方法
 *
 * 2.暂停4秒在邮件方法, sendEmail,sendSMS
 * 3.新增普通方法sayHello，sayHello，sendEmail
 *      加一个普通方法和同步锁没有关系
 *
 * 4.两部手机， sendSMS，sendEmail
 *      换成两个对象后，不是同一把锁，情况立刻就变了
 *
 * 5.两静态同步方法，一手机，sendEmail,sendSMS
 * 6.两静态同步方法，两手机，sendEmail,sendSMS
 *      static 修饰的是全局锁，类锁     非static的 对象锁
 *
 *      synchronized实现同步锁基础：Java中的每个对象可以作为一个锁
 *      具体有以下3中形式：
 *      对于普通同步方法，锁的是当前对象
 *      对于同步方法块，锁的是synchronized括号内配置的内容
 *      对于静态的同步方法，锁的是当前类的Class对象
 *
 * 7.1个静态同步方法，1个普通同步方法，一手机， sendSMS，sendEmail
 * 8.1个静态同步方法，1个普通同步方法，两手机, sendSMS，sendEmail
 */
public class Lock8Demo4 {
    public static void main(String[] args) {

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
//                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(() -> {
            try {
//                phone.sendSMS();
                phone2.sendSMS();
//                phone.sayHello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();

    }
}

class Phone{
    public static synchronized void sendEmail() throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("***sendEmail***");

    }
    public synchronized void sendSMS() throws Exception{

        System.out.println("***sendSMS***");

        synchronized (this.getClass()){

        }

    }

    public void sayHello(){
        System.out.println("***sayHello***");
    }

}
