package com.kalengo.testplugin;

import com.kalengo.commonlib.TimeCache;

/***
 * 这个类主要是为了写一些方法然后用asm bytecode outline工具看asm源代码
 * Created by duke on 2019/4/9.
 */
public class TestAsm {
    private void methodTimeStart() {
        System.out.println("========start=========");
        TimeCache.setStartTime("method", System.nanoTime());
    }
    private void methodTimeDnd() {
        TimeCache.setEndTime("method", System.nanoTime());
        System.out.println(TimeCache.getCostTime("method"));
        System.out.println("========end=========");
    }
}
