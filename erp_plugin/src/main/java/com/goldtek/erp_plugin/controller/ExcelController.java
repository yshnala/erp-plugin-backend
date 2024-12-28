package com.goldtek.erp_plugin.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.goldtek.erp_plugin.service.ExcelUploadService;
import com.goldtek.erp_plugin.service.ExcelDowloadService;

/**
 * 
 * 項目名稱:erp_plugin 類名稱: ImportExcelController:匯入Excel控制器 創建人: Alan 創建時間:
 * 2024年12月21日 下午6:42:12
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

	Logger logger = LoggerFactory.getLogger(ExcelController.class);

	@Autowired
	ExcelDowloadService excelDowloadService;
	
	@Autowired
	ExcelUploadService excelUploadService;

	/**
	 * 
	 * 方法名: exportCustomerPartNoExcel
	 * <p>
	 * 說明: 導出客戶料號對照Sample
	 * <p>
	 * 創建人: Alan
	 * <p>
	 * 創建時間: 2024年12月21日 下午7:12:18
	 * <p>
	 */
	@GetMapping("export/sample/customerPartNoMapping")
	public ResponseEntity<Object> exportCustomerPartNoExcel() {
		try {
			byte[] excelData = excelDowloadService.generateExcel();

			// 設置 HTTP 回應標頭
			HttpHeaders headersResponse = new HttpHeaders();
			headersResponse.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sample.xlsx");
			headersResponse.add(HttpHeaders.CONTENT_TYPE,
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

			return new ResponseEntity<>(excelData, headersResponse, HttpStatus.OK);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * 
	 * 方法名: importCustomerPartNoExcel
	 * <p>
	 * 說明: 上傳客戶料號對照 創建人: Alan
	 * <p>
	 * 創建時間: 2024年12月21日 下午8:00:37
	 * <p>
	 */
	@PostMapping("upload/sample/customerPartNoMapping")
	public ResponseEntity<String> uploadCustomerPartNoExcel(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
		}

		try {
			logger.info("解析EXCEL...");

			//解析Excel
			excelUploadService.processExcelFile(file);
			//上傳FTP
			//儲存ERP DB
//			throw new RuntimeException("TEST");
			return ResponseEntity.ok("File uploaded and logged successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file:"+e);
		}
	}

}
