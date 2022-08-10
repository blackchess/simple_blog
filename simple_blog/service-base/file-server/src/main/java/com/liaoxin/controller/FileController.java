package com.liaoxin.controller;

import com.liaoxin.common.common.ResultBean;
import com.liaoxin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/4
 **/

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("")
    public ResultBean saveFile(MultipartFile[] files){
        List result = new ArrayList();
        result.addAll(fileService.saveFile(files));
        return ResultBean.success(result);
    }

    @GetMapping("")
    public ResultBean<List<InputStream>> getFiles(@RequestParam String[] filenames){
        List<InputStream> inputStreamList = fileService.readFileInDataBase(filenames);
        return ResultBean.success(inputStreamList);
    }

}
