package com.jianyu.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedInputStreamDemo {
	private static final String FILE_PATH = "D:\\apps\\redis-desktop-manager-0.8.8.384.exe";

	public static void main(String[] args) throws IOException {
		copyFile();
	}

	/**
	 * 带缓冲的字节流拷贝文件
	 * 
	 * @throws IOException
	 */
	public static void copyFile() throws IOException {
		BufferedInputStream bufis = new BufferedInputStream(new FileInputStream(FILE_PATH));
		BufferedOutputStream bufos = new BufferedOutputStream(new FileOutputStream("D:\\apps\\test-copy.exe"));

		int by = 0;
		while ((by = bufis.read()) != -1) {
			bufos.write(by);
		}
		bufos.close();
		bufis.close();

	}
}
