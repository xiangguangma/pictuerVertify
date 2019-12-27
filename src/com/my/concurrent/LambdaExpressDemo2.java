package com.my.concurrent;

/**
 * author: Ma Xiangguang
 * date: 2019/12/25 14:55
 * version: 1.0
 *
 * 1.函数式编程
 * 口诀：拷贝中括号     写死右箭头     落地大括号
 *
 * 2.@FunctionalInterface
 * 3.default
 * 4.static
 */
public class LambdaExpressDemo2 {
    public static void main(String[] args) {
        /*Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello 12306");
            }

            @Override
            public int add(int a, int b) {
                return 0;
            }
        };*/

        Foo foo = (int a,int b) -> {
            System.out.println("hello 12306");
            return a+b;
        };


        System.out.println(foo.add(2, 6));

        System.out.println(foo.mul(10, 2));

        System.out.println(Foo.sub(9, 8));


    }
}

@FunctionalInterface
interface Foo{
//    void sayHello();

    int add(int a, int b);

    default int mul(int a, int b){
        return a * b;
    }

    static int sub(int a,int b){
        return a - b;
    }
}
