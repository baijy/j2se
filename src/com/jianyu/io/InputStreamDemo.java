package com.jianyu.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InputStreamDemo {
	private static final String TXT_PATH = "D:\\dev_learn\\Springboot.application属性大全.txt";

	public static void main(String[] args) throws IOException {
		readFile();
	}

	public static void readFile() throws IOException {
		FileInputStream fis = new FileInputStream(TXT_PATH);
		int len = 0;
		while ((len = fis.read()) != -1) {
			System.out.print((char) len); //编码错误
		}

	}
}
