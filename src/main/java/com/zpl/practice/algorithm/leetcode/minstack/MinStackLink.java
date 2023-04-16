package com.zpl.practice.algorithm.leetcode.minstack;

/**
 * 最小栈
 * 链表法
 *
 * @author ZhangPeiL1n
 * @date 2023/4/16 16:07
 **/
public class MinStackLink {

    /**
     * 链表尾/栈顶
     */
    private Node tail;

    public static void main(String[] args) {
        MinStackLink minStackLink = new MinStackLink();
        minStackLink.push(2147483646);
        minStackLink.push(2147483646);
        minStackLink.push(2147483646);
        System.out.println(minStackLink.top());
        minStackLink.pop();
        System.out.println(minStackLink.getMin());
        minStackLink.pop();
        System.out.println(minStackLink.getMin());
        minStackLink.pop();
        minStackLink.push(2147483647);
    }

    /**
     * 推入元素
     *
     * @param val 元素
     */
    public void push(int val) {
        this.tail = new Node(val, tail);
    }

    /**
     * pop() 删除栈顶但不返回
     */
    public void pop() {
        Node tailNode = this.tail;
        // 向前移动尾指针
        this.tail = tailNode.prevNode;
        tailNode.removeSelf();
    }

    /**
     * top() 返回栈顶但不删除
     *
     * @return 栈顶元素
     */
    public int top() {
        return this.tail.value;
    }

    /**
     * 获取当前栈内最小值
     *
     * @return 最小值
     */
    public int getMin() {
        return this.tail.minNode.value;
    }

    /**
     * 链表元素
     */
    public static class Node {
        /**
         * 值
         */
        private final int value;

        /**
         * 前一个值
         */
        private Node prevNode;

        /**
         * 最小值指针
         */
        private Node minNode;

        Node(int value, Node tail) {
            this.value = value;
            this.prevNode = tail;

            if (tail == null || value < tail.minNode.value) {
                this.minNode = this;
            } else {
                this.minNode = tail.minNode;
            }
        }

        private void removeSelf() {
            this.prevNode = null;
            this.minNode = null;
        }
    }
}
