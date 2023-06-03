package com.liaoxin.bpo;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/8
 **/

public interface SaveFileBPO {

    /**
     * 存储文件
     * @param files
     * @return
     */
    List saveFile(MultipartFile[] files);

}
