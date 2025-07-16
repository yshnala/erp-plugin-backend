package com.goldtek.erp_plugin.api;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * WFERP OPENAPI Query方法說明
 * 功能說明：
 * 根據請求服務名(OAPMA.MA001)的MA012定義，加上傳入的查詢條件，返回MA012定義的相關資料表資料。
 * 
 * @author macgyver_chung
 *
 */
public class StdDataApproveRequest {
    private StdDataQuery std_data;
    String jsonResult = "";			//把BEAN內容轉為 JSON

    // Getter 和 Setter
    public StdDataQuery getStd_data() {
        return std_data;
    }

    public void setStd_data(StdDataQuery std_data) {
        this.std_data = std_data;
    }

    public String getJsonResult(StdDataApproveRequest request) {
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

	public static class StdDataQuery {
        private ParameterQuery parameter;

        // Getter 和 Setter
        public ParameterQuery getParameter() {
            return parameter;
        }

        public void setParameter(ParameterQuery parameter) {
            this.parameter = parameter;
        }
    }


	public static class ParameterQuery {

		private String enterprise_no;

		public String getEnterprise_no() {
			return enterprise_no;
		}

		public void setEnterprise_no(String enterprise_no) {
			this.enterprise_no = enterprise_no;
		}

		public List<Map<String, String>> getDatakeys() {
			return datakeys;
		}

		public void setDatakeys(List<Map<String, String>> datakeys) {
			this.datakeys = datakeys;
		}

		private List<Map<String, String>> datakeys;

	}
	
    public static class AllConditions {
   
        
    }
   
}