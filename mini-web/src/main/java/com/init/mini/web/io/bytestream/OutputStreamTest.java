package com.init.mini.web.io.bytestream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputStreamTest {

    public static void main(String[] args) throws IOException  {
        test();
    }

    /**
     * 文件内容覆盖为 str
     *
     * 文件路径和文件内容可以参数化
     *
     * @throws IOException
     */
    public static void test() throws IOException {
        File file = new File("/Users/apple/Documents/mk/testMarkDown.md");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        String str = "# 测试写入到文件";
        fileOutputStream.write(str.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
