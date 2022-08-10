package com.liaoxin.bpo.impl;

import com.liaoxin.bpo.SaveFileBPO;
import com.liaoxin.common.exception.AppException;
import com.liaoxin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/8
 **/
public class SaveFileBPOImpl implements SaveFileBPO {

    @Autowired
    FileService fileService;

    @Value("${file.saveMode}")
    private Integer saveMode;

    @Override
    public List saveFile(MultipartFile[] files) {
        List<?> list = new ArrayList();
        if(saveMode == 1){
           list.addAll(fileService.saveFile(files));
        }else if(saveMode == 2){
            list.addAll(fileService.saveFileInDataBase(files));
        }else{
            throw new AppException("没有找到【"+saveMode+"】类型的存储模式");
        }
        return list;
    }
}
