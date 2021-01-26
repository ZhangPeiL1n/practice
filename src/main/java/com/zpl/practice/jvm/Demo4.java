package com.zpl.practice.jvm;

/**
 * @author ZhangPeiL1n
 * @date 2021/1/26 15:17
 * @Description
 **/
public class Demo4 {
    // jvm 参数
    // -XX:NewSize=104857600
    // -XX:MaxNewSize=104857600
    // -XX:InitialHeapSize=209715200
    // -XX:MaxHeapSize=209715200
    // -XX:SurvivorRatio=8
    // -XX:MaxTenuringThreshold=15
    // -XX:PretenureSizeThreshold=3145728
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
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data = null;

        Thread.sleep(1000);
    }
}
