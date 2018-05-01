package com.jianyu.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {

    public static void main(String[] args) throws IOException {
        String str = "fjdlksafd,,,,,";
        byte[] bs = str.getBytes();
        FileOutputStream fos  =new FileOutputStream("E:/2.txt",true);
        fos.write(bs);
    }
}

