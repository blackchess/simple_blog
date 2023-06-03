package com.liaoxin.service;


import java.io.InputStream;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/4
 **/
public interface MongoService {

    String saveFile(InputStream inputStream, String fileName, String contentType);

    InputStream readFile(String filename);
}
