package com.kalengo.testplugin;

import com.kalengo.commonlib.MethodTime;

/***
 * Created by duke on 2019/4/12.
 */
public class Logic {

    @MethodTime
    public void test() {
        for (int i = 0;i < 1000;i++) {
            System.out.println();
        }
    }

    @MethodTime
    public void test2() {
        for (int i = 0;i < 100;i++) {
            System.out.println();
        }
    }
}
