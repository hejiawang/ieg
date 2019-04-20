package com.wang.jmonkey.common.utils.poi.excel;

import com.wang.jmonkey.common.utils.poi.annotation.ExcelField;
import com.xiaoleilu.hutool.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ImportExcelUtil {

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 标题行号
     */
    private int headerNum;

    public ImportExcelUtil(File file, int headerNum) throws IOException {
        this(file, headerNum, 0);
    }

    public ImportExcelUtil(File file, int headerNum, int sheetIndex) throws IOException {
        this(file.getName(), new FileInputStream(file), headerNum, sheetIndex);
    }

    public ImportExcelUtil(String fileName, InputStream is, int headerNum, int sheetIndex) throws IOException {
        if (StringUtils.isBlank(fileName)) throw new RuntimeException("导入文档为空!");

        if(fileName.toLowerCase().endsWith("xls")) this.wb = new HSSFWorkbook(is);
        else if(fileName.toLowerCase().endsWith("xlsx")) this.wb = new XSSFWorkbook(is);
        else throw new RuntimeException("文档格式不正确!");

        if (this.wb.getNumberOfSheets()<sheetIndex) throw new RuntimeException("文档中没有工作表!");

        this.sheet = this.wb.getSheetAt(sheetIndex);
        this.headerNum = headerNum;
    }

    /**
     * 获取导入数据列表
     * @param cls 导入对象类型
     * @return 对象实体list
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <E> List<E> getDataList(Class<E> cls) throws IllegalAccessException, InstantiationException {
        List<Object[]> annotationList = CollectionUtil.newArrayList();

        // 获取ExcelField注解的字段
        Field[] fs = cls.getDeclaredFields();
        Arrays.stream(fs).forEach(f -> {
            ExcelField ef = f.getAnnotation(ExcelField.class);
            if (null != ef) annotationList.add(new Object[]{ef, f});
        });

        List<E> dataList = CollectionUtil.newArrayList();
        // 遍历Excel单元格
        for (int i = this.getDataRowNum(); i < this.getLastDataRowNum(); i++) {
            E e = cls.newInstance();

            int column = 0;
            Row row = this.getRow(i);
            // 获取实体字段值
            for (Object[] os : annotationList){
                Object val = this.getCellValue(row, column++);

                if (val != null){
                    Class<?> valType = ((Field)os[1]).getType();
                    val = this.converValue(valType, val);

                    Field f = (Field)os[1];
                    f.setAccessible(true);
                    ReflectionUtils.setField(f, e, val);
                }
            }

            dataList.add(e);
        }

        return dataList;
    }

    /**
     * 转换值类型
     * @param valType 值类型
     * @param val 值
     * @return 值
     */
    public Object converValue (Class<?> valType, Object val) {
        try {
            if (valType == String.class){
                String s = String.valueOf(val.toString());
                if(StringUtils.endsWith(s, ".0")) val = StringUtils.substringBefore(s, ".0");
                else val = String.valueOf(val.toString());
            }else if (valType == Integer.class){
                val = Double.valueOf(val.toString()).intValue();
            }else if (valType == Long.class){
                val = Double.valueOf(val.toString()).longValue();
            }else if (valType == Double.class){
                val = Double.valueOf(val.toString());
            }else if (valType == Float.class){
                val = Float.valueOf(val.toString());
            }else if (valType == Date.class){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                val = sdf.parse( val.toString() );
            }
        } catch (Exception ex) {
            val = null;
        }

        return val;
    }

    /**
     * 获取单元格值
     * @param row 获取的行
     * @param column 获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, int column) {
        Object val = "";
        try {
            Cell cell = row.getCell(column);
            if (cell != null) {
                if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                    val = this.getCellValueNumeric(cell);
                }

                if (cell.getCellTypeEnum() == CellType.STRING) {
                    val = cell.getStringCellValue();
                }

                if (cell.getCellTypeEnum() == CellType.FORMULA){
                    // val = cell.getCellFormula();
                    val = cell.getNumericCellValue();
                }

                if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
                    val = cell.getBooleanCellValue();
                }

                if (cell.getCellTypeEnum() ==CellType.ERROR) {
                    val = cell.getErrorCellValue();
                }
            }
        } catch (Exception e) {
            return val;
        }
        return val;
    }

    /**
     * 获取单元格数值
     * 当excel 中的数据为数值或日期是需要特殊处理
     * @param cell 单元格
     * @return
     */
    private Object getCellValueNumeric(Cell cell) {
        Object result = "";
        if (cell.getCellTypeEnum() != CellType.NUMERIC) return result;

        if (HSSFDateUtil.isCellDateFormatted(cell)) {
            double d = cell.getNumericCellValue();
            Date date = HSSFDateUtil.getJavaDate(d);
            SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
            result = dformat.format(date);
        } else {
            DecimalFormat df = new DecimalFormat("#.#########");
            df.setGroupingUsed(false);// true时的格式：1,234,567,890
            result = df.format(cell.getNumericCellValue());// 数值类型的数据为double，所以需要转换一下
        }

        return result;
    }

    /**
     * 获取数据行号
     * @return 数据行号
     */
    public int getDataRowNum(){
        return headerNum + 1;
    }

    /**
     * 获取最后一个数据行号
     * @return 最后一个数据行号
     */
    public int getLastDataRowNum(){
        return this.sheet.getLastRowNum() + 1 + headerNum;
    }

    /**
     * 获取行对象
     * @param rownum 行数
     * @return 行对象
     */
    public Row getRow(int rownum){
        return this.sheet.getRow(rownum);
    }
}
