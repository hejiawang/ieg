package com.wang.jmonkey.modules.report.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wang.jmonkey.modules.report.model.entity.ReportStudentRecord;
import com.wang.jmonkey.modules.report.mapper.ReportStudentRecordMapper;
import com.wang.jmonkey.modules.report.service.IReportStudentRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 报告————学生来访记录 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-08-03
 */
@Service
public class ReportStudentRecordServiceImpl extends ServiceImpl<ReportStudentRecordMapper, ReportStudentRecord> implements IReportStudentRecordService {

    /**
     * 查询信息
     * @param entity 实体信息
     * @return HttpResult
     */
    @Override
    public List<ReportStudentRecord> list(ReportStudentRecord entity) {
        EntityWrapper<ReportStudentRecord> wrapper = new EntityWrapper<>(entity);
        wrapper.orderBy("record_date", false);

        return super.selectList(wrapper);
    }
}
