package com.zpl.practice.algorithm.leetcode.minstack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhangPeiL1n
 * @date 2023/4/14 15:34
 **/
public class MinStackWithTwoStack {
    /**
     * 栈本体，存放所有元素
     */
    private final List<Integer> stack = new LinkedList<>();
    /**
     * 维护每个栈深时的最小元素
     * 动态规划的思想，记录每个栈深的最小值
     */
    private final List<Integer> minStack = new LinkedList<>();

    /**
     * 推入元素
     *
     * @param val 元素
     */
    public void push(int val) {
        stack.add(val);
        int minVal = val;
        if (minStack.size() > 0) {
            minVal = Math.min(minStack.get(minStack.size() - 1), val);
        }
        minStack.add(minVal);
    }

    /**
     * pop() 删除栈顶但不返回
     */
    public void pop() {
        stack.remove(stack.size() - 1);
        minStack.remove(minStack.size() - 1);
    }

    /**
     * top() 返回栈顶但不删除
     *
     * @return 栈顶元素
     */
    public int top() {
        return stack.get(stack.size() - 1);
    }

    /**
     * 获取当前栈内最小值
     *
     * @return 最小值
     */
    public int getMin() {
        return minStack.get(minStack.size() - 1);
    }
}
