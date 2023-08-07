package com.liaoxin.controller;

import com.liaoxin.common.common.ResultBean;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/4
 * @Description: 文件存储控制器
 **/

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    FileService fileService;

    /**
     * 批量存储文件
     * @param files 文件列表
     */
    @PostMapping("")
    public ResultBean saveFile(MultipartFile[] files){
        if(files == null || files.length == 0){
            throw new AppException("文件不能为空");
        }
        return ResultBean.success(fileService.saveFile(files));
    }

    /**
     * 批量获取文件
     * @param filenames 文件名
     */
//    @GetMapping("")
//    public ResultBean<List<InputStream>> getFiles(@RequestParam String[] filenames){
//        List<InputStream> inputStreamList = fileService.readFileInDataBase(filenames);
//        return ResultBean.success(inputStreamList);
//    }

}
