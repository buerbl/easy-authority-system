package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author 布尔bl
 * @create 2021/11/16 23:57
 */
public class ExcelExport {
    public static <T> void export(String sheetName, String[] headers, List<T> dataList, File destFile) throws Exception {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        createSheet(sheetName, headers, dataList, workbook);
        workbook.write(new FileOutputStream(destFile));
    }

    public static <T> void createSheet(String sheetName , String[] headers , List<T> dataList , SXSSFWorkbook wb) {
        SXSSFSheet sheet = wb.createSheet(sheetName);
        // 设置表头和单元格格式
        CellStyle headStyle = setHeaderStyle(wb);
        CellStyle bodyStyle = setBodyStyle(wb);
        // 创建表头和单元格数据
        createHeader(headers, sheet, headStyle);
        createBody(dataList, sheet, bodyStyle);
    }
    private static CellStyle setHeaderStyle(SXSSFWorkbook wb) {
        // 设置表格单元格格式
        CellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());

        // 设置字体格式
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);
        return style;
    }

    private static CellStyle setBodyStyle(SXSSFWorkbook wb) {
        // 设置表格单元格格式
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        // 设置字体格式
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        style.setFont(font);
        return style;
    }


    private static void createHeader(String[] headers, SXSSFSheet sheet, CellStyle headStyle) {
        SXSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(16F);
        for (int i = 0; i < headers.length; i++) {
            // 创建单元格
            SXSSFCell cell = row.createCell(i);
            cell.setCellStyle(headStyle);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            sheet.trackAllColumnsForAutoSizing();
            sheet.autoSizeColumn(i);
        }
    }

    private static <T> void  createBody(List<T> dataList, SXSSFSheet sheet, CellStyle bodyStyle) {
        for (int i = 0; i < dataList.size(); i++) {
            // 从第二行开始，第一行做表头
            SXSSFRow row = sheet.createRow(i+1);
            Map<Object, Object> map = JSONObject.parseObject(JSONObject.toJSONString( dataList.get(i),new SerializerFeature[] { SerializerFeature.WriteMapNullValue }), Map.class);
//            for (int j = 0; j < map.size(); j++) {
//                SXSSFCell cell = row.createCell(j);
//                cell.setCellStyle(bodyStyle);
//                XSSFRichTextString text = new XSSFRichTextString(map.toString());
//                cell.setCellValue(text);
//                sheet.trackAllColumnsForAutoSizing();
//                sheet.autoSizeColumn(i);
//            }

            int j=0;
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                SXSSFCell cell = row.createCell(j++);
                cell.setCellStyle(bodyStyle);
                XSSFRichTextString text = new XSSFRichTextString("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                cell.setCellValue(text);
                sheet.trackAllColumnsForAutoSizing();
                sheet.autoSizeColumn(i);
            }
        }




    }


}
