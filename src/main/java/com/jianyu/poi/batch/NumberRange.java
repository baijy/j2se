package com.jianyu.poi.batch;

public class NumberRange {
    public static void main(String[] args) {

        int x = 1002; // 数据总行数，包括标题行
        int y =6; // 均分成几份处理
        int z = 2; // 标题行有几行

        int[][] array = getRangeArray(x, y, z);

        printArray(array);
    }

    /**
     * 根据总数和线程数，获取每个线程需要处理的平均分布的整数区间
     * 余数由最后一个线程处理
     *
     * @param x 总数量，包括标题行，即excel的行指示数
     * @param y 均分成多少份
     * @param z 标题行总行数（需要排除的行）
     * @return 返回一个二维数组，每个数组都是起始和结束点（第1行的index是0）
     */
    public static int[][] getRangeArray(int x, int y, int z) {
        int[][] array = new int[y][2];
        x -= z; //从总数中去除标题行再计算
        int average = x / y;
        int remainder = x % y;
        for (int m = 0; m < y; m++) {
            array[m][0] = z;
            array[m][1] = z + average-1; // 平均数-1，就是index增加的数量
            // 余数叠加到最后一个线程处理
            if (m == y - 1) {
                array[m][1] += remainder;
            }
            //下一个起始序号
            z = z + average;
        }
        return array;
    }

    /**
     * 括号方式打印二维数组
     *
     * @param array
     */
    public static void printArray(int[][] array) {
        System.out.print("{");
        for (int i = 0; i < array.length; i++) {
            System.out.print("{");
            for (int j = 0; j < array[i].length; j++) {
                if (j != array[i].length - 1) {
                    System.out.print(array[i][j] + ",");
                } else {
                    System.out.print(array[i][j]);
                }
            }
            if (i != array.length - 1) {
                System.out.print("},");
            } else {
                System.out.print("}");
            }
        }
        System.out.print("}");
    }
}
