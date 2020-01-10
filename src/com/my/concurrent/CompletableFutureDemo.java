package com.my.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * author: Ma Xiangguang
 * date: 2020/1/9 17:45
 * version: 1.0
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "\t 没有返回，update mysql ok");
//        });
//        try {
//            completableFuture.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 有返回");
            int a  = 10 / 0;
            return 1024;
        });
        try {
            completableFuture1.whenComplete((t,u) -> {
                System.out.println("*** t: " + t);
                System.out.println("*** u: " + u);
            }).exceptionally( f -> {
                System.out.println("*** exception: " + f.getMessage());
                return 4444;
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
