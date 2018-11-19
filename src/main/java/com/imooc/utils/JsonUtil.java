package com.imooc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
    /*
    把对象转成json格式
     */
    public static String toJson(Object object){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();
        return gson.toJson(object);
    }
}
