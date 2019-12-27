package com.my.concurrent;

/**
 * author: Ma Xiangguang
 * date: 2019/12/27 10:11
 * version: 1.0
 */
public class MyObject {

    public static void main(String[] args) {
        Object object = new Object();
//        System.out.println(object.getClass().getClassLoader().getParent().getParent());
//        System.out.println(object.getClass().getClassLoader().getParent());
        System.out.println(object.getClass().getClassLoader());

        System.out.println("***************");

        //我爸是李刚，有事儿找我爸
        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader());

    }
}
