package com.init.mini.web.jvm.hashcode;

import com.google.common.base.Objects;

public class UserDemo {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDemo userDemo = (UserDemo) o;
        return getAge() == userDemo.getAge() &&
                Objects.equal(getName(), userDemo.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getAge());
    }
}
