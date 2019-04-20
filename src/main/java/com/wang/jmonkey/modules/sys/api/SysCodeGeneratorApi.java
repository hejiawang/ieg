package com.wang.jmonkey.modules.sys.api;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.modules.sys.model.param.SysCodeGeneratorParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 代码生成 api
 * @Auther: HeJiawang
 * @Date: 2018-12-11
 */
@Slf4j
@RestController
@RequestMapping("/sys/code")
public class SysCodeGeneratorApi extends BaseHttp {

    /**
     * 代码生成
     * @param param
     * @return
     */
    @PostMapping(value = "/generator")
    public HttpResult<Boolean> generator(@RequestBody SysCodeGeneratorParam param ){
        try {
            autoGenerator(param).execute();

            return new HttpResult<>(true);
        } catch (Exception e){
            log.error(e.getMessage());

            return new HttpResult<>(false);
        }
    }

    private AutoGenerator autoGenerator(SysCodeGeneratorParam param){
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig(param.getAuthor(), param.getOutputDir()))
                .setTemplate(templateConfig())
                .setDataSource(dataSourceConfig(param.getDbIp(), param.getDbPost(), param.getDbTable(), param.getDbName(), param.getDbPassword()))
                .setStrategy(strategyConfig(param.getTableName()))
                .setPackageInfo( packageConfig(param.getName()));

        return autoGenerator;
    }

    private GlobalConfig globalConfig(String author, String outputDir){
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

    private DataSourceConfig dataSourceConfig(String dbIp, String dbPost, String dbTable, String dbName, String dbPassword){
        DataSourceConfig config = new DataSourceConfig();
        config.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://" + dbIp + ":" + dbPost + "/" + dbTable + "?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false")
                .setUsername(dbName).setPassword(dbPassword).setDriverName("com.mysql.cj.jdbc.Driver");

        return config;
    }

    private StrategyConfig strategyConfig(String tableName){
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

    private PackageConfig packageConfig(String name){
        PackageConfig config = new PackageConfig();
        config.setParent("com.wang.jmonkey.modules." + name)
                .setController("api")
                .setEntity("model.entity");
        return config;
    }
}
