package com.goldtek.erp_plugin.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelDowloadService {
	
	
    public byte[] generateExcel() throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            // 創建工作表
            Sheet sheet = workbook.createSheet("Sample Data");

            // 創建標題行
            Row headerRow = sheet.createRow(0);
            String[] headers = {"CustomerNo", "ItemNo", "CustomerItem"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            // 插入示例數據
            Object[][] data = {
                    {1, "John Doe", "john.doe@example.com"},
                    {2, "Jane Smith", "jane.smith@example.com"},
                    {3, "Michael Brown", "michael.brown@example.com"}
            };

            int rowNum = 1;
            for (Object[] rowData : data) {
                Row row = sheet.createRow(rowNum++);
                for (int colNum = 0; colNum < rowData.length; colNum++) {
                    row.createCell(colNum).setCellValue(rowData[colNum].toString());
                }
            }

            // 自動調整列寬
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // 將 Excel 寫入輸出流
            workbook.write(out);
            return out.toByteArray();
        }
    }

    private CellStyle createHeaderCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }

}
