package com.sanli.generator;

import com.sanli.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void doGenerator(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        File parent = new File(projectPath).getParentFile();
        // 获取当前项目根路径
        String inputPath = new File(parent, "demo/acm-template").getAbsolutePath();
        // 拷贝静态文件
        StaticGenerator.copyByRecursive(inputPath, projectPath);
        // 生成动态文件
        String templatePath = projectPath + "/src/main/resources/templates/MainTemplate.java.ftl";
        String output = projectPath + "/MainTemplate.java";
        DynamicGenerator.doGenerate(templatePath, output, model);

    }
    public static void main(String[] args) {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("sanli");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutput("Sum: ");
        try {
            doGenerator(mainTemplateConfig);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }
}
