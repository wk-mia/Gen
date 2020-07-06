package com.priv.mia.domain;

import org.springframework.stereotype.Component;

@Component
public class templateUtil {
    
    // 首字母大写
    public static String getUpperString(String sourceString){
        char[] chars = sourceString.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }
    
    // 首字母小写
    public static String getLowerString(String sourceString){
        char[] chars = sourceString.toCharArray();
        if (chars[0] >= 'A' && chars[0] <= 'Z') {
            chars[0] = (char) (chars[0] + 32);
        }
        return new String(chars);
    }
}
