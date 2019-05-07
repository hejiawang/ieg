package com.wang.jmonkey.common.http.abs;

import com.wang.jmonkey.common.http.result.HttpResult;
import com.wang.jmonkey.common.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description: 基础控制器
 * @Auther: HeJiawang
 * @Date: 2018/04/11
 */
public abstract class BaseHttp {

    private static Logger logger = LoggerFactory.getLogger(BaseHttp.class);

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;

    /**
     * 数据规则
     */
    protected String dataScope;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();

        // 获取数据规则
        if (null != request.getAttribute("dataScope"))
            this.dataScope = request.getAttribute("dataScope").toString();
    }

    /**
     * 同意上传文件接口模板
     * @param uploadFile MultipartFile
     * @param basePath 不同业务模块上传的文件目录
     * @return HttpResult
     */
    public HttpResult<String> uploadFile(MultipartFile uploadFile, String basePath, Boolean isTest) {
        HttpResult<String> result = new HttpResult<>();

        try {
            String filePath = basePath + FileUtil.renderFileName(uploadFile.getOriginalFilename());

            if( FileUtil.uploadFile(filePath, uploadFile.getInputStream(), isTest) ) result.setResult(filePath);
            else result.setIsSuccess(false);
        } catch (IOException e) {
            logger.error("upload file error : ", e);
            result.setIsSuccess(false);
        }

        return result;
    }
}
