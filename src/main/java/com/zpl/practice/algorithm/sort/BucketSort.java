package com.zpl.practice.algorithm.sort;

import java.util.Arrays;

/**
 * 桶排序
 * <p>
 * 桶排序（Bucket Sort）的原理是：
 * 根据目标数组target中最大的数字max，创建一个容量为max+1的桶数组bucket，且数组中的所有元素初始化为0
 * 排序时，遍历目标数组target，用bucket来记录target中元素出现的次数，
 * 对应关系为target的值为bucket的角标
 * 例如，target[0] = 1,那么就将bucket[1]的值+1，
 * target[1] = 5,那么就将bucket[5]的值+1
 * 这个bucket就像map一样，key是target的值，value是出现次数，秒啊
 *
 * @author ZhangPeilin
 * @date 2019/03/25
 */

public class BucketSort {
    /**
     * 这里的max也可以通过一个比较来获得
     *
     * @param target 要排序的目标数组
     * @param max    数组中最大的元素+1
     */
    private static void bucketSort(int[] target, int max) {
        //桶数组
        int[] buckets;
        buckets = new int[max];
        //计数
        //计数是正如上面所说，将bucket中对应的角标+1来统计出现次数
        for (int i : target) {
            buckets[i] += 1;
        }

        //放数
        //这里的逻辑是，遍历bucket，然后在target中依次放入bucket的角标值
        //i是bucket的角标，j是target的角标
        for (int i = 0, j = 0; i < max; i++) {
            while (buckets[i] > 0) {
                buckets[i] -= 1;
                target[j] = i;
                //target的角标增加，相当于依次放入target中
                j += 1;
            }
        }

    }

    public static void main(String[] args) {
        int[] array = {5, 5, 5, 2, 4, 4, 3, 2, 1};
        bucketSort(array, 6);
        System.out.println(Arrays.toString(array));
    }
}
