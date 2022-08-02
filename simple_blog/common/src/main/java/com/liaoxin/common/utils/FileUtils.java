package com.liaoxin.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileUtils {

    private static String fileType = ".jpg,.jpeg,.png,.bmp,.gif";

    public static String cutImageType(MultipartFile file){
        String fileSuffix = "";
        // 获取文件名，带后缀
        String originalFilename = file.getOriginalFilename();
        // 获取文件的后缀格式
        if (fileType.indexOf(fileSuffix) != -1) {
            fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        }
        return fileSuffix;
    }
}
