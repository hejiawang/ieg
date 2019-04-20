package com.wang.jmonkey.test.modules.test;

public class Test {

    @org.junit.Test
    public void t(){
        String str = "0003";

        if (str.length() < 4) {
            for (int i = 4, l = str.length(); i > l; i--) {
                str = "0" + str;
            }
        }


        System.out.println(str);
    }

}

