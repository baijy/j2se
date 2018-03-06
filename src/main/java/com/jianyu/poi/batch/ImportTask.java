package com.jianyu.poi.batch;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.concurrent.CountDownLatch;

/**
 * http://blog.csdn.net/huihuijor/article/details/38040195
 * https://www.cnblogs.com/wihainan/p/4765862.html
 *
 */
public class ImportTask implements Runnable {
    private final CountDownLatch doneSignal; //读取结束标志
    private XSSFSheet sheet; //需要读取的表单
    private int start; // 读取起始位置
    private int end; //读取结束位置

    public ImportTask(CountDownLatch doneSignal, XSSFSheet sheet, int start, int end) {
        this.doneSignal = doneSignal;
        this.sheet = sheet;
        this.start = start;
        this.end = end;
    }

    public void run() {
        int i = start;
        try {
            while (i <= end) {
                XSSFRow row = sheet.getRow(i);
                System.out.print(" 正在处理第"+(i+1)+"行：");

                //模拟每一行的业务逻辑
                Thread.sleep(200);

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    XSSFCell cell = row.getCell(j);
                    if (null != cell) {
                        String cellValue = "";
                        // 字符串类型
                        if (cell.getCellType() == 1) {
                            cellValue = cell.getStringCellValue();
                        } else if (cell.getCellType() == 0) {
                            // 数字类型
                            cellValue = String.valueOf(cell.getNumericCellValue());
                        }

                        if (null != cellValue && !cellValue.equals("")) {
                            System.out.print(cellValue+" ");
                        }
                    }
                }
                ++i;
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            doneSignal.countDown();
        }
    }


}
