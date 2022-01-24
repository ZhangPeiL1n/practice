package com.zpl.practice.jvm;

/**
 * @author 张沛霖
 * @date 2021/1/15
 */
public class Demo1 {
    //jvm 参数
    // -XX:NewSize=5242880
    // -XX:MaxNewSize=5242880
    // -XX:InitialHeapSize=10485760
    // -XX:MaxHeapSize=10485760
    // -XX:SurvivorRatio=8
    // -XX:PretenureSizeThreshold=10485760
    // -XX:+UseParNewGC
    // -XX:+UseConcMarkSweepGC
    // -XX:+PrintGCDetails
    // -XX:+PrintGCTimeStamps
    // -Xloggc:gc.log
    public static void main(String[] args) {
        byte[] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
    }
}
