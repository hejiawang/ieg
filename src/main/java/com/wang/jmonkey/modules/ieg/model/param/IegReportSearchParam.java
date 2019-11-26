package com.wang.jmonkey.modules.ieg.model.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 报告检索条件信息
 */
@Data
@Accessors(chain = true)
public class IegReportSearchParam {

    /**
     * 专业名称 使用空格分割
     */
    private String major;
    /**
     * 院校名称 使用空格分割
     */
    private String name;
    /**
     * 所在地区 省 市 区
     */
    private List<String> area;
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

    private Integer current;
    private Integer size;
    private Integer total;

    /**
     * 分页——limitStrat
     */
    private int limitStart;

    /**
     * buildLimitStart
     */
    public IegReportSearchParam buildLimitStart() {
        this.limitStart = this.size * ( this.current - 1 );

        return this;
    }
}
