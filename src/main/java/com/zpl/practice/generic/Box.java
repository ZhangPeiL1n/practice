package com.zpl.practice.generic;

/**
 * 测试泛型类
 *
 * @param <T> 泛型
 * @author zhangpeilin
 */
public class Box<T> {
    private T genericProperty;

    private T anotherGenericProperty;

    public Box() {
    }

    public Box(T genericProperty) {
        this.genericProperty = genericProperty;
    }

    public Box(T genericProperty, T anotherGenericProperty) {
        this.genericProperty = genericProperty;
        this.anotherGenericProperty = anotherGenericProperty;
    }

    public T getGenericProperty() {
        return genericProperty;
    }

    public void setGenericProperty(T genericProperty) {
        this.genericProperty = genericProperty;
    }

    public T getAnotherGenericProperty() {
        return anotherGenericProperty;
    }

    public void setAnotherGenericProperty(T anotherGenericProperty) {
        this.anotherGenericProperty = anotherGenericProperty;
    }

    public void print() {
        System.out.println(genericProperty);
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>(1);
        Box<String> stringBox = new Box<>("a");
    }
}
