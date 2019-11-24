package com.wang.jmonkey.modules.ieg.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 院校信息检索页 列表数据
 * @Auther: heJiawang
 * @Date: 2019-11-23
 */
@Data
@Accessors(chain = true)
public class IegReportListDto {

    /**
     * 主键ID
     */
    private String schoolId;

    /**
     * 学校logo
     */
    private String logo;

    /**
     * 学校名称
     */
    private String name;

    /**
     * 学校简介
     */
    private String describe;

    /**
     * 院校所在地——省
     */
    private String areaProvince;

    /**
     * 院校所在地——市
     */
    private String areaCity;

    /**
     * 院校所在地——区
     */
    private String areaArea;

    /**
     * 院校所在地详细地址
     */
    private String areaDetail;

    /**
     * 特性名称
     */
    private List<String> featureNames;

    /**
     * 专业名称
     */
    private List<String> majorNames;

}
