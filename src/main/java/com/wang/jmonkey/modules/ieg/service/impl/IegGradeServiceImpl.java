package com.wang.jmonkey.modules.ieg.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.common.utils.poi.excel.ImportExcelUtil;
import com.wang.jmonkey.modules.ieg.model.entity.IegGrade;
import com.wang.jmonkey.modules.ieg.mapper.IegGradeMapper;
import com.wang.jmonkey.modules.ieg.model.param.IegGradeParam;
import com.wang.jmonkey.modules.ieg.service.IIegGradeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * <p>
 * 报考指南——一分一段表 服务实现类
 * </p>
 *
 * @author HeJiawang
 * @since 2019-04-02
 */
@Slf4j
@Service
public class IegGradeServiceImpl extends ServiceImpl<IegGradeMapper, IegGrade> implements IIegGradeService {

    /**
     * mappere
     */
    @Autowired
    private IegGradeMapper mapper;

    /**
     * 基础路径
     */
    @Value("${jmonkey.static-locations-file}")
    private String staticLocation;

    /**
     * 分页查询信息
     * @param page page
     * @param entity 实体信息
     * @return
     */
    @Override
    public Page<IegGrade> list(Page<IegGrade> page, IegGrade entity) {
        EntityWrapper<IegGrade> wrapper = new EntityWrapper<>();
        wrapper.setEntity(entity);
        wrapper.orderBy("score", false);

        return super.selectPage(page, wrapper);
    }

    /**
     * 批量导入
     * @param param param
     * @return Boolean
     */
    @Transactional
    @Override
    public Boolean importGrade(IegGradeParam param) {
        Boolean result;

        File file = new File(this.staticLocation + param.getFilePath());
        try {
            ImportExcelUtil ei = new ImportExcelUtil(file, 0);
            List<IegGrade> list = ei.getDataList(IegGrade.class);
            list.forEach( iegGrade -> {
                if (iegGrade.getScore() !=null && iegGrade.getSort() != null && iegGrade.getNumber() != null) {
                    iegGrade.setYear(param.getYear()).setType(param.getType());
                    super.insert(iegGrade);
                }
            });

            result = true;
        } catch (Exception e) {
            log.error("ieg grade importGrade error :", e);
            result = false;
        }

        return result;
    }

    /**
     * 批量校验
     * @param param param
     * @return Boolean
     */
    @Override
    public HttpResult<Boolean> checkGrade(IegGradeParam param) {
        HttpResult<Boolean> result = new HttpResult<>();

        File file = new File(this.staticLocation + param.getFilePath());
        try {
            ImportExcelUtil ei = new ImportExcelUtil(file, 0);
            List<IegGrade> checkList = ei.getDataList(IegGrade.class);

            // 存在的个数与导入校验的条数不一致，校验失败
            if (checkList.size() != mapper.checkExist(param)) {
                return result.setIsSuccess(false).setMessage("数据条数不一致!!!");
            }

            // 只要有一条与数据库中的不一样，就是校验失败
            for (IegGrade grade : checkList) {
                EntityWrapper wrapper = new EntityWrapper();
                wrapper.setEntity(grade.setYear(param.getYear()).setType(param.getType()));
                if (super.selectCount(wrapper) != 1) {
                    return result.setIsSuccess(false).setMessage(grade.getScore() + "分信息不一致!!!");
                }
            }

            if( mapper.checkYes(param) > 0) result.setIsSuccess(true).setMessage("校验成功");
        } catch (Exception e) {
            log.error("ieg grade checkGrade error :", e);
            result.setIsSuccess(false).setMessage("校验失败, 原因不明, 请检查文档格式");
        }

        return result;
    }

    /**
     * 批量删除
     * @param entity 删除条件
     * @return true
     */
    @Override
    public Boolean delByYearAndType(IegGrade entity) {
        return mapper.delByYearAndType(entity) >= 0;
    }

    /**
     * 校验是否存在
     * @param param param
     * @return true 已存在
     */
    @Override
    public Boolean checkExist(IegGradeParam param) {
        return mapper.checkExist(param) > 0;
    }

}
