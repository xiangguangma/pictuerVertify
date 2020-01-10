package com.my.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * author: Ma Xiangguang
 * date: 2020/1/8 10:14
 * version: 1.0
 */
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock lock = this.readWriteLock.writeLock();

    public void put(String key, Object value){


        //锁住资源
        lock.lock();
        try{

            System.out.println(Thread.currentThread().getName()+"\t ---> 写入数据\t" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t ---> 写入完成");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放资源
            lock.unlock();
        }


    }

    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"\t  读取数据");
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName()+"\t  读取完成 \t" + result);
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt+"", tempInt+"");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
