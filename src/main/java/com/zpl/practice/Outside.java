package com.zpl.practice;

/**
 * @author 张沛霖
 * @date 2020/9/9
 */
public class Outside {
    // class Inside {}

    static class Inside {
    }

    public static void main(String[] args) {
        Outside.Inside inside;
        // Outside outside = new Outside();
        inside = new Outside.Inside();
    }
}
