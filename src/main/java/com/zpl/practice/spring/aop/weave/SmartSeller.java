package com.zpl.practice.spring.aop.weave;

/**
 * @author ZhangPeilin
 * @date 2018/10/23
 */

public class SmartSeller implements Seller {
    @Override
    public int sell(String goods, String clientName) {
        System.out.println("SmartSeller: sell" + goods + "to" + clientName);
        return 100;
    }
}
