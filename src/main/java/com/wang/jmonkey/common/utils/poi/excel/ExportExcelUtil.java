package com.wang.jmonkey.common.utils.poi.excel;

import com.wang.jmonkey.common.model.BaseEnum;
import com.wang.jmonkey.common.utils.poi.annotation.ExcelField;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Description: poi 导出Excel工具类
 * @Auther: HeJiawang
 * @Date: 2019/3/17
 */
public class ExportExcelUtil {

    /**
     * 工作薄对象
     */
    private SXSSFWorkbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     * 当前行号
     */
    private int rownum;

    /**
     * 注解列表（Object[]{ ExcelField, Field }）
     */
    List<Object[]> annotationList = CollectionUtil.newArrayList();

    /**
     * 构造函数
     * @param title 表格标题，传“空值”，表示无标题
     * @param cls 实体对象，通过annotation.ExportField获取标题
     */
    public ExportExcelUtil(String title, Class<?> cls) {
        // 获取ExcelField注解的字段
        Field[] fs = cls.getDeclaredFields();
        Arrays.stream(fs).forEach(f -> {
            ExcelField ef = f.getAnnotation(ExcelField.class);
            if (null != ef) annotationList.add(new Object[]{ef, f});
        });

        // 获取导出数据的表头列表
        List<String> headerList = CollectionUtil.newArrayList();
        annotationList.forEach( os -> {
            String t = ((ExcelField)os[0]).title();
            headerList.add(t);
        });

        // 初始化Excel
        new InitExcel(title, headerList);
    }

    /**
     * 添加数据（通过annotation.ExportField添加数据）
     * @return list 数据列表
     */
    public <E> ExportExcelUtil setDataList(List<E> dataList){
        dataList.forEach( e -> {
            int colunm = 0;
            Row row = sheet.createRow(rownum++);

            for (Object[] os : annotationList){
                ExcelField ef = (ExcelField)os[0];
                Field f = (Field)os[1];
                f.setAccessible(true);
                Object val = ReflectionUtils.getField(f, e);

                this.addCell(row, colunm++, val, ef.align(), ef.fieldType());
            }
        });

        return this;
    }

    /**
     * 添加一个单元格
     * @param row 添加的行
     * @param column 添加列号
     * @param val 添加值
     * @param align 对齐方式（1：靠左；2：居中；3：靠右）
     * @return 单元格对象
     */
    public Cell addCell(Row row, int column, Object val, int align, Class<?> fieldType) {
        Cell cell = row.createCell(column);
        CellStyle style = styles.get("data"+(align>=1&&align<=3?align:""));

        if (val == null){
            cell.setCellValue("");
        } else if (val instanceof String) {
            cell.setCellValue((String) val);
        } else if (val instanceof Integer) {
            cell.setCellValue((Integer) val);
        } else if (val instanceof Long) {
            cell.setCellValue((Long) val);
        } else if (val instanceof Double) {
            cell.setCellValue((Double) val);
        } else if (val instanceof Float) {
            cell.setCellValue((Float) val);
        } else if (val instanceof BaseEnum) {
            cell.setCellValue(((BaseEnum) val).getDesc());
        } else if (val instanceof Date) {
            DataFormat format = wb.createDataFormat();
            style.setDataFormat(format.getFormat("yyyy-MM-dd"));
            cell.setCellValue((Date) val);
        } else {
            cell.setCellValue(val.toString());
        }

        cell.setCellStyle(style);
        return cell;
    }

    /**
     * 输出到文件
     * @param name 输出文件名
     */
    public ExportExcelUtil write(String name) throws IOException {
        this.write(new FileOutputStream(name));

        return this;
    }

    /**
     * 输出到客户端
     * @param fileName 输出文件名
     */
    public ExportExcelUtil write(HttpServletResponse response, String fileName) throws IOException{
        response.reset();
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(fileName, "UTF-8"));
        write(response.getOutputStream());

        return this;
    }

    /**
     * 输出数据流
     * @param os 输出数据流
     */
    public ExportExcelUtil write(OutputStream os) throws IOException{
        wb.write(os);

        return this;
    }

    /**
     * 清理临时文件
     */
    public ExportExcelUtil dispose(){
        wb.dispose();

        return this;
    }

    /**
     * 初始化Excel
     */
    private class InitExcel {

        public InitExcel (String title, List<String> headerList) {
            wb = new SXSSFWorkbook(500);
            sheet = wb.createSheet(title);
            styles = createStyles(wb);

            // Create title
            this.initializeTitle(title, headerList.size()-1);

            // Create header
            this.initializeHeader(headerList);
        }

        /**
         * 初始化Excel表头列表
         * @param headerList 表头列表
         */
        private void initializeHeader(List<String> headerList) {
            if (headerList == null) throw new RuntimeException("headerList not null!");

            Row headerRow = sheet.createRow(rownum++);
            headerRow.setHeightInPoints(16);

            for (int i = 0; i < headerList.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellStyle(styles.get("header"));
                cell.setCellValue(headerList.get(i));
                // sheet.autoSizeColumn(i);
            }

            for (int i = 0; i < headerList.size(); i++) {
                int colWidth = sheet.getColumnWidth(i)*2;
                sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);
            }
        }

        /**
         * 初始化Excel表格标题
         * @param title 表格标题
         * @param titleLastCol titleLastCol
         */
        private void initializeTitle(String title, int titleLastCol) {
            if (StringUtils.isNotBlank(title)){
                Row titleRow = sheet.createRow(rownum++);
                titleRow.setHeightInPoints(30);
                Cell titleCell = titleRow.createCell(0);
                titleCell.setCellStyle(styles.get("title"));
                titleCell.setCellValue(title);
                sheet.addMergedRegion(
                        new CellRangeAddress(titleRow.getRowNum(), titleRow.getRowNum(), titleRow.getRowNum(), titleLastCol)
                );
            }
        }

        /**
         * 创建表格样式
         * @param wb 工作薄对象
         * @return 样式列表
         */
        private Map<String, CellStyle> createStyles(Workbook wb) {
            Map<String, CellStyle> styles = new HashMap<>();

            CellStyle style = wb.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            Font titleFont = wb.createFont();
            titleFont.setFontName("Arial");
            titleFont.setFontHeightInPoints((short) 16);
            titleFont.setBold(true);
            style.setFont(titleFont);
            styles.put("title", style);

            style = wb.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setBorderRight(BorderStyle.THIN);
            style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
            style.setBorderTop(BorderStyle.THIN);
            style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
            Font dataFont = wb.createFont();
            dataFont.setFontName("Arial");
            dataFont.setFontHeightInPoints((short) 10);
            style.setFont(dataFont);
            styles.put("data", style);

            style = wb.createCellStyle();
            style.cloneStyleFrom(styles.get("data"));
            style.setAlignment(HorizontalAlignment.LEFT);
            styles.put("data1", style);

            style = wb.createCellStyle();
            style.cloneStyleFrom(styles.get("data"));
            style.setAlignment(HorizontalAlignment.CENTER);
            styles.put("data2", style);

            style = wb.createCellStyle();
            style.cloneStyleFrom(styles.get("data"));
            style.setAlignment(HorizontalAlignment.RIGHT);
            styles.put("data3", style);

            style = wb.createCellStyle();
            style.cloneStyleFrom(styles.get("data"));
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = wb.createFont();
            headerFont.setFontName("Arial");
            headerFont.setFontHeightInPoints((short) 10);
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            style.setFont(headerFont);
            styles.put("header", style);

            return styles;
        }
    }

}
