package com.sanli.model;

import lombok.Data;

@Data
public class MainTemplateConfig {
    // 是否生成循环
    private boolean loop = false;
    // 生成输出
    private String output = "求和结果：";
    // 作者信息
    private String author = "sanli";
}
