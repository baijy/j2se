package com.jianyu.qrcode;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成二维码的工具类
 */
public class QRCodeUtil {
    public static void main(String[] args) {
        int width = 200;
        int height = 200;
        String format = "png";
        String content = "你好哇包子,20180501";

        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8"); // 字符编码
        hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M); //纠错等级
        hints.put(EncodeHintType.MARGIN,0); // 图片边距

        try{
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height,hints);
            Path file = new File("C:\\Users\\BaiJianyu\\Desktop\\qrcode.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix,format,file);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
