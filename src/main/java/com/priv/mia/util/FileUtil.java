package com.priv.mia.util;

import com.priv.mia.Generate.GenByTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(GenByTemplate.class);

    // 判断目录是否存在
    public static String getDir(String directory)throws Exception{
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

    // 读取文件
    public static String readFile(File file)throws Exception{
        // 读取文件
        return new String(Files.readAllBytes(file.toPath()), "UTF8");
    }

    // 如果不存在文件夹，创建文件夹
    public static void makeDir(File dir)throws Exception{
        if(!dir.exists()){
            dir.mkdir();
        }
    }

    // 如果不存在文件，创建文件夹
    public static void createNewFile(File file)throws Exception{
        if(!file.exists()){
            file.createNewFile();
        }
    }

    // 数据写入文件
    public static void wirteSteamToFile(Path resultOutFilePath, String targetData)throws Exception{

        try(BufferedWriter out = Files.newBufferedWriter(resultOutFilePath)){
            out.write(targetData);
        }catch (Exception e){
            logger.info("failed to write file.");
            e.printStackTrace();
            throw e;
        }
    }
}
