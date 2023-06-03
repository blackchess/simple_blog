package com.liaoxin.service.impl;

import com.liaoxin.common.exception.AppException;
import com.liaoxin.service.MongoService;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: liaoxin
 * @Date: 2022/8/4
 **/
@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Override
    public String saveFile(InputStream inputStream, String fileName, String contentType) {
        return gridFsTemplate.store(inputStream, fileName, contentType).toString();
    }

    @Override
    public InputStream readFile(String fileId) {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(fileId)));
        GridFsResource gridFsResource = gridFsTemplate.getResource(gridFSFile);
        try {
            return gridFsResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new AppException("获取文件失败");
        }
    }
}
