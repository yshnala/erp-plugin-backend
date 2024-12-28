package com.goldtek.erp_plugin.service;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelUploadService {
	 private static final Logger logger = LoggerFactory.getLogger(ExcelUploadService.class);

	    public void processExcelFile(MultipartFile file) throws IOException {
	        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
	            // 假設只處理第一個工作表
	            Sheet sheet = workbook.getSheetAt(0);

	            for (Row row : sheet) {
	                StringBuilder rowContent = new StringBuilder();
	                for (Cell cell : row) {
	                    switch (cell.getCellType()) {
	                        case STRING:
	                            rowContent.append(cell.getStringCellValue()).append(" ");
	                            break;
	                        case NUMERIC:
	                            rowContent.append(cell.getNumericCellValue()).append(" ");
	                            break;
	                        case BOOLEAN:
	                            rowContent.append(cell.getBooleanCellValue()).append(" ");
	                            break;
	                        default:
	                            rowContent.append(" ");
	                    }
	                }
	                logger.info("Row {}: {}", row.getRowNum(), rowContent.toString().trim());
	            }
	        }
	    }

}
