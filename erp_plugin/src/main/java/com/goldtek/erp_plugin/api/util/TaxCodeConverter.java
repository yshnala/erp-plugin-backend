package com.goldtek.erp_plugin.api.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 深圳稅別碼轉深圳鼎新稅別碼作業
 * @author alan_huang
 *
 */
@Component
public class TaxCodeConverter {
	private static final Map<String, String> TAX_CODE_MAP_SZSAP_TO_SZERP = new HashMap<>();
	private static final Map<String, String> TAX_CODE_MAP_SZ_TO_SZERP = new HashMap<>();
	private static final Map<String, String> TAX_CODE_MAP_TPERP_TO_SAP = new HashMap<>();
	private static final Map<String, String> CODE_MAP= new HashMap<>();
	private static final Map<String, String> CODE_MAP_TO_ERP= new HashMap<>();

	
    // 靜態初始化稅別碼映射
    static {
        // S 系列稅別碼
//        TAX_CODE_MAP.put("S120", "A00");
//        TAX_CODE_MAP.put("S125", "A05");
//        TAX_CODE_MAP.put("S126", "A06");
//        TAX_CODE_MAP.put("S130", "B00");
//        TAX_CODE_MAP.put("S131", "B13");
//        TAX_CODE_MAP.put("S135", "B05");
//        TAX_CODE_MAP.put("S136", "B06");
//        TAX_CODE_MAP.put("S139", "B09");
//        TAX_CODE_MAP.put("S140", "B17");
//        TAX_CODE_MAP.put("S150", "B16");
//        TAX_CODE_MAP.put("S1XX", "MXX");
//        TAX_CODE_MAP.put("S225", "A05");
//        TAX_CODE_MAP.put("S235", "B05");
//        TAX_CODE_MAP.put("S237", "B17");
//        TAX_CODE_MAP.put("S320", "A00");
//        TAX_CODE_MAP.put("S325", "A05");
//        TAX_CODE_MAP.put("S335", "B05");
//
//        // T 系列稅別碼
//        TAX_CODE_MAP.put("T130", "D13");
//        TAX_CODE_MAP.put("T131", "D05");
//        TAX_CODE_MAP.put("T132", "D05");
//        TAX_CODE_MAP.put("T140", "D17");
//        TAX_CODE_MAP.put("T150", "D16");
//        TAX_CODE_MAP.put("T201", "C01");
//        TAX_CODE_MAP.put("T202", "C02");
//        TAX_CODE_MAP.put("T203", "C03");
//        TAX_CODE_MAP.put("T205", "C06");
//        TAX_CODE_MAP.put("T213", "C13");
//        TAX_CODE_MAP.put("T215", "C16");
//        TAX_CODE_MAP.put("T217", "C17");
//        TAX_CODE_MAP.put("T235", "D05");
//        TAX_CODE_MAP.put("T302", "D02");
//        TAX_CODE_MAP.put("T303", "D03");
//        TAX_CODE_MAP.put("T304", "D04");
//        TAX_CODE_MAP.put("T305", "D05");
//        TAX_CODE_MAP.put("T306", "D06");
//        TAX_CODE_MAP.put("T309", "D09");
//        TAX_CODE_MAP.put("T310", "D10");
//        TAX_CODE_MAP.put("T311", "D11");
//        TAX_CODE_MAP.put("T313", "D13");
//        TAX_CODE_MAP.put("T317", "D16");
//        TAX_CODE_MAP.put("T335", "D05");
//
//        // 其他稅別碼
//        TAX_CODE_MAP.put("T902", "MI2");
    	TAX_CODE_MAP_SZSAP_TO_SZERP.put("J0", "MI1");
    	TAX_CODE_MAP_SZSAP_TO_SZERP.put("J1", "D13");
    	TAX_CODE_MAP_SZSAP_TO_SZERP.put("J2", "D10");
    	TAX_CODE_MAP_SZSAP_TO_SZERP.put("X0", "A00");
    	TAX_CODE_MAP_SZSAP_TO_SZERP.put("X1", "B13");
    	
        // T 系列稅別碼
    	TAX_CODE_MAP_SZ_TO_SZERP.put("T135", "P21");
    	TAX_CODE_MAP_SZ_TO_SZERP.put("T131", "P05");
        TAX_CODE_MAP_SZ_TO_SZERP.put("T132", "P02");
        TAX_CODE_MAP_SZ_TO_SZERP.put("T901", "P15");
        TAX_CODE_MAP_SZ_TO_SZERP.put("T902", "P15");
        
        
        TAX_CODE_MAP_TPERP_TO_SAP.put("P21", "T135");
        TAX_CODE_MAP_TPERP_TO_SAP.put("P05", "T131");
        TAX_CODE_MAP_TPERP_TO_SAP.put("P02", "T132");
//        TAX_CODE_MAP_TPERP_TO_SAP.put("P15", "T901");
        TAX_CODE_MAP_TPERP_TO_SAP.put("P15", "T902");
        
        
        CODE_MAP_TO_ERP.put("S120", "A00");
        CODE_MAP_TO_ERP.put("S125", "A05");
        CODE_MAP_TO_ERP.put("S126", "A06");
        CODE_MAP_TO_ERP.put("S130", "B00");
        CODE_MAP_TO_ERP.put("S131", "B13");
        CODE_MAP_TO_ERP.put("S135", "B05");
        CODE_MAP_TO_ERP.put("S136", "B06");
        CODE_MAP_TO_ERP.put("S139", "B09");
        CODE_MAP_TO_ERP.put("S140", "B17");
        CODE_MAP_TO_ERP.put("S150", "B16");
        CODE_MAP_TO_ERP.put("S1XX", "MXX");
        CODE_MAP_TO_ERP.put("S220", "A00");
        CODE_MAP_TO_ERP.put("S225", "A05");
        CODE_MAP_TO_ERP.put("S235", "B05");
        CODE_MAP_TO_ERP.put("S317", "B17");
        CODE_MAP_TO_ERP.put("S320", "A00");
        CODE_MAP_TO_ERP.put("S325", "A05");
        CODE_MAP_TO_ERP.put("S335", "B05");
        CODE_MAP_TO_ERP.put("T108", "M05");
        CODE_MAP_TO_ERP.put("T130", "D13");
        CODE_MAP_TO_ERP.put("T131", "D05");
        CODE_MAP_TO_ERP.put("T132", "D05");
        CODE_MAP_TO_ERP.put("T135", "D05");
        CODE_MAP_TO_ERP.put("T140", "D17");
        CODE_MAP_TO_ERP.put("T150", "D16");
        CODE_MAP_TO_ERP.put("T201", "C01");
        CODE_MAP_TO_ERP.put("T202", "C02");
        CODE_MAP_TO_ERP.put("T203", "C03");
        CODE_MAP_TO_ERP.put("T206", "C06");
        CODE_MAP_TO_ERP.put("T211", "C11");
        CODE_MAP_TO_ERP.put("T213", "C13");
        CODE_MAP_TO_ERP.put("T215", "C16");
        CODE_MAP_TO_ERP.put("T217", "C17");
        CODE_MAP_TO_ERP.put("T235", "D05");
        CODE_MAP_TO_ERP.put("T301", "D01");
        CODE_MAP_TO_ERP.put("T302", "D02");
        CODE_MAP_TO_ERP.put("T303", "D03");
        CODE_MAP_TO_ERP.put("T304", "D04");
        CODE_MAP_TO_ERP.put("T305", "D05");
        CODE_MAP_TO_ERP.put("T306", "D06");
        CODE_MAP_TO_ERP.put("T309", "D09");
        CODE_MAP_TO_ERP.put("T310", "D10");
        CODE_MAP_TO_ERP.put("T311", "D11");
        CODE_MAP_TO_ERP.put("T313", "D13");
        CODE_MAP_TO_ERP.put("T315", "D16");
        CODE_MAP_TO_ERP.put("T317", "D17");
        CODE_MAP_TO_ERP.put("T335", "D05");
        CODE_MAP_TO_ERP.put("T901", "M00");
        CODE_MAP_TO_ERP.put("T902", "M00");
        
        CODE_MAP.put("J0", "T901");
        CODE_MAP.put("J1", "T313");
        CODE_MAP.put("J2", "T310");
        CODE_MAP.put("X0", "S120");
        CODE_MAP.put("X1", "S131");
        CODE_MAP.put("J7", "T305");
        CODE_MAP.put("J8", "T306"); 
        CODE_MAP.put("J9", "T309");
        CODE_MAP.put("J4", "T301");
        CODE_MAP.put("J5", "T302");
        CODE_MAP.put("J6", "T303");
        CODE_MAP.put("JA", "T311");
        CODE_MAP.put("J3", "T304");
    }

