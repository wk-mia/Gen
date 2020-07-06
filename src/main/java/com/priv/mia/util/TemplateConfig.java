package com.priv.mia.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@ConfigurationProperties(prefix = "domain")
@PropertySource(value = "classpath:templateConfig.properties")
public class TemplateConfig {

    private String destPath;

    private String templateDirectory;

    private String reNameRule;

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

}
