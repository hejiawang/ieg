package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.utils.poi.excel.ImportExcelUtil;
import com.wang.jmonkey.modules.ieg.model.entity.IegEnrollInfo;
import com.wang.jmonkey.modules.ieg.mapper.IegEnrollInfoMapper;
import com.wang.jmonkey.modules.ieg.model.param.IegEnrollInfoParam;
import com.wang.jmonkey.modules.ieg.service.IIegEnrollInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 报考指南——招生录取投档线信息 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-04
 */
@Slf4j
@Service
public class IegEnrollInfoServiceImpl extends ServiceImpl<IegEnrollInfoMapper, IegEnrollInfo> implements IIegEnrollInfoService {

    /**
     * 基础路径
     */
    @Value("${jmonkey.static-locations-file}")
    private String staticLocation;

    /**
     * 导入投档分数线信息
     * @param param param
     * @return Boolean
     */
    @Transactional
    @Override
    public Boolean importInfo(IegEnrollInfoParam param) {
        Boolean result;

        File file = new File(this.staticLocation + param.getFilePath());
        try {
            ImportExcelUtil ei = new ImportExcelUtil(file, 0);
            List<IegEnrollInfo> list = ei.getDataList(IegEnrollInfo.class);
            list.forEach( enrollInfo -> {
                if (StringUtils.isNotEmpty(enrollInfo.getSubmitCode()) &&
                        StringUtils.isNotEmpty(enrollInfo.getSchoolName()) &&
                        StringUtils.isNotEmpty(enrollInfo.getSchoolCode())) {

                    String schoolCode = enrollInfo.getSchoolCode();
                    if (schoolCode.length() < 4) {
                        for (int i = 4, l = schoolCode.length(); i > l; i--) {
                            schoolCode = "0" + schoolCode;
                        }
                    }

                    String submitCode = enrollInfo.getSubmitCode();
                    if (submitCode.length() < 4) {
                        for (int i = 4, l = submitCode.length(); i > l; i--) {
                            submitCode = "0" + submitCode;
                        }
                    }

                    super.insert(
                        enrollInfo.setEnrollId(param.getEnrollId()).setSchoolCode(schoolCode).setSubmitCode(submitCode)
                    );
                }
            });

            result = true;
        } catch (Exception e) {
            log.error("enroll info importInfo error :", e);
            result = false;
        }

        return result;
    }

    /**
     * list page
     * @param page page
     * @param entity entity
     * @return Page<IegEnrollInfo>
     */
        @Override
        public Page<IegEnrollInfo> listPage(Page<IegEnrollInfo> page, IegEnrollInfo entity) {
            EntityWrapper<IegEnrollInfo> wrapper = new EntityWrapper<>();
            wrapper.setEntity(entity);
            wrapper.orderBy("school_code");

        return super.selectPage(page, wrapper);
    }

    /**
     * 清空投档分数线信息
     * @param enrollId enrollId
     * @return Boolean
     */
    @Override
    public Boolean delByEnroll(String enrollId) {
        EntityWrapper<IegEnrollInfo> wrapper = new EntityWrapper<>();
        wrapper.setEntity(new IegEnrollInfo().setEnrollId(enrollId));

        return super.delete(wrapper);
    }
}
