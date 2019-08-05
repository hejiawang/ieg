package com.wang.jmonkey.modules.report.model.param;

import com.wang.jmonkey.modules.report.model.entity.ReportStudent;
import com.wang.jmonkey.modules.report.model.entity.ReportStudentArea;
import com.wang.jmonkey.modules.report.model.entity.ReportStudentMajor;
import com.wang.jmonkey.modules.report.model.entity.ReportStudentSchool;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * <p>
 * 报告————学生基本信息 Param
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ReportStudentParam extends ReportStudent {

    /**
     * 考生意向城市
     */
    private List<ReportStudentArea> areaList;

    /**
     * 学生意向专业
     */
    private List<ReportStudentMajor> majorList;

    /**
     * 学生意向院校
     */
    private List<ReportStudentSchool> schoolList;

    /**
     * converToEntity
     * @return ReportStudent
     */
    public ReportStudent converToEntity(){
        ReportStudent student = new ReportStudent();
        BeanUtils.copyProperties(this, student);

        return student;
    }
}
