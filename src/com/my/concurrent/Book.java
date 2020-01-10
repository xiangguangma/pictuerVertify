package com.my.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * author: Ma Xiangguang
 * date: 2020/1/8 17:05
 * version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Book {

    private String name;
    private String author;
    private Integer price;
    private Integer count;


    public static void main(String[] args){
        Book book = new Book();
        book.setName("java");
        book.setPrice(234);
        System.out.println(book);

        Book book1 = new Book();
        book1.setName("c++").setAuthor("maxiangguang").setCount(23);

        System.out.println(book1);
    }
}
