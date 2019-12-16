package com.wang.jmonkey.modules.ieg.model.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 院校信息查询记录
 */
@Data
@Accessors(chain = true)
public class IegSchoolLogParam {

    /**
     * 学校名称
     */
    private String schoolName;
    /**
     * 顾问名称
     */
    private String userName;

    private Date startDate;

    private Date endDate;

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
    public IegSchoolLogParam buildLimitStart() {
        this.limitStart = this.size * ( this.current - 1 );

        return this;
    }
}
