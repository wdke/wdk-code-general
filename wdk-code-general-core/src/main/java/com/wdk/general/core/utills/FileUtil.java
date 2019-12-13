package com.wdk.general.core.utills;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @ClassName FileUtil
 * @Description TODO
 * @Author wdk
 * @Date 2018/6/27 13:34
 * @Version 1.0
 */
public class FileUtil {


    private static final Logger logger= LoggerFactory.getLogger(FileUtil.class);

    public static void delFile(String path, String filename){
        File file=new File(path+"/"+filename);
        if(file.exists()&&file.isFile())
            file.delete();
    }


    public static boolean delFile(String filename){
        File dirFile=new File(filename);
        // 如果dir对应的文件不存在，则退出
        if (!dirFile.exists()) {
            return false;
        }

        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {

            for (File file : dirFile.listFiles()) {
                delFile(file);
            }
        }

        return dirFile.delete();
    }


    public static boolean delFile(File dirFile){
        // 如果dir对应的文件不存在，则退出
        if (!dirFile.exists()) {
            return false;
        }

        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {

            for (File file : dirFile.listFiles()) {
                delFile(file);
            }
        }

        return dirFile.delete();
    }
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            logger.info("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            logger.info("创建目录" + destDirName + "成功！");
            return true;
        } else {
            logger.info("创建目录" + destDirName + "失败！");
            return false;
        }
    }

    public static void creatDirectory(String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            logger.info("成功创建目录！",file.getParentFile().getPath());
        }
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
            logger.info("成功创建文件！",path);
        } else {
            logger.info("成功创建失败，文件已经存在！",path);
        }
    }

    public static void creatFile(String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
            logger.info("成功创建目录！",file.getParentFile().getPath());
        }
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("成功创建文件！",path);
        } else {
            logger.info("创建失败，文件已经存在！",path);
        }
    }
    public static void write(String path, String content) throws IOException {
        String charSet="UTF-8";
        creatFile(path);
        FileOutputStream out=new FileOutputStream(path);
        OutputStreamWriter writer = new OutputStreamWriter(out, charSet);
        try {

            logger.info("开始写文件！",path);
            writer.write(content.toString());
            logger.info("文件已经读写完毕！",path);
        } catch (Exception e) {
            logger.info("读写文件时发生异常！",path,"异常信息为"+e.getMessage());
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }


    public static void write(String path, String fileName, String content) throws IOException {
        System.out.println(path+"/"+fileName);
        creatDirectory(path);
        File file=new File(path+"/"+fileName);
        if(!file.exists()) {
            try {
                file.createNewFile();
                System.out.println(fileName + "创建成功！");
                logger.info("file 不存在 ，创建成功");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String charSet = "UTF-8";
            FileOutputStream out = new FileOutputStream(path + "/" + fileName);
            OutputStreamWriter writer = new OutputStreamWriter(out, charSet);
            try {
                writer.write(content.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        }else{
            System.out.println("文件已经存在，未重写");
            logger.info("file 文件已经存在，未重写");

        }
    }
}
