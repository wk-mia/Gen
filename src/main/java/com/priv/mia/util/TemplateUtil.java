package com.priv.mia.util;

import com.priv.mia.domain.ReName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.InputMismatchException;

@Component
public class TemplateUtil {

    private static ReName reName;

    @Autowired
    private ReName myReName;

    @PostConstruct
    private void init() {
        reName = myReName;
    }


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

    // 初始化
    public static void initial(TemplateConfig templateConfig)throws Exception{
        // 重命名规则
        if(templateConfig.getReNameRule() == null || templateConfig.getReNameRule().length() <= 0){
            reName.setIsNeedReName(false);
        }else {
            // 配置是否合法
            String[] rules = templateConfig.getReNameRule().split(":");
            if(!(rules.length == 2) || rules[0].length() <= 0 || rules[1].length() <= 0){
                throw new InputMismatchException(
                        new StringBuffer("Input does not meet the rules:reNameRule...").
                                append("\r\n").
                                append(templateConfig.getReNameRule()).
                                append("\r\n").
                                append("please check the properties file.").toString());
            }
            reName.setIsNeedReName(true);
        }
        // 默认输出路径
        if(templateConfig.getDestPath() == null || templateConfig.getDestPath().length() <= 0){
            templateConfig.setDestPath("/dest/");
        }
        // 默认输入目录
        if(templateConfig.getTemplateDirectory() == null || templateConfig.getTemplateDirectory().length() <= 0){
            templateConfig.setTemplateDirectory("/templates/bu/");
        }
    }

    public static String getNewName(String oldFileName,String reNameRule)throws Exception{
        if(reName.getIsNeedReName()){
            String[] rules = reNameRule.split(":");
            return oldFileName.replace(rules[0],rules[1]);

        }else {
            return oldFileName;
        }
    }
}
