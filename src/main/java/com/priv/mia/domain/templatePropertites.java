package com.priv.mia.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "domain")
@PropertySource(value = "classpath:domainConfig.properties")
public class templatePropertites {

    // 包空间
    private String nameSpace;
    // 业务名称
    private String domainName;
    // 业务名称首字母大写
    private String domainNameUpper;
    // 业务名称首字母小写
    private String domainNameLower;
    // 时间串
    private String dateFormatString;
    // 作者
    private String author;
    // 生成路径
    private String generatePath;
    // 模板文件目录
    private String templateDirectory;
    // 模板文件名称前缀
    private String templateNamePrefix;


    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainNameUpper() {
        return domainNameUpper;
    }

    public void setDomainNameUpper(String domainNameUpper) {
        this.domainNameUpper = domainNameUpper;
    }

    public String getDateFormatString() {
        return dateFormatString;
    }

    public void setDateFormatString(String dateFormatString) {
        this.dateFormatString = dateFormatString;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGeneratePath() {
        return generatePath;
    }

    public void setGeneratePath(String generatePath) {
        this.generatePath = generatePath;
    }

    public String getTemplateNamePrefix() {
        return templateNamePrefix;
    }

    public void setTemplateNamePrefix(String templateNamePrefix) {
        this.templateNamePrefix = templateNamePrefix;
    }

    public String getDomainNameLower() {
        return domainNameLower;
    }

    public void setDomainNameLower(String domainNameLower) {
        this.domainNameLower = domainNameLower;
    }

    public String getTemplateDirectory() {
        return templateDirectory;
    }

    public void setTemplateDirectory(String templateDirectory) {
        this.templateDirectory = templateDirectory;
    }
}
