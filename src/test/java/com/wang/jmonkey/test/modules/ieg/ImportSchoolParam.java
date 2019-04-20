package com.wang.jmonkey.test.modules.ieg;

import com.wang.jmonkey.common.utils.poi.annotation.ExcelField;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ImportSchoolParam {

    /**
     * 批次代码 专科批7 本科批3
     */
    @ExcelField(title="pcdm", align=2)
    private String pcdm;

    /**
     * 科类代码 1是文史  5是理科
     */
    @ExcelField(title="kldm", align=2)
    private String kldm;

    /**
     * 投档单位
     */
    @ExcelField(title="tddw", align=2)
    private String tddw;

    /**
     * 投档单位名称
     */
    @ExcelField(title="tddwmc", align=2)
    private String tddwmc;

    /**
     * 院校代码
     */
    @ExcelField(title="yxdh", align=2)
    private String yxdh;

    /**
     * 专业代码
     */
    @ExcelField(title="zydh", align=2)
    private String zydh;

    /**
     * 专业名称
     */
    @ExcelField(title="zymc", align=2)
    private String zymc;

    /**
     * 原计划数
     */
    @ExcelField(title="yjhs", align=2)
    private String yjhs;

    /**
     * 录取数
     */
    @ExcelField(title="lqs", align=2)
    private String lqs;

}
