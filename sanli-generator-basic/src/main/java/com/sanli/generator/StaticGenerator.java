package com.sanli.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StaticGenerator {
    public static void copy(String src, String dest) {
        FileUtil.copy(src, dest, false);
    }

    public static void copyByRecursive(String src, String dest) throws IOException {
        File input = new File(src);
        File output = new File(dest);
        if(input.isDirectory()){
            System.out.println("input: " + input.getName());
            System.out.println("output: " + output.getName());
            File destDir = new File(output, input.getName());
            if(!destDir.exists()){
                destDir.mkdirs();
            }
            File[] files = input.listFiles();
            if(files != null){
                for(File file : files){
                    copyByRecursive(file.getAbsolutePath(), output.getAbsolutePath());
                }
            }
        }
        else{
            Path path = new File(output, input.getName()).toPath();
            System.out.println("input: " + input);
            System.out.println("output: " + path);
            Files.copy(input.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static void main(String[] args) {
        // 获取当前项目根路径
        String projectPath = System.getProperty("user.dir");
        // 拷贝静态文件
        File parent = new File(projectPath).getParentFile();
        String inputPath = new File(parent, "demo/acm-template").getAbsolutePath();
        copy(inputPath, projectPath);
    }
}
