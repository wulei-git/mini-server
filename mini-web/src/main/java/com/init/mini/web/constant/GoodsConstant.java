package com.init.mini.web.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsConstant {

    public static List<String> TARGET = Arrays.asList(
            "target1",
            "target2",
            "target3",
            "target4"
    );

    public static List<String> DEPT;

    public static List<String> PLAN;

    static {
        DEPT = new ArrayList<>();
        PLAN = new ArrayList<>();
        for (int i=1; i < 5; i++) {
            DEPT.add(String.valueOf(i));
        }
        for (int i=1; i < 20; i++) {
            PLAN.add(new StringBuilder().append("plan").append(i).toString());
        }
    }
}
