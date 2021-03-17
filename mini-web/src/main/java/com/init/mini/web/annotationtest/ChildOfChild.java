package com.init.mini.web.annotationtest;

public class ChildOfChild extends Child {

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String sing() {
        return super.sing();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