    /**
     * 稅別碼轉換功能
     * @param inputTaxCode 輸入的稅別碼（例如 T902）
     * @return 轉換後的稅別碼（例如 MI2），如果找不到則返回 null
     */
    public String convertTaxCode(String inputTaxCode) {
        if (inputTaxCode == null || inputTaxCode.trim().isEmpty()) {
            return null;
        }
        return TAX_CODE_MAP_SZ_TO_SZERP.getOrDefault(inputTaxCode.trim().toUpperCase(), null);
    }
    
    public String convertTaxCodeTaipeiToSap(String inputTaxCode) {
        if (inputTaxCode == null || inputTaxCode.trim().isEmpty()) {
            return null;
        }
        System.out.println("開始轉換"+TAX_CODE_MAP_TPERP_TO_SAP.get(inputTaxCode));
        return TAX_CODE_MAP_TPERP_TO_SAP.getOrDefault(inputTaxCode.trim().toUpperCase(), null);
    }
    
    
    public String convertTaxCodeSzSapToSzErp(String inputTaxCode) {
        if (inputTaxCode == null || inputTaxCode.trim().isEmpty()) {
            return null;
        }
        System.out.println("開始轉換"+TAX_CODE_MAP_SZSAP_TO_SZERP.get(inputTaxCode));
        return TAX_CODE_MAP_SZSAP_TO_SZERP.getOrDefault(inputTaxCode.trim().toUpperCase(), null);
    }
    
    
    public String convertToOldTaxCode(String inputTaxCode) {
        if (inputTaxCode == null || inputTaxCode.trim().isEmpty()) {
            return null;
        }
        System.out.println("開始轉換"+CODE_MAP.get(inputTaxCode));
        return CODE_MAP.getOrDefault(inputTaxCode.trim().toUpperCase(), null);
    }
    
    
    public String convertToSZErpTaxCode(String inputTaxCode) {
        if (inputTaxCode == null || inputTaxCode.trim().isEmpty()) {
            return null;
        }
        System.out.println("開始轉換"+CODE_MAP_TO_ERP.get(inputTaxCode));
        return CODE_MAP_TO_ERP.getOrDefault(inputTaxCode.trim().toUpperCase(), null);
    }
    
}
