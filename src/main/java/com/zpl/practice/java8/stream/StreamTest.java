package com.zpl.practice.java8.stream;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author 张沛霖
 * @date 2020/10/20
 */
public class StreamTest {

    public static void main(String[] args) {
        // // 基本使用
        // Stream<String> stream = Stream.of("A", "B", "C", "D");
        // stream.forEach(System.out::println);
        //
        // Stream<String> stream1 = Arrays.stream(new String[]{"A", "B", "C"});
        // stream1.forEach(System.out::println);

        // //Suppelier 来源的 stream
        // Stream<Integer> natual = Stream.generate(new StreamTest().new NatualSupplier());
        // // natual.limit(20).forEach(System.out::println);
        // System.out.println(natual.limit(20).count());

        // //Stream map 方法
        // Stream<Integer> s = Stream.of(1, 2, 3, 4, 5);
        // Stream<Integer> s2 = s.map(n -> n * n);
        // s2.forEach(System.out::println);

        // //Stream map 连用
        // Stream.of("Apple ","pear "," ORANGE"," BaNaNa").map(String::trim).map(String::toLowerCase).forEach(System.out::println);

        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).filter(n -> n % 2 != 0).forEach(System.out::println);


    }

    class NatualSupplier implements Supplier<Integer> {
        int n = 0;

        @Override
        public Integer get() {
            n++;
            return n;
        }
    }
}
