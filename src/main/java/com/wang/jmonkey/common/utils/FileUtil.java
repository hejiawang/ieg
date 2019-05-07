package com.wang.jmonkey.common.utils;

import com.xiaoleilu.hutool.lang.Assert;
import com.xiaoleilu.hutool.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 文件工具类
 * @Auther: HeJiawang
 * @Date: 2018/12/28
 */
@Slf4j
@Component
public class FileUtil {

    @Component
    static class FileBuilder {
        /**
         * 文件保存基础路径
         */
        public static String staticLocationsFile;

        public static String fileTest;

        public static int fileFont;

        /**
         * 重命名文件分隔符
         */
        public static String FILE_NAME_SPLIT = "_";

        @Value("${jmonkey.static-locations-file}")
        public void setStaticLocationsFile( String staticLocationsFile ){
            this.staticLocationsFile = staticLocationsFile;
        }

        @Value("${jmonkey.ieg.file-test}")
        public void setFileTest (String fileTest) {
            this.fileTest = fileTest;
        }

        @Value("${jmonkey.ieg.file-font}")
        public void setFileFont (int fileFont) {
            this.fileFont = fileFont;
        }
    }

    /**
     * 生成上传文件的文件名
     *  uuid + _ + 原始文件名
     *
     *  优点：在服务器中能看出原始文件名
     *  缺点：存在数据库中的文件名长度不容易控制
     * @param fileName 原始文件名
     * @return 新文件名
     */
    public static String renderFileName( String fileName ){
        Assert.notBlank(fileName, "原始文件名不能为null");

        // IE上传时文件名是全路径名
        if(fileName.contains(File.separator)){
            String[] fs = fileName.split("\\\\");
            fileName = fs[fs.length - 1];
        }

        // 方便控制数据库中存储的字符长度
        if(fileName.length() > 100) fileName = fileName.substring(fileName.length()-100);

        return  UUIDUtil.value() + FileBuilder.FILE_NAME_SPLIT + fileName;
    }

    /**
     * 上传文件
     * @param filePath 上传文件路径
     * @param is 上传文件的输入流
     * @return
     */
    public static boolean uploadFile(String filePath, InputStream is) {
        Assert.notBlank(filePath, "上传文件路径不能为null");

        boolean result;
        try {
            File targetFile = new File(FileBuilder.staticLocationsFile + filePath);
            FileUtils.copyInputStreamToFile(is, targetFile);

            Font font = new Font("微软雅黑",Font.BOLD,FileBuilder.fileFont);
            float alpha = new Float(0.8);

            BufferedImage bi = ImageUtil.read(targetFile);
            int height = bi.getHeight(null);
            int fontSize = font.getSize();
            int y = (height - fontSize) / 2;

            int width = bi.getWidth(null);
            int x = (width - (FileBuilder.fileTest.length())*fontSize) / 2;

            ImageUtil.pressText(targetFile, targetFile, FileBuilder.fileTest, Color.white, font, x, y, alpha);

            result = true;
        } catch (IOException e) {
            log.error("uploadFile error : ", e);
            result = false;
        }
        return result;
    }
}
