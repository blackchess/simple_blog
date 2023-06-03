package com.liaoxin.service;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
 * @Auther: liaoxin
 * @Date: 2022/9/30
 **/
public interface ExportService {

    void exportExcel(Map paramMap, HttpServletResponse response);

}
