package com.init.mini.web.annotationtest;

public class AnnotationClient {

    @DIYAnnotion(desc = "diy", age = 16)
    // only one filed
//    @OnlyOneFiled("onlyOneFiled")
    // flag annotation
//    @FlagAnnotation
    private String testDIYAnnotation() {
        return null;
    }


}
