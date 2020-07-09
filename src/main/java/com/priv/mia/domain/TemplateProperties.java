package com.priv.mia.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 标签及属性配置类
 *
 * @author : wuyz
 * @date : 2020/07/09
 */
@Component
@ConfigurationProperties(prefix = "domain")
@PropertySource(value = "classpath:templateConfig.properties")
public class TemplateProperties {

    private String destPath;

    private String templateDirectory;

    private String reNameRule;

    private String toCaseRule;

    private HashMap<String,Object> tagMap;


    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public String getTemplateDirectory() {
        return templateDirectory;
    }

    public void setTemplateDirectory(String templateDirectory) {
        this.templateDirectory = templateDirectory;
    }

    public String getReNameRule() {
        return reNameRule;
    }

    public void setReNameRule(String reNameRule) {
        this.reNameRule = reNameRule;
    }

    public HashMap<String, Object> getTagMap() {
        return tagMap;
    }

    public void setTagMap(HashMap<String, Object> tagMap) {
        this.tagMap = tagMap;
    }

    public String getToCaseRule() {
        return toCaseRule;
    }

    public void setToCaseRule(String toCaseRule) {
        this.toCaseRule = toCaseRule;
    }
}
