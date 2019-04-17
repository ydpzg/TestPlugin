package com.kalengo.commonlib;

import java.util.HashMap;
import java.util.Map;

/***
 * Created by duke on 2019/4/9.
 */
public class TimeCache {

    public static Map<String, Long> sStartTime = new HashMap<>();
    public static Map<String, Long> sEndTime = new HashMap<>();

    public static void setStartTime(String methodName, long time) {
        sStartTime.put(methodName, time);
    }

    public static void setEndTime(String methodName, long time) {
        sEndTime.put(methodName, time);
    }

    public static String getCostTime(String methodName) {
        long start = sStartTime.get(methodName);
        long end = sEndTime.get(methodName);
        return "method: " + methodName + " time " + (end - start) / 1000.0 + " ms";
    }

}