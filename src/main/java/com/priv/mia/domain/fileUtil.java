package com.priv.mia.domain;

import com.alibaba.druid.util.Utils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.util.Date;
import java.util.Scanner;

@Component
public class fileUtil {

    private static final Logger logger = LoggerFactory.getLogger(fileUtil.class);

    private static templatePropertites myTemplatePropertites;

    @Autowired
    private templatePropertites templatePropertites;

    @PostConstruct
    private void init() {
        myTemplatePropertites = templatePropertites;
    }

    // 模板文件所在目录
    public static String getCurrentDir()throws Exception{
        // 获取配置文件中模板文件所在目录
        String directory = ResourceUtils.getURL("classpath:").getPath()
                .concat(myTemplatePropertites.getTemplateDirectory());
        File file = new File(directory);
        if(file.exists()){
            return file.getAbsolutePath();
        }else {
            throw new FileNotFoundException(
                    new StringBuffer("the directory is not exits...").
                            append("\r\n").
                            append(file.getAbsolutePath()).
                            append("\r\n").
                            append("please check the properties file.").toString());
        }
    }

    // 初始化业务名称、创建时间
    private static void initial(){
        logger.info("Please enter domain name...");

        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextLine()){
            myTemplatePropertites.setDomainName(scanner.nextLine().trim());
        }

        myTemplatePropertites.setDateFormatString(Utils.toString(new Date()));

        myTemplatePropertites.setDomainNameUpper(templateUtil.getUpperString(myTemplatePropertites.getDomainName()));

        myTemplatePropertites.setDomainNameLower(templateUtil.getLowerString(myTemplatePropertites.getDomainName()));

        logger.info("Start generating files...");
    }

    public static void generate() throws Exception{
        fileUtil.initial();

        // 模板文件夹载入
        File directory = new File(fileUtil.getCurrentDir());

        File[] files = directory.listFiles();
        for (File file : files) {
            // 读取文件
            String templateData = new String(Files.readAllBytes(file.toPath()), "UTF8");
            // 替换占位内容
            String sqlmapResult = fileUtil.convertTemplate(templateData);
            // 写入文件
            fileUtil.writeSteamToFile(file.getName(),sqlmapResult);
        }

        logger.info("Mission completed...");
    }

    private static String convertTemplate(String SourceData){
        VelocityContext context = new VelocityContext();
        context.put("nameSpace", myTemplatePropertites.getNameSpace());
        context.put("domainName", myTemplatePropertites.getDomainName());
        context.put("domainNameUpper", myTemplatePropertites.getDomainNameUpper());
        context.put("domainNameLower", myTemplatePropertites.getDomainNameLower());
        context.put("createDate", myTemplatePropertites.getDateFormatString());
        context.put("author", myTemplatePropertites.getAuthor());
        StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "code_gen", SourceData);
        return writer.getBuffer().toString();
    }

    private static void writeSteamToFile(String sourceFileName,String targetData)throws Exception{
        String rootPath = new File("").getCanonicalPath();
        String outFilePath = rootPath.concat(myTemplatePropertites.getGeneratePath());
        File resultOutDirectory = new File(outFilePath);
        // 如果不存在目录，创建当前文件夹
        if(!resultOutDirectory.exists()){
            resultOutDirectory.mkdir();
        }
        // 如果不存在指定文件，创建文件
        String outFileName = outFilePath.concat(sourceFileName.replace(myTemplatePropertites.getTemplateNamePrefix(), myTemplatePropertites.getDomainName()));
        File resultOutFile = new File(outFileName);
        if(!resultOutFile.exists()){
            resultOutFile.createNewFile();
        }
        try(BufferedWriter out = Files.newBufferedWriter(resultOutFile.toPath())){
            out.write(targetData);
        }catch (Exception e){
            logger.info("failed to write file.");
            e.printStackTrace();
            throw e;
        }
    }

}
