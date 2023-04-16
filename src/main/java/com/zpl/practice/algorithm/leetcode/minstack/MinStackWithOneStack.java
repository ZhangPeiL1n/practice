package com.zpl.practice.algorithm.leetcode.minstack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2023/4/14 15:34
 **/
public class MinStackWithOneStack {
    /**
     * 栈本体，存放所有元素与 min 的差值
     * 差值大于等于 0，说明比 min 大，相加得到当前值
     * 差值小于 0，说明比 min 小，是上一个 min，减去得到上一个 min
     */
    private final List<Integer> deltaStack = new LinkedList<>();
    int min;

    /**
     * 推入元素
     *
     * @param val 元素
     */
    public void push(int val) {
        if (deltaStack.size() == 0) {
            deltaStack.add(0);
            min = val;
            return;
        }

        int deltaValue = val - min;
        deltaStack.add(deltaValue);
        // 更新 min
        if (deltaValue < 0) {
            min = val;
        }
    }

    /**
     * pop() 删除栈顶但不返回
     */
    public void pop() {
        Integer deltaValue = deltaStack.remove(deltaStack.size() - 1);
        // 得到旧的 min
        if (deltaValue < 0) {
            min = min - deltaValue;
        }
    }

    /**
     * top() 返回栈顶但不删除
     *
     * @return 栈顶元素
     */
    public int top() {
        Integer deltaValue = deltaStack.get(deltaStack.size() - 1);
        if (deltaValue >= 0) {
            return deltaValue + min;
        } else {
            return min;
        }
    }

    /**
     * 获取当前栈内最小值
     *
     * @return 最小值
     */
    public int getMin() {
        return min;
    }
}
