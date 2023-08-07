package com.liaoxin.service.impl;

import com.liaoxin.common.exception.AppException;
import com.liaoxin.config.FileConfig;
import com.liaoxin.service.FileService;
import com.liaoxin.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/4
 **/

@Service
public class FileServiceImpl implements FileService {

//    @Autowired
//    MongoService mongoService;

    @Autowired
    FileConfig fileConfig;

    @Override
    public List saveFile(MultipartFile[] files) {

        if(files.length <= 0) throw new AppException("上传文件不能为空");

        List paths = new ArrayList();

        for(MultipartFile file : files){
            // 获取文件名称
            String originalFilename = file.getOriginalFilename();
            // 获取上传文件后缀
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            String fileName = UUID.randomUUID() + "." + fileSuffix;
            String path = fileConfig.getSaveUrl() + fileName;
            File destFile = new File(path);

            if(!destFile.getParentFile().exists()){
                destFile.getParentFile().mkdirs();
            }

            try {
                file.transferTo(destFile);
                paths.add(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return paths;
    }

//    @Override
//    public List saveFileInDataBase(MultipartFile[] files) {
//        List result = new ArrayList();
//        for (MultipartFile file : files) {
//            // 获取文件名称
//            String originalFilename = file.getOriginalFilename();
//            // 获取上传文件后缀
//            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
//            String fileName = UUID.randomUUID() + "." + fileSuffix;
//            try {
//                InputStream inputStream = file.getInputStream();
//                result.add(mongoService.saveFile(inputStream,fileName,fileSuffix));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    @Override
//    public List<InputStream> readFileInDataBase(String[] filenames) {
//        ArrayList list = new ArrayList();
//        for(String filename : filenames){
//            list.add(mongoService.readFile(filename));
//        }
//        return list;
//    }
}
