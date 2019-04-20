package com.wang.jmonkey.modules.ieg.api;

import com.wang.jmonkey.common.http.abs.BaseHttp;
import com.wang.jmonkey.common.http.result.HttpResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 报考指南——学校详细信息 api
 * @Auther: HeJiawang
 * @Date: 2019-03-26
 */
@RestController
@RequestMapping("/ieg/school/detail")
public class IegSchoolDetailApi extends BaseHttp {

    /**
     * ContentParam
     */
    @Component
    static class ContentParam {
        public static String describe;
        public static String faculty;
        public static String life;
        public static String scholarship;

        @Value("${jmonkey.ieg.school.img.describe}")
        public void setDescribe( String describe ){
            ContentParam.describe = describe;
        }

        @Value("${jmonkey.ieg.school.img.faculty}")
        public void setFaculty(String faculty) {
            ContentParam.faculty = faculty;
        }

        @Value("${jmonkey.ieg.school.img.life}")
        public void setLife(String life) {
            ContentParam.life = life;
        }

        @Value("${jmonkey.ieg.school.img.scholarship}")
        public void setScholarship(String scholarship) {
            ContentParam.scholarship = scholarship;
        }
    }

    /**
     * contentMap
     */
    private Map<String, String> contentMap = new HashMap<String, String>(){
        {
            put("describe", ContentParam.describe);
            put("faculty", ContentParam.faculty);
            put("life", ContentParam.life);
            put("scholarship", ContentParam.scholarship);
        }
    };
    /**
     * 上传图片
     * @param uploadFile uploadFile
     * @return Boolean
     */
    @PostMapping("/file")
    public HttpResult<String> file(@RequestParam(value = "file") MultipartFile uploadFile, String content ){
        return super.uploadFile(uploadFile, contentMap.get(content));
    }
}
