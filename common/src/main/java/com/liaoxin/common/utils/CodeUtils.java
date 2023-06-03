package com.liaoxin.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.servlet.http.HttpServletResponse;
import java.util.Random;

public class CodeUtils {

    /**
     * 生成指定长度数字验证码序列
     */
    public static String createCode(int len){
        Random random = new Random();
        StringBuffer codeString =new StringBuffer();
        for(int i = 0;i < len;i ++){
            int num = random.nextInt(10);
            codeString.append(num);
        }
        return codeString.toString();
    }

    /**
     * 创建默认宽、高的二维码
     * @param text      二维码包含的信息
     * @param response  响应输出流
     * @throws Exception
     */
    public static void createQRCodePicture(String text, HttpServletResponse response) throws Exception{
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 140, 140);
        MatrixToImageWriter.writeToStream(bitMatrix,"png", response.getOutputStream());
    }

    /**
     * 创建二维码图片到文件夹
     * @param text      二维码包含的信息
     * @param width     图片宽度
     * @param height    图片长度
     * @param response  响应输出流
     */
    public static void createQRCodePicture(String text, int width, int height, HttpServletResponse response) throws Exception{
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToStream(bitMatrix,"png", response.getOutputStream());
    }
}
