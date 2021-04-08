package com.init.mini.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 一个按照顺序轮询地返回对象的选择器
 * @param <T>
 */
public class SequenceSelector<T> implements Selector {

    // 对象集合
    private final List<T> clients;

    // 轮询地游标
    private final AtomicLong sequence = new AtomicLong(0);

    // 对象集合大小
    private final int size;

    public SequenceSelector(Collection<T> clients) {
        size = clients.size();
        this.clients = new ArrayList<>(size);
        this.clients.addAll(clients);
    }

    @Override
    public int num() {
        return size;
    }

    @Override
    public Object select() {
        return size == 0
                ? null
                : this.clients.get((int) sequence.getAndIncrement() % size);
    }

    public static void main(String[] args) {
        List<Object> ls = new ArrayList<>(2);
        ls.add(1);
        ls.add(2);
        ls.add(3);

        SequenceSelector sequenceSelector = new SequenceSelector(ls);

        for(int i=0; i<10; i++) {
            System.out.println(sequenceSelector.select());
        }
        System.out.println(System.getProperties().getProperty("kafka.servers"));
    }
}
