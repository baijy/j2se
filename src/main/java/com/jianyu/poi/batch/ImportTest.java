package com.jianyu.poi.batch;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImportTest {
    public static void main(String[] args) throws IOException {
        //ExecutorService es = Executors.newCachedThreadPool();
        final int POOL_SIZE=10;
        final int TITLE_ROWS=2;
        ExecutorService es = Executors.newFixedThreadPool(POOL_SIZE);
        CountDownLatch doneSignal = new CountDownLatch(POOL_SIZE);
        String path = "D:\\testfile\\file\\1000行.xlsx";
        long start = System.currentTimeMillis();
        // 读取工作簿
        FileInputStream fis = new FileInputStream(new File(path));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        long readFinished = System.currentTimeMillis();
        System.out.println("Excel读取完毕，耗时："+(readFinished-start)+"ms");

        int[][] array = NumberRange.getRangeArray(sheet.getLastRowNum()+1,POOL_SIZE,TITLE_ROWS);

        for(int i =0;i<POOL_SIZE;i++){
            es.submit(new ImportTask(doneSignal,sheet,array[i][0],array[i][1]));
        }

        try {
            doneSignal.await(); //等待所有线程完成操作
            es.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "ms");

    }
}
