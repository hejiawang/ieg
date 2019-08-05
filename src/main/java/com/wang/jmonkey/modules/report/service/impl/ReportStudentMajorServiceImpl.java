package com.wang.jmonkey.modules.report.service.impl;

import com.wang.jmonkey.modules.report.model.entity.ReportStudentMajor;
import com.wang.jmonkey.modules.report.mapper.ReportStudentMajorMapper;
import com.wang.jmonkey.modules.report.service.IReportStudentMajorService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报告————学生意向专业 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
@Service
public class ReportStudentMajorServiceImpl extends ServiceImpl<ReportStudentMajorMapper, ReportStudentMajor> implements IReportStudentMajorService {

    /**
     * mapper
     */
    @Autowired
    private ReportStudentMajorMapper mapper;

    /**
     * margeList
     * @param studentId studentId
     * @param majorList majorList
     * @return boolean
     */
    @Override
    public boolean margeList(String studentId, List<ReportStudentMajor> majorList) {
        mapper.deleteByStudentId(studentId);

        majorList.forEach(major -> {
            major.setStudentId(studentId);

            super.insert(major);
        });

        return true;
    }
}
