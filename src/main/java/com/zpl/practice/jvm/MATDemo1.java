package com.zpl.practice.jvm;

import java.util.ArrayList;

/**
 * @author 张沛霖
 * @date 2021/2/10
 */
public class MATDemo1 {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Data> datas = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            datas.add(new Data());
        }
        Thread.sleep(1 * 60 * 60 * 1000);
    }

    static class Data {

    }
}
