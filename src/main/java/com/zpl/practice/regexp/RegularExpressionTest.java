package com.zpl.practice.regexp;

/**
 * 正则表达式练习
 *
 * @author 张沛霖
 * @date 2020/9/4
 */
public class RegularExpressionTest {

    public void test() {
        int a = 0;
        setting(a);
        System.out.println(a);
    }

    public void setting(int a) {
        a = 1;
        System.out.println(a);
    }


    public static void main(String[] args) {
        // String s = "18603595366".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        // System.out.println(s);

        // // String s = "156-5268-7634".replace("-","");
        // String s = "15652687634";
        // String regExp = "1[^0]\\d{9}";
        // Pattern pattern = Pattern.compile(regExp);
        // Matcher matcher = pattern.matcher(s);
        // System.out.println(matcher.matches());


        // String regExp = "([0-9\\-]+)\\d{4}(\\d{2})";

        // String s = "010-87997690";
        // String replaceStr = s.replaceAll("([0-9-]+)\\d{4}(\\d{2})","$1****$2");
        // System.out.println(replaceStr);

        RegularExpressionTest regularExpressionTest = new RegularExpressionTest();
        regularExpressionTest.test();


    }
}
