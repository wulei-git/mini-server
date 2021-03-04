package com.init.mini.web.io.charstream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterTest {
    public static void main(String[] args) throws IOException {
        test();
    }

    public static void test() throws IOException {
        File file = new File("/Users/apple/Documents/mk/testMarkDown.md");
        Writer writer = new FileWriter(file);
        String str = "# 测试字符流";
        writer.write(str);
        // 写文件先将输出流写入到缓冲区，当缓冲区写满后才将缓冲区的内容输出到文件中。
        // 但是当主机完成输出流的输出后，有可能缓冲区这个时候还没有被填满，这样的话，
        // 就会一直等待主机发送内容，这时候，就可以使用flush()方法将缓冲区的内容强制
        // 输出到文件中，清空缓冲区。
        // 一般在关闭输出流之前，要先调用flush()方法强制缓冲区中的内容输出，并清空缓冲区。
        writer.flush();
        writer.close();
    }
}
