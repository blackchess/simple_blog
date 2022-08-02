package com.liaoxin.common.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

public class EntityUtils<T,M> {
    public List<M> copyList(List<T> source, List<M> target, Class<M> classObj){
        if(Objects.nonNull(source) && Objects.nonNull(target)){
            source.forEach(item -> {
                try {
                    M instance = classObj.newInstance();
                    BeanUtils.copyProperties(item,instance);
                    target.add(instance);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
        return target;
    }

    public void beforeCopy(List<T> source, List<M> target, Class<M> classObj){};

}
