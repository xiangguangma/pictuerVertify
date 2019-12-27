package com.my.concurrent;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * author: Ma Xiangguang
 * date: 2019/12/25 15:18
 * version: 1.0
 *
 * ArrayList   线程不安全
 *
 * 1.故障现象
 *      java.util.ConcurrentModificationException
 * 2.导致原因
 *      多线程并发争抢同一资源类且没加锁
 * 3.解决方法
 *      3.1  new Vector<>();
 *      3.2  Collections.synchronizedList(new ArrayList<>());
 *      3.3  new CopyOnWriteArrayList<>();  //写实复制技术
 *
 * 4.优化建议（同样的错误不想第2次）
 *
 *
 * HashSet  线程不安全
 *
 */
public class NotSafeDemo3 {

    public static void main(String[] args) {
//        listNotSafe();

        setNotSafe();

//        mapNotSafe();
        new Thread(() -> {

            System.out.println(Thread.currentThread().getName());
        },String.valueOf(19)).start();

    }

    private static void mapNotSafe() {
        Map<String,String> map = new ConcurrentHashMap<>(); //new HashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set<String> set = new ConcurrentSkipListSet<>(); //new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        //效率 ArrayList       安全 Vector
        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>()); //new Vector<>(); //new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
