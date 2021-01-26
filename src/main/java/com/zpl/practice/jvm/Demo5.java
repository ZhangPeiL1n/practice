package com.zpl.practice.jvm;

/**
 * @author ZhangPeiL1n
 * @date 2021/1/26 16:44
 * @Description
 **/
public class Demo5 {
    //jvm 参数
    //-XX:NewSize=104857600
    // -XX:MaxNewSize=104857600
    // -XX:InitialHeapSize=209715200
    // -XX:MaxHeapSize=209715200
    // -XX:SurvivorRatio=8
    // -XX:MaxTenuringThreshold=15
    // -XX:PretenureSizeThreshold=20971520
    // -XX:+UseParNewGC
    // -XX:+UseConcMarkSweepGC
    // -XX:+PrintGCDetails
    // -XX:+PrintGCTimeStamps
    // -Xloggc:gc.log
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30000);
        while (true) {
            loadData();
        }
    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 4; i++) {
            data = new byte[10 * 1024 * 1024];
        }
        data = null;

        byte[] data1 = new byte[10 * 1024 * 1024];
        byte[] data2 = new byte[10 * 1024 * 1024];

        byte[] data3 = new byte[10 * 1024 * 1024];
        data3 = new byte[10 * 1024 * 1024];

        Thread.sleep(1000);
    }
}
