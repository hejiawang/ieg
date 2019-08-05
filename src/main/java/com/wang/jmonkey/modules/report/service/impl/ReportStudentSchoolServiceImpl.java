package com.wang.jmonkey.modules.report.service.impl;

import com.wang.jmonkey.modules.report.model.entity.ReportStudentSchool;
import com.wang.jmonkey.modules.report.mapper.ReportStudentSchoolMapper;
import com.wang.jmonkey.modules.report.service.IReportStudentSchoolService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报告————学生意向院校 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
@Service
public class ReportStudentSchoolServiceImpl extends ServiceImpl<ReportStudentSchoolMapper, ReportStudentSchool> implements IReportStudentSchoolService {

    /**
     * mapper
     */
    @Autowired
    private ReportStudentSchoolMapper mapper;

    /**
     * margeList
     * @param studentId studentId
     * @param schoolList schoolList
     * @return boolean
     */
    @Override
    public boolean margeList(String studentId, List<ReportStudentSchool> schoolList) {
        mapper.deleteByStudentId(studentId);

        schoolList.forEach(school -> {
            school.setStudentId(studentId);

            super.insert(school);
        });

        return true;
    }
}
