package com.goldtek.erp_plugin.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.goldtek.erp_plugin.api.entity.CustomerItemData;

@Service
public class ExcelUploadService {
	private static final Logger logger = LoggerFactory.getLogger(ExcelUploadService.class);

	public List<CustomerItemData> processExcelFile(MultipartFile file) throws IOException {
		List<CustomerItemData> list = new ArrayList();
		
	    try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
	        Sheet sheet = workbook.getSheetAt(0);

	        for (Row row : sheet) {
	            if (row.getRowNum() == 0) continue; // 跳過標題列（第一列）

	            CustomerItemData customerItemData = new CustomerItemData();

	            for (Cell cell : row) {
	                int colIndex = cell.getColumnIndex();

	                switch (colIndex) {
	                    case 0: // 第一欄：customer_no
	                        if (cell.getCellType() == CellType.STRING) {
	                            customerItemData.setCustomer_no(cell.getStringCellValue());
	                        } else if (cell.getCellType() == CellType.NUMERIC) {
	                            customerItemData.setCustomer_no(String.valueOf((int)cell.getNumericCellValue()));
	                        }
	                        break;
	                    case 1: // 第二欄：item_no
	                        if (cell.getCellType() == CellType.STRING) {
	                            customerItemData.setItem_no(cell.getStringCellValue());
	                        } else if (cell.getCellType() == CellType.NUMERIC) {
	                            customerItemData.setItem_no(String.valueOf((int)cell.getNumericCellValue()));
	                        }
	                        break;
	                    case 2: // 第三欄：customer_item
	                        if (cell.getCellType() == CellType.STRING) {
	                            customerItemData.setCustomer_item(cell.getStringCellValue());
	                        } else if (cell.getCellType() == CellType.NUMERIC) {
	                            customerItemData.setCustomer_item(String.valueOf((int)cell.getNumericCellValue()));
	                        }
	                        break;
	                    default:
	                        // 忽略多餘欄位
	                        break;
	                }
	                
	            }
	            list.add(customerItemData);
	            logger.info("CustomerItemData: {}, {}, {}", 
	                customerItemData.getCustomer_no(), 
	                customerItemData.getItem_no(), 
	                customerItemData.getCustomer_item()
	            );
	        }
	    }
		return list;
	}


}
