package com.wang.jmonkey.utils.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

/**
 * @Description:
 * @Auther: HeJiawang
 * @Date: 2018/10/25
 */
public class CodeGenerator {

    private String outputDir = "D:/WorkSpace/JMonkey2.0/src/main/java";
    private String author = "HeJiawang";
    private String tableName = "sys_task";

    @Test
    public void codeGeneratorTest(){
        autoGenerator().execute();
    }

    private AutoGenerator autoGenerator(){
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig())
                .setTemplate(templateConfig())
                .setDataSource(dataSourceConfig())
                .setStrategy(strategyConfig())
                .setPackageInfo( packageConfig());

        return autoGenerator;
    }

    private GlobalConfig globalConfig(){
        GlobalConfig config = new GlobalConfig();
        config.setControllerName("%sApi")
                .setAuthor(author)
                .setBaseResultMap(true)
                .setOutputDir(outputDir)
                .setActiveRecord(true)
                .setEnableCache(false)
                .setFileOverride(false);

        return config;
    }

    private DataSourceConfig dataSourceConfig(){
        DataSourceConfig config = new DataSourceConfig();
        config.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://localhost:3306/j_monkey?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false")
                .setUsername("root").setPassword("123456").setDriverName("com.mysql.cj.jdbc.Driver");

        return config;
    }

    private StrategyConfig strategyConfig(){
        StrategyConfig config = new StrategyConfig();
        config.setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setSuperEntityClass("com.wang.jmonkey.common.model.BaseEntity")
                .setSuperEntityColumns(new String[]{"create_date", "create_by", "update_date", "update_by", "delete_flag", "remark"})
                .setEntityLombokModel(true)
                .setInclude(tableName);

        return config;
    }

    private TemplateConfig templateConfig(){
        TemplateConfig config = new TemplateConfig();
        config.setController("/templates/Api.java");

        return config;
    }

    private PackageConfig packageConfig(){
        PackageConfig config = new PackageConfig();
        config.setParent("com.wang.jmonkey.modules.sys")
                .setController("api")
                .setEntity("model.entity");
        return config;
    }


}
