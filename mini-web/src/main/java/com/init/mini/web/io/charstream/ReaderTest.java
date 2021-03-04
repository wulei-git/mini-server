package com.init.mini.web.io.charstream;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReaderTest {
    public static void main(String[] args) throws IOException{
        test();
    }

    public static void test() throws IOException {
        File file = new File("/Users/apple/Documents/mk/testMarkDown.md");
        Reader reader = new FileReader(file);
        char[] chars = new char[1024];
        int len = reader.read(chars);
        System.out.println(new String(chars, 0, len));
        reader.close();
    }
}
