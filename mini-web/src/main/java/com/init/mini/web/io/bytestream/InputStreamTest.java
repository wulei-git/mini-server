package com.init.mini.web.io.bytestream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class InputStreamTest {
    public static void main(String[] args) throws IOException {
        test();
    }

    /**
     * 文件路径和输出路径可以参数化
     *
     * @throws IOException
     */
    public static void test() throws IOException {
        File file = new File("/Users/apple/Documents/mk/testMarkDown.md");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] b = new byte[1024];
        int len = fileInputStream.read(b);
        System.out.println(new String(b, 0, len));
        fileInputStream.close();
    }
}
