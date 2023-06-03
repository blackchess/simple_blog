package com.liaoxin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liaoxin.common.common.ResultBean;
import com.liaoxin.common.common.WebConst;
import com.liaoxin.domain.Label;
import com.liaoxin.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Auther: liaoxin
 * @Date: 2022/7/21
 **/
@RestController
@RequestMapping("label")
@CrossOrigin("*")
public class LabelController {

    @Autowired
    LabelService labelService;

    @GetMapping("")
    public ResultBean<List<Label>> list(){
        Map resultMap = new HashMap();
        resultMap.put("list",labelService.list(new QueryWrapper<Label>().eq("status", WebConst.STATUS_1)));
        return ResultBean.success(resultMap);
    }

    @GetMapping("{id}")
    public ResultBean<Label> getLabel(@PathVariable("id") Long id){
        Label label = labelService.getById(id);
        if(Objects.nonNull(label)){
            return ResultBean.success(label);
        }
        return ResultBean.failure();
    }

    @PostMapping("")
    public ResultBean addOrUpdateLabel(@RequestBody Label label){
        label.setCreateTime(new Date());
        label.setUpdateTime(new Date());
        Boolean result = labelService.saveOrUpdate(label);
        if(result){
            return ResultBean.success();
        }
        return ResultBean.failure();
    }

    @DeleteMapping("{ids}")
    public ResultBean batchDeleteLabel(@PathVariable("ids") Long[] ids){
        Boolean result = labelService.removeBatchByIds(Arrays.asList(ids));
        if(result){
            return ResultBean.success();
        }
        return ResultBean.failure();
    }

}
