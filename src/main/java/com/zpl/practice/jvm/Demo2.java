package com.zpl.practice.jvm;

/**
 * @author 张沛霖
 * @date 2021/1/21
 */
public class Demo2 {
    //jvm 参数
    //-XX:NewSize=10485760
    // -XX:MaxNewSize=10485760
    // -XX:InitialHeapSize=20971520
    // -XX:MaxHeapSize=20971520
    // -XX:SurvivorRatio=8
    // -XX:MaxTenuringThreshold=15
    // -XX:PretenureSizeThreshold=10485760
    // -XX:+UseParNewGC
    // -XX:+UseConcMarkSweepGC
    // -XX:+PrintGCDetails
    // -XX:+PrintGCTimeStamps
    // -Xloggc:gc.log
    public static void main(String[] args) {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[128 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];

        array3 = new byte[2 * 1024 * 1024];
        array3 = new byte[2 * 1024 * 1024];
        array3 = new byte[128 * 1024];
        array3 = null;


        byte[] array4 = new byte[2 * 1024 * 1024];
    }
}