package com.jianyu.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 转换流测试
 * 
 * @author jianyu.bai
 *
 */
public class InputStreamReaderDemo {
	private static final String TXT_PATH = "D:\\dev_learn\\Springboot.application属性大全.txt";

	public static void main(String[] args) throws IOException {
		// 转换流，是字节流和字符流的一个桥梁
		InputStreamReader isr = new InputStreamReader(new FileInputStream(TXT_PATH), "UTF-8");
		int len = 0;
		while ((len = isr.read()) != -1) {
			System.out.print((char) len);
		}
	}

}
