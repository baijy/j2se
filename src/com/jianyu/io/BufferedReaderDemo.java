package com.jianyu.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedReaderDemo {
	private static final String TXT_PATH = "D:\\dev_learn\\Springboot.application属性大全.txt";

	public static void main(String[] args) throws IOException {
		copyFile();
	}

	/**
	 * 使用带缓冲区的字符流拷贝文件
	 * 
	 * @throws IOException
	 */
	public static void copyFile() throws IOException {
		// 把需要增强的对象传递给缓冲类
		BufferedReader bufr = new BufferedReader(new FileReader(TXT_PATH));
		BufferedWriter bufw = new BufferedWriter(new FileWriter("D:\\dev_learn\\copy.txt"));
		String line = null;

		try {
			while ((line = bufr.readLine()) != null) {
				bufw.write(line);
				bufw.newLine();// 换行
				bufw.flush();
			}
		} catch (Exception e) {

		} finally {
			if (null != bufw) {
				try {
					bufw.close();
				} catch (Exception e2) {
				}
			}
			if (null != bufr) {
				try {
					bufw.close();
				} catch (Exception e2) {
				}
			}
		}
	}

}
