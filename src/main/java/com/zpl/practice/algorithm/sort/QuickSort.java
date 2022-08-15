package com.zpl.practice.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 快速排序
 *
 * @author zhangpeilin
 */
public class QuickSort {

    public static Integer[] quickSort(Integer[] source) {
        if (source.length == 0) {
            return source;
        }
        int index = 0;
        int value = source[index];
        List<Integer> small = new LinkedList<>();
        List<Integer> big = new LinkedList<>();
        for (int i = 1; i < source.length; i++) {
            Integer target;
            if ((target = source[i]) <= value) {
                small.add(target);
            } else {
                big.add(target);
            }
        }
        ArrayList<Integer> result = new ArrayList<>(small.size() + 1 + big.size());
        result.addAll(Arrays.asList(quickSort(small.toArray(new Integer[0]))));
        result.add(value);
        result.addAll(Arrays.asList(quickSort(big.toArray(new Integer[0]))));
        return result.toArray(new Integer[0]);
    }


    public static void main(String[] args) {
        Integer[] integer = new Integer[]{2, 4, 1, 3, 5, 6, 7};
        System.out.println(Arrays.toString(quickSort(integer)));
    }
}
