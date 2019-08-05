package com.wang.jmonkey.modules.report.service.impl;

import com.wang.jmonkey.modules.report.model.entity.ReportStudentArea;
import com.wang.jmonkey.modules.report.mapper.ReportStudentAreaMapper;
import com.wang.jmonkey.modules.report.service.IReportStudentAreaService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 报告————学生意向城市 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
@Service
public class ReportStudentAreaServiceImpl extends ServiceImpl<ReportStudentAreaMapper, ReportStudentArea> implements IReportStudentAreaService {

    /**
     * mapper
     */
    @Autowired
    private ReportStudentAreaMapper mapper;

    /**
     * margeList
     * @param studentId studentId
     * @param areaList areaList
     * @return boolean
     */
    @Transactional
    @Override
    public boolean margeList(String studentId, List<ReportStudentArea> areaList) {
        mapper.deleteByStudentId(studentId);

        areaList.forEach(reportStudentArea -> {
            reportStudentArea.setStudentId(studentId);

            super.insert(reportStudentArea);
        });

        return true;
    }
}
