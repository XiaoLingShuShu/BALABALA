package com.balabala.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class BeanHelper {

    public static <T> T copyProperties(Object source, Class<T> target){
        try {
            T t = target.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static <T> List<T> copyWithCollection(List<?> sourceList, Class<T> target){

            return sourceList.stream().map(s -> copyProperties(s, target)).collect(Collectors.toList());

    }

    public static <T> Set<T> copyWithCollection(Set<?> sourceList, Class<T> target){

            return sourceList.stream().map(s -> copyProperties(s, target)).collect(Collectors.toSet());

    }
}
