package com.my.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * author: Ma Xiangguang
 * date: 2019/12/26 16:46
 * version: 1.0
 */
class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("***come in call ***");
        return 1024;
    }
}
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask<>(new MyThread2());

        new Thread(futureTask,"A").start();

        Integer o = (Integer) futureTask.get();
        System.out.println(o);

    }
}
