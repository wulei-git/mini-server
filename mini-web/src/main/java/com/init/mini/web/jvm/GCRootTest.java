package com.init.mini.web.jvm;

import java.util.ArrayList;
import java.util.List;

public class GCRootTest {

    public static void main(String[] args) throws Exception {
        List<GCRootTest> gcRootTestList = new ArrayList<>();
        while (true) {
            gcRootTestList.add(new GCRootTest());
            Thread.sleep(10);
        }
    }
}
