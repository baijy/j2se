package com.jianyu.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderDemo {
	private static final int SIZE_1024 = 1024;
	private static final String TXT_PATH = "D:\\dev_learn\\Springboot.application属性大全.txt";

	public static void main(String[] args) throws IOException {
		// readFile();
		//readFileInArray();
		writeStringToFile();
	}

	/**
	 * 从文件读取字符，读到一个就输出一个
	 * 
	 * @throws IOException
	 */
	public static void readFile() throws IOException {
		FileReader fr = new FileReader(TXT_PATH);
		int ch = 0;
		while ((ch = fr.read()) != -1) {
			System.out.print((char) ch);
		}
	}

	/**
	 * 读一个字符就存入字符数组里，读完1kb再打印
	 */
	public static void readFileInArray() {
		FileReader fr = null;
		try {
			fr = new FileReader(TXT_PATH);
			char[] buf = new char[SIZE_1024];
			int len = 0;
			while ((len = fr.read(buf)) != -1) {
				System.out.println(new String(buf, 0, len));
			}

		} catch (Exception e) {

		} finally {
			if (null != fr) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将文本数据存储到一个文件中
	 * @throws IOException 
	 */
	public static void writeStringToFile() throws IOException{
		FileWriter fw = new FileWriter("D:\\dev_learn\\test20180116.txt");
		fw.write("一些测试字符\n\r换了一行");
		fw.flush();
		fw.write("aaaa");
		fw.close(); //会自动调用flush()
	}
	
	
	
}
