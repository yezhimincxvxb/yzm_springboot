package com.yzm.mybatis.config;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 逆向工程，代码生成器
 */
public class CodeGenerator {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        //指定逆向工程配置文件
        File configFile = new File(CodeGenerator.class.getResource("/generatorConfig.xml").getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

}
