package com.jianyu.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * POI读取Excel文件
 * @author jianyu.bai
 *
 */
public class ReadExcel {

	public static void main(String[] args) throws IOException {
		readExcel("D:\\星汇联盟商户导入模板.xlsx");
	}

	public static void readExcel(String path) throws IOException {
		// 读取工作簿
		FileInputStream fis = new FileInputStream(new File(path));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);

		// 遍历每一行
		for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				XSSFCell cell = row.getCell(j);
				if (null != cell) {
					String cellValue = "";
					// Excel值最好都是文本类型
					if (cell.getCellType() == 0) {
						cellValue = String.valueOf(cell.getNumericCellValue());
					} else if (cell.getCellType() == 1) {
						cellValue = cell.getStringCellValue();
						System.out.print(cellValue + "|");
					}
				}
			}
			System.out.println();
		}
	}
}
