package com.jianyu.poi.batch;

import java.util.Arrays;

public class NumberRange {
    public static void main(String[] args) {

        int x = 10321;
        int y = 10;

        int[][] array = getRangeArray(x, y);

        printArray(array);
    }

    /**
     * 根据总数和线程数，获取每个线程需要处理的平均分布的整数区间
     * 余数由最后一个线程处理
     * @param x
     * @param y
     * @return 返回一个二维数组，每个数组都是起始和结束点
     */
    public static int[][] getRangeArray(int x, int y) {
        int[][] array =new int[y][2];

        int average = x/y;
        int k =0;
        for( int m=0;m<y;m++){
            array[m][0]=k+1;
            array[m][1]=k+average;
            // 余数一律最后一个线程处理
            if( m == y-1){
                array[m][1] += x%y;
            }
            k += average;
        }
        return array;
    }

    /**
     * 括号方式打印二维数组
     * @param array
     */
    public static void printArray(int[][] array) {
        System.out.print("{");
        for(int i=0;i<array.length;i++){
            System.out.print("{");
            for(int j =0; j<array[i].length;j++){
                if( j!= array[i].length-1){
                    System.out.print(array[i][j]+",");
                }else{
                    System.out.print(array[i][j]);
                }
            }
            if( i!= array.length-1){
                System.out.print("},");
            }else{
                System.out.print("}");
            }
        }
        System.out.print("}");
    }
}
