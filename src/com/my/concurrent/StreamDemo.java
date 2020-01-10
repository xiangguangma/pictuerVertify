package com.my.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author: Ma Xiangguang
 * date: 2020/1/9 11:02
 * version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
class User{
    private Integer id;
    private String name;
    private Integer age;

}

/**
 * 题目： 请按照给出数据，找出同时间满足一下条件的用户，也即一下条件全部满足
 *         偶数ID 且 年龄大于 24 且 用户名转为大写 且用户名字母倒序排序
 *         只输出一个用户名字
 */

public class StreamDemo {

    public static void main(String[] args){

        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        List<User> users = Arrays.asList(u1,u2,u3,u4,u5);
//        functionInterface();

        List<Integer> list = Arrays.asList(1,2,3);
//        list.stream().map(x -> x * 2 ).collect(Collectors.toList()).forEach(System.out:: println);

        users.stream().filter(u -> u.getId() % 2 == 0)
                .filter(t -> t.getAge() > 24)
                .map(m -> m.getName().toUpperCase())
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .forEach(System.out::println);



    }

    private static void functionInterface() {
        //函数型接口
        Function<String, Integer> function = String::length;
        System.out.println(function.apply("abc"));


        //断定型接口
        Predicate<String> predicate = String::isEmpty;
        System.out.println(predicate.test(""));

        //消费型接口
        Consumer<String> consumer = System.out:: println;
        consumer.accept("AAA");

        //供给型接口
        Supplier<String> supplier = () -> "aaa";
        System.out.println(supplier.get());
    }
}
