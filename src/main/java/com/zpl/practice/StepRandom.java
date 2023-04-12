package com.zpl.practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 生成随机脚步顺序
 *
 * @author ZhangPeiL1n
 * @date 2023/2/27 11:46
 **/
public class StepRandom {

    private static final String[] STEPS = {"No.1 Pas de bourree", "No.2 Cross step", "No.3 Loose leg", "No.4 Shuffle", "No.5 Salsa", "No.6 Criss cross", "No.7 Swirl", "No.8 Farmer", "No.9  Back step", "No.10 Jack in the box", "No.11 Heel step", "No.12 Side walk", "No.13 Stomping", "No.14 Cross road", "No.15  Skate", "No.16 Twist", "No.17 Kick step", "No.18 Peter paul", "No.19 Marjory", "No.20 Bart simpson"};

    public static String[] random(int stepCount, int fixedIndex) {
        assert stepCount >= 0;
        String[] resultSteps = new String[stepCount];
        if (fixedIndex < 1) {
            Random random = new Random();
            // System.out.println(random.nextInt(STEPS.length));
            resultSteps[0] = STEPS[random.nextInt(STEPS.length)];
        } else {
            // 输入 index 按序号来，数组从0开始
            resultSteps[0] = STEPS[fixedIndex - 1];
        }
        for (int i = 1; i < stepCount; i++) {
            Random random = new Random();
            // System.out.println(random.nextInt(STEPS.length));
            resultSteps[i] = STEPS[random.nextInt(STEPS.length)];
        }
        return resultSteps;
    }

    public static void main(String[] args) {
        // // 脚步总数
        // int stetCount = 2;
        // // 哪个脚步固定,固定的就放在首位
        // int fixedIndex = -1;
        // String[] result = random(stetCount, fixedIndex);
        // System.out.println(Arrays.toString(Arrays.stream(result).toArray()));

        enumAll();
    }

    public static void enumAll() {
        // 方向
        final String[] direction = {"Left", "Right", "Front", "Back", "Turn"};
        // 取几个方向
        int size = 4;
        // 结果
        String[] result = new String[size];

        // 去重，是否允许多个方向重复
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < direction.length; i++) {
            countMap.put(i, 0);
        }
        countMap.put(direction.length, 0);
        // 重复的最大个数，比方说四个方向最多出现两个左，否则可能出现四个左的情况
        int maxSize = 2;
        // 递归层数
        int recursionCount = 0;
        recursion(direction, result, recursionCount, size, countMap, maxSize);
        System.out.println(countMap.get(direction.length));
        // for (int i = 0; i < size; i++) {
        //     Random random = new Random();
        //     result[i] = direction[random.nextInt(direction.length)];
        // }
    }

    public static void recursion(String[] direction, String[] result, int index, int targetSize, Map<Integer, Integer> coutMap, int maxSize) {
        // 下一层递归的层数
        int nextIndex = index + 1;
        for (int i = 0; i < direction.length; i++) {
            int currentCount = coutMap.get(i);
            // 重复方向次数判断
            if (currentCount < maxSize) {
                result[index] = direction[i];
                coutMap.put(i, currentCount + 1);
                // 递归出口
                if (nextIndex == targetSize) {
                    System.out.println(Arrays.toString(result));
                    coutMap.put(direction.length, coutMap.get(direction.length) + 1);
                } else {
                    recursion(direction, result, nextIndex, targetSize, coutMap, maxSize);
                }
                // 恢复重复方向计数
                coutMap.put(i, currentCount);
            }
        }
    }
}

