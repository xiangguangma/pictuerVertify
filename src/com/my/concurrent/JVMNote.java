package com.my.concurrent;

/**
 * author: Ma Xiangguang
 * date: 2019/12/27 16:18
 * version: 1.0
 */
public class JVMNote {

    public static void main(String[] args) {

        //核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        long maxMemory = Runtime.getRuntime().maxMemory();//jvm最大内存量  物理内存  1/4
        long totalMemory = Runtime.getRuntime().totalMemory();//jvm内存总量  物理内存   1/64

        System.out.println("-Xmx:MAX_MEMORY:" + maxMemory +"(字节)\t" + (maxMemory/(double)1024/1024)+"Mb");
        System.out.println("-Xms:TOTAL_MEMORY:" + totalMemory +"(字节)\t" + (totalMemory/(double)1024/1024)+"Mb");

        //调节  1.初始值和最大值应该一样，防止忽高忽低
        //      2.

        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        byte[] bytes = new byte[40* 1024 * 1024];

        // [GC (Allocation Failure) [PSYoungGen: 1971K->488K(2560K)] 1971K->768K(9728K), 0.0012934 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        // [GC (Allocation Failure) [PSYoungGen: 1971K->488K(2560K)]   //GC类型：YoungGC前新生代占用内存 -> YoungGC后新生代占用内存（新生代总共大小）
        // 1971K->768K(9728K), 0.0012934 secs]   //YoungGC前jvm堆内存占用 -> YoungGC后jvm堆内存占用（JVM堆总大小），YoungGC耗时
        // [Times: user=0.00 sys=0.00, real=0.00 secs]  //YoungGC用户耗时   YoungGC系统耗时   YoungGC实际耗时

        //名称：GC前内存占用 -> GC后内存占用 （读区内存总大小）
        //[Full GC (Allocation Failure) [PSYoungGen: 480K->0K(2560K)] [ParOldGen: 344K->690K(7168K)] 824K->690K(9728K), [Metaspace: 3461K->3461K(1056768K)], 0.0050205 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
        //[Full GC (Allocation Failure) [PSYoungGen: 480K->0K(2560K)]
        // [ParOldGen: 344K->690K(7168K)] 824K->690K(9728K),
        // [Metaspace: 3461K->3461K(1056768K)], 0.0050205 secs]
        // [Times: user=0.00 sys=0.00, real=0.01 secs]  
    }
}
