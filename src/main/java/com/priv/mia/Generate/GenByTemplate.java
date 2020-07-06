package com.priv.mia.Generate;

import com.priv.mia.util.FileUtil;
import com.priv.mia.util.TemplateConfig;
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

@Component
public class GenByTemplate {

    private static final Logger logger = LoggerFactory.getLogger(GenByTemplate.class);

    private static TemplateConfig myTemplateConfig;

    @Autowired
    private TemplateConfig templateConfig;

    @PostConstruct
    private void init() {
        myTemplateConfig = templateConfig;
    }


    public static void generate() throws Exception{

        TemplateUtil.initial(myTemplateConfig);
        logger.info("Start generating files...");

        // 获取配置文件中模板文件所在目录
        String dirPath = ResourceUtils.getURL("classpath:").getPath()
                .concat(myTemplateConfig.getTemplateDirectory());
        File directory = new File(FileUtil.getDir(dirPath));

        File[] files = directory.listFiles();
        for (File file : files) {
            // 读取文件
            String templateData = FileUtil.readFile(file);
            // 替换占位内容
            String sqlmapResult = GenByTemplate.convertTemplate(templateData);
            // 写入文件
            String rootPath = new File("").getCanonicalPath();
            String outFilePath = rootPath.concat(myTemplateConfig.getDestPath());
            File resultOutDirectory = new File(outFilePath);
            FileUtil.makeDir(resultOutDirectory);

            String outFileName = outFilePath.concat(TemplateUtil.getNewName(file.getName(),myTemplateConfig.getReNameRule()));
            File resultOutFile = new File(outFileName);
            FileUtil.createNewFile(resultOutFile);

            FileUtil.wirteSteamToFile(resultOutFile.toPath(),sqlmapResult);
        }

        logger.info("Mission completed...");
    }

    private static String convertTemplate(String SourceData){
        VelocityContext context = new VelocityContext(myTemplateConfig.getTagMap());
        StringWriter writer = new StringWriter();
        Velocity.evaluate(context, writer, "code_gen", SourceData);
        return writer.getBuffer().toString();
    }


}
