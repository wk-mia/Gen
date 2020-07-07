package com.priv.mia.generate;

import com.priv.mia.util.FileUtil;
import com.priv.mia.domain.TemplateProperties;
import com.priv.mia.util.TemplateUtil;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;

@Component
public class GenByTemplate {

    private static final Logger logger = LoggerFactory.getLogger(GenByTemplate.class);

    private static TemplateProperties myTemplateProperties;

    @Autowired
    private TemplateProperties templateProperties;

    @PostConstruct
    private void init() {
        myTemplateProperties = templateProperties;
    }


    public static void generate() throws Exception{

        TemplateUtil.initial(myTemplateProperties);
        logger.info("Start generating files...");

        // 获取配置文件中模板文件所在目录
        String dirPath = ResourceUtils.getURL("classpath:").getPath()
                .concat(myTemplateProperties.getTemplateDirectory());
        File directory = new File(FileUtil.getDir(dirPath));

        File[] files = directory.listFiles();
        for (File file : files) {
            // 读取文件
            String templateData = FileUtil.readFile(file);
            // 替换占位内容
            String sqlmapResult = GenByTemplate.convertTemplate(templateData);
            // 写入文件
            String rootPath = new File("").getCanonicalPath();
            String outFilePath = rootPath.concat(myTemplateProperties.getDestPath());
            File resultOutDirectory = new File(outFilePath);
            FileUtil.makeDir(resultOutDirectory);

            String outFileName = outFilePath.concat(TemplateUtil.getNewName(file.getName(), myTemplateProperties.getReNameRule()));
            File resultOutFile = new File(outFileName);
            FileUtil.createNewFile(resultOutFile);

            FileUtil.wirteSteamToFile(resultOutFile.toPath(),sqlmapResult);
        }

        logger.info("Mission completed...");
    }

    private static String convertTemplate(String SourceData)throws Exception{
        // 增加未定义的标签
        HashMap<String,Object> appendProperties = TemplateUtil.appendProperties(SourceData, myTemplateProperties.getTagMap());
        if(appendProperties != null && appendProperties.size() > 0) {
            myTemplateProperties.getTagMap().putAll(appendProperties);
        }

        VelocityContext context = new VelocityContext(myTemplateProperties.getTagMap());

        StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "code_gen", SourceData);
        return writer.getBuffer().toString();
    }


}
