package com.jianyu.io.file;

import java.io.File;
//import it.sauronsoftware.jave.Encoder;
//import it.sauronsoftware.jave.MultimediaInfo;

/**
 * 下载jave的网址http://www.sauronsoftware.it/projects/jave/index.php
 * http://www.sauronsoftware.it/projects/jave/jave-1.0.2.zip
 *
 * 自己写的，用来统计视频时长的类
 */
public class ListFiles {


    public static void main(String[] args) {
        String i = "16";
        //String file_path="D:\\JavaLessons01\\上海传智-day"+i+"_avi\\day"+i+"_avi";
        //String file_path="D:\\JavaLessons01\\上海传智-day28_avi\\day28_avi";
        String file_path = "D:\\JavaLessons01\\上海传智-day28-30天\\day30_avi\\DOM";

        listFiles(file_path);
        getTotalTime(file_path);
    }

    /**
     * @param file_path 需要列出文件的目录
     * @description 列出制定目录的所有文件或目录
     * @author BaiJianyu
     */
    public static void listFiles(String file_path) {
        File dir = new File(file_path);
        File[] files = dir.listFiles();
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            sb = sb.append(file.getName().replace(".avi", "") + "\n");
        }
        System.out.println(sb);
    }

    /**
     * @param file_path 需要计算视频文件时长的的目录
     * @description 计算制定目录下所有视频文件的总时长
     * @author BaiJianyu
     */
    public static void getTotalTime(String file_path) {
        /*File source = new File(file_path);
        File[] files=source.listFiles();
        Encoder encoder = new Encoder();
        long single_time = 0;
        long total_time = 0;
        for (File file: files){
            try {
                MultimediaInfo m = encoder.getInfo(file);
                single_time = m.getDuration();
                total_time=total_time+single_time;
                //System.out.println("时长" + single_time / 60000 + "分");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("总时长" + total_time / 60000 + "分");*/
    }

}
