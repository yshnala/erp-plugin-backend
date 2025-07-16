package com.goldtek.erp_plugin.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * WFERP OPENAPI Read方法說明
 * 功能說明：
 * 根據請求服務名(OAPMA.MA001)的MA012定義。以單頭主鍵為查詢條件，返回MA012定義的相關資料表資料。
 * 
 * @author macgyver_chung
 *
 */
public class StdDataReadRequest {
    private StdDataRead std_data; // 標準資料
    String jsonResult = "";

    // Getter 和 Setter
    public StdDataRead getStd_data() {
        return std_data;
    }

    public void setStd_data(StdDataRead std_data) {
        this.std_data = std_data;
    }
    
    public String getJsonResult(StdDataReadRequest request) {
        // 轉換為 JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
			jsonResult = objectMapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}


    public static class StdDataRead {
        private ParameterRead parameter; // 參數

        // Getter 和 Setter
        public ParameterRead getParameter() {
            return parameter;
        }

        public void setParameterRead(ParameterRead parameter) {
            this.parameter = parameter;
        }
    }

    public static class ParameterRead {
        private List<Map<String, String>> datakeys;

        public List<Map<String, String>> getDatakeys() {
            return datakeys;
        }

        public void setDatakeys(List<Map<String, String>> datakeys) {
            this.datakeys = datakeys;
        }
    }
    
//
//    public static class ParameterRead {
//        private List<DataKey> datakeys; // 數據鍵列表
//
//        // Getter 和 Setter
//        public List<DataKey> getDatakeys() {
//            return datakeys;
//        }
//
//        public void setDatakeys(List<DataKey> datakeys) {
//            this.datakeys = datakeys;
//        }
//    }
//
//    public static class DataKey {
//        private String item_no; // 物品編號
//
//        // Getter 和 Setter
//        public String getItem_no() {
//            return item_no;
//        }
//
//        public void setItem_no(String item_no) {
//            this.item_no = item_no;
//        }
//    }
}
