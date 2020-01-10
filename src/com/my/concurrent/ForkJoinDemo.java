package com.my.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * author: Ma Xiangguang
 * date: 2020/1/9 15:02
 * version: 1.0
 *
 *
 */
class MyTask extends RecursiveTask {

    private static final Integer ADJUST_VALUE = 10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int middle) {
        this.begin = this.begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) < ADJUST_VALUE){
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        }else {
            int middle = (begin+end)/2;
            MyTask task1 = new MyTask(begin, middle);
            MyTask task2 = new MyTask(middle+1, end);
            task1.fork();
            task2.fork();
            result =(Integer) task1.join() + (Integer) task2.join();
        }
        return result;
    }
}
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyTask myTask = new MyTask(0, 100);

        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask submit = pool.submit(myTask);

        System.out.println(submit.get());

        pool.shutdown();
    }
}
