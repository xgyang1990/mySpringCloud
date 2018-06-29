package com.xyang.userService.utils;

import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class RedisConfigUtil {
    private static Properties prop = null;

    static{
        prop = new Properties();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("/Users/xyang137/Developer/GitHub/springcloud/userService/src/main/resources/redis.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return prop.getProperty(key);
    }
}
