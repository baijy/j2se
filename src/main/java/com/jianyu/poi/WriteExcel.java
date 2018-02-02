package com.jianyu.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * POI写入Excel<br>
 * 如果基于现有excel写入，只要把inputStream来源改成现有excel文档
 * @author jianyu.bai
 *
 */
public class WriteExcel {
	public static void main(String[] args) throws IOException {
		writeExcel();
	}

	/**
	 * 通用的关键是标题跟列名要对应起来
	 * 用@Excel注解是一种不错的方法，但是对bean有入侵
	 * 
	 * @throws IOException
	 */
	public static void writeExcel() throws IOException {
		List<Person> list = new ArrayList<Person>();
		Person xiaoming = new Person("小明", 22);
		Person xiaohua = new Person("小华", 20);
		list.add(xiaoming);
		list.add(xiaohua);

		XSSFWorkbook outBook = new XSSFWorkbook();
		XSSFSheet outSheet = outBook.createSheet("Sheet1");
		outSheet.setColumnWidth(1, 8000);
		outSheet.setColumnWidth(2, 8000);
		XSSFRow outTitleRow = outSheet.createRow(0);
		// 输出的Excel，标题列
		XSSFCell titleCell1 = outTitleRow.createCell(0);
		XSSFCell titleCell2 = outTitleRow.createCell(1);
		titleCell1.setCellValue("姓名");
		titleCell2.setCellValue("年龄");

		int outRowNum = 1; // 第一行是标题，内容从第二行开始写入
		for (Person p : list) {
			// 设置内容
			XSSFRow outContentRow = outSheet.createRow(outRowNum);
			outContentRow.createCell(0).setCellValue(p.getName());
			outContentRow.createCell(1).setCellValue(p.getAge());
			outRowNum++;
		}

		// 写入Excel
		FileOutputStream fOut = new FileOutputStream("D:\\测试文件.xlsx");
		outBook.write(fOut);
		fOut.flush();
		fOut.close();
	}
}

class Person {
	private String name;
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

}
