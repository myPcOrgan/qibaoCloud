package com.qibao.backend.utils;


import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExportExcel {

    private Workbook wb = null;
    private Sheet sheet = null;
    private static final Logger LOG = LoggerFactory.getLogger(ExportExcel.class);

    public ExportExcel(Workbook wb, Sheet sheet) {
        this.wb = wb;
        this.sheet = sheet;
    }

    public Sheet getSheet() {
        return this.sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Workbook getWb() {
        return this.wb;
    }

    public void setWb(Workbook wb) {
        this.wb = wb;
    }

    public void createNormalHead(int rowIndex, String headString, int colSum) {
        Row row = this.sheet.createRow(rowIndex);
        Cell cell = row.createCell(0);
        row.setHeight((short)400);
        cell.setCellType(1);
        cell.setCellValue(new HSSFRichTextString(headString));
        this.sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (short)colSum));
        CellStyle cellStyle = this.wb.createCellStyle();
        cellStyle.setAlignment((short)2);
        cellStyle.setVerticalAlignment((short)1);
        cellStyle.setWrapText(true);
        Font font = this.wb.createFont();
        font.setBoldweight((short)700);
        font.setFontName("宋体");
        font.setFontHeight((short)300);
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

    public void createColumHeader(int rowIndex, String[] columHeader) {
        Row row = this.sheet.createRow(rowIndex);
        row.setHeight((short)600);
        CellStyle cellStyle = this.wb.createCellStyle();
        cellStyle.setAlignment((short)2);
        cellStyle.setVerticalAlignment((short)1);
        cellStyle.setWrapText(true);
        Font font = this.wb.createFont();
        font.setBoldweight((short)700);
        font.setFontName("宋体");
        font.setFontHeight((short)250);
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor((short)22);
        cellStyle.setFillPattern((short)1);

        for(int i = 0; i < columHeader.length; ++i) {
            Cell cell = row.createCell(i);
            cell.setCellType(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new HSSFRichTextString(columHeader[i]));
        }

    }

    public void createCell(Workbook wb, Row row, int col, short align, String val) {
        Cell cell = row.createCell(col);
        cell.setCellType(1);
        cell.setCellValue(new HSSFRichTextString(val));
        CellStyle cellstyle = wb.createCellStyle();
        cellstyle.setAlignment(align);
        cell.setCellStyle(cellstyle);
    }

    public void createLastSumRow(int colSum, String[] cellValue) {
        CellStyle cellStyle = this.wb.createCellStyle();
        cellStyle.setAlignment((short)2);
        cellStyle.setVerticalAlignment((short)1);
        cellStyle.setWrapText(true);
        Font font = this.wb.createFont();
        font.setBoldweight((short)700);
        font.setFontName("宋体");
        font.setFontHeight((short)250);
        cellStyle.setFont(font);
        Row lastRow = this.sheet.createRow((short)(this.sheet.getLastRowNum() + 1));
        Cell sumCell = lastRow.createCell(0);
        sumCell.setCellValue(new HSSFRichTextString("合计"));
        sumCell.setCellStyle(cellStyle);
        this.sheet.addMergedRegion(new CellRangeAddress(this.sheet.getLastRowNum(), 0, this.sheet.getLastRowNum(), (short)colSum));

        for(int i = 2; i < cellValue.length + 2; ++i) {
            sumCell = lastRow.createCell(i);
            sumCell.setCellStyle(cellStyle);
            sumCell.setCellValue(new HSSFRichTextString(cellValue[i - 2]));
        }

    }

    public void addMergedRegion(HSSFSheet sheet, int cellLine, int startRow, int endRow, Workbook workBook) {
        CellStyle style = workBook.createCellStyle();
        style.setVerticalAlignment((short)1);
        style.setAlignment((short)2);
        String s_will = sheet.getRow(startRow).getCell(cellLine).getStringCellValue();
        int count = 0;
        boolean flag = false;

        for(int i = 1; i <= endRow; ++i) {
            String s_current = sheet.getRow(i).getCell(0).getStringCellValue();
            Cell cell;
            if(s_will.equals(s_current)) {
                s_will = s_current;
                if(flag) {
                    sheet.addMergedRegion(new CellRangeAddress(startRow - count, startRow, cellLine, cellLine));
                    HSSFRow cellValueTemp = sheet.getRow(startRow - count);
                    String row = sheet.getRow(startRow - count).getCell(0).getStringCellValue();
                    cell = cellValueTemp.createCell(0);
                    cell.setCellValue(row);
                    cell.setCellStyle(style);
                    count = 0;
                    flag = false;
                }

                startRow = i;
                ++count;
            } else {
                flag = true;
                s_will = s_current;
            }

            if(i == endRow && count > 0) {
                sheet.addMergedRegion(new CellRangeAddress(endRow - count, endRow, cellLine, cellLine));
                String var15 = sheet.getRow(startRow - count).getCell(0).getStringCellValue();
                HSSFRow var16 = sheet.getRow(startRow - count);
                cell = var16.createCell(0);
                cell.setCellValue(var15);
                cell.setCellStyle(style);
            }
        }

    }

    public void outputExcel(String fileName) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(new File(fileName));
            this.wb.write(fos);
        } catch (FileNotFoundException var4) {
            LOG.error("SYSTEM", "系统异常", var4);
        } catch (IOException var5) {
            LOG.error("SYSTEM", "系统异常", var5);
        }finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
