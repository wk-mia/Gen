package com.priv.mia.util;

import com.priv.mia.casefactory.CaseFactory;
import com.priv.mia.domain.ReName;
import com.priv.mia.domain.TemplateProperties;
import com.priv.mia.domain.ToCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

/**
 * 模板属性及标签配置工具类
 *
 * @author : wuyz
 * @date : 2020/07/09
 */
@Component
public class TemplateUtil {

    private TemplateUtil(){}

    private static ReName reName;

    @Autowired
    private ReName myReName;

    private static ToCase toCase;

    @Autowired
    private ToCase mytoCase;

    @PostConstruct
    private void init() {
        reName = myReName;
        toCase = mytoCase;
    }



    /**
     * 初始化
     * @param templateProperties
     * @throws Exception
     */
    public static void initial(TemplateProperties templateProperties)throws Exception{

        TemplateUtil.checkReNameRule(templateProperties);
        TemplateUtil.checkToCaseRule(templateProperties);

        // 默认输出路径
        if(templateProperties.getDestPath() == null || templateProperties.getDestPath().length() <= 0){
            templateProperties.setDestPath("/dest/");
        }
        // 默认输入目录
        if(templateProperties.getTemplateDirectory() == null || templateProperties.getTemplateDirectory().length() <= 0){
            templateProperties.setTemplateDirectory("/templates/bu/");
        }
    }


    /**
     * 重命名规则
     * @param templateProperties
     * @throws Exception
     */
    private static void checkReNameRule(TemplateProperties templateProperties)throws Exception{
        if(templateProperties.getReNameRule() == null || templateProperties.getReNameRule().length() <= 0){
            reName.setIsNeedReName(false);
        }else {
            // 配置是否合法
            String[] rules = templateProperties.getReNameRule().split(":");
            boolean isInRule = (rules.length > 2 || rules.length < 2) || rules[0].length() <= 0 || rules[1].length() <= 0;
            if(isInRule){
                throw new InputMismatchException(
                        new StringBuffer("Input does not meet the rules:reNameRule...").
                                append("\r\n").
                                append(templateProperties.getReNameRule()).
                                append("\r\n").
                                append("please check the properties file.").toString());
            }
            reName.setIsNeedReName(true);
        }
    }


    /**
     * 字符串转换规则
     * @param templateProperties
     * @throws Exception
     */
    private static void checkToCaseRule(TemplateProperties templateProperties)throws Exception{
        // 字符串转换规则
        if(templateProperties.getToCaseRule() == null || templateProperties.getToCaseRule().length() <= 0){
            toCase.setEnableToCase(false);
        }else {
            toCase.setEnableToCase(true);
            toCase.setToCaseList(new ArrayList<>());
            // 配置是否合法
            String[] rules = templateProperties.getToCaseRule().split(";");
            for (String rule : rules) {
                if("xxxBeginUpper".equals(rule.trim()) || "xxxAllUpper".equals(rule.trim())
                        || "xxxBeginLower".equals(rule.trim()) || "xxxAllLower".equals(rule.trim())){
                    toCase.getToCaseList().add(rule.trim().replace("x",""));
                }else {
                    throw new InputMismatchException(
                            new StringBuffer("Input does not meet the rules:toCaseRule...").
                                    append("\r\n").
                                    append(templateProperties.getToCaseRule()).
                                    append("\r\n").
                                    append("please check the properties file.").toString());
                }
            }
        }
    }


    /**
     * 获取命名
     * @param oldFileName
     * @param reNameRule
     * @return
     * @throws Exception
     */
    public static String getNewName(String oldFileName,String reNameRule)throws Exception{
        if(reName.getIsNeedReName()){
            String[] rules = reNameRule.split(":");
            return oldFileName.replace(rules[0],rules[1]);
        }else {
            return oldFileName;
        }
    }


    /**
     * 追加未定义的标签
     * @param sourceData
     * @param alreadyMap
     * @return
     * @throws Exception
     */
    public static HashMap<String,Object> appendProperties(String sourceData,HashMap<String,Object> alreadyMap)throws Exception{
        // 追加字符串条件标签
        if(toCase.isEnableToCase()){
            HashMap<String,Object> hashMap = new HashMap<>(16);
            toCase.getToCaseList().forEach(
                    item -> {
                        HashMap<String, Object> toCaseMap = CaseFactory.createCase(item).getToCaseMap(sourceData, alreadyMap);
                        if(toCaseMap != null && toCaseMap.size() > 0) {
                            hashMap.putAll(toCaseMap);
                        }
                    }
            );
            return hashMap;
        }else {
            return null;
        }
    }
}
