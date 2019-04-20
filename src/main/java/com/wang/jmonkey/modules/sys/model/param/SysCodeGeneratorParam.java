package com.wang.jmonkey.modules.sys.model.param;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SysCodeGeneratorParam {

    /**
     * 模块作者
     */
    private String author;
    /**
     * 系统名称 如系统设置模块的包为com.wang.jmonkey.modules.sys.* 那么该字段值为name
     */
    private String name;
    /**
     * 表名
     */
    private String tableName;

    /**
     * 输出目录, 如D:/WorkSpace/JMonkey2.0/src/main/java
     */
    private String outputDir;
    /**
     * 数据库ip
     */
    private String dbIp;
    /**
     * 数据库端口
     */
    private String dbPost;
    /**
     * 数据库名称
     */
    private String dbTable;
    /**
     * 数据库登陆名称
     */
    private String dbName;
    /**
     * 数据库登陆密码
     */
    private String dbPassword;

}
