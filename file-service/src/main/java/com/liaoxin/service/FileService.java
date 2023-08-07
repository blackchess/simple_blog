package com.liaoxin.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/4
 **/
public interface FileService {

    /**
     * 存储文件功能
     * @param files
     * @return
     */
    List saveFile(MultipartFile[] files);

//    /**
//     * 数据库存储文件功能
//     * @param files
//     * @return
//     */
//    List saveFileInDataBase(MultipartFile[] files);
//
//    /**
//     * 读取文件
//     * @param ids
//     * @return
//     */
//    List<InputStream> readFileInDataBase(String[] ids);



}
