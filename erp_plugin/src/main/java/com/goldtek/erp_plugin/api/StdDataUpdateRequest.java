package com.goldtek.erp_plugin.api;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StdDataUpdateRequest {
    private StdDataUpdate std_data; // 標準資料
    String jsonResult;

    // Getter 和 Setter
    public StdDataUpdate getStd_data() {
        return std_data;
    }

    public void setStd_data(StdDataUpdate std_data) {
        this.std_data = std_data;
    }

    public String getJsonResult(StdDataUpdateRequest request) {
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

	
    public static class StdDataUpdate {
        private ParameterUpdate parameter; // 參數

        // Getter 和 Setter
        public ParameterUpdate getParameter() {
            return parameter;
        }

        public void setParameter(ParameterUpdate parameter) {
            this.parameter = parameter;
        }
    }

    public static class ParameterUpdate {
        private List<ItemBasicDataUpdate> item_basic_data; // 物品基本資料列表

        // Getter 和 Setter
        public List<ItemBasicDataUpdate> getItem_basic_data() {
            return item_basic_data;
        }

        public void setItem_basic_data(List<ItemBasicDataUpdate> item_basic_data) {
            this.item_basic_data = item_basic_data;
        }
    }

    public static class ItemBasicDataUpdate {
        private String item_no; // 物品編號
        private String item_desc; // 物品描述
        private String item_name; // 物品名稱

        // Getter 和 Setter
        public String getItem_no() {
            return item_no;
        }

        public void setItem_no(String item_no) {
            this.item_no = item_no;
        }

        public String getItem_desc() {
            return item_desc;
        }

        public void setItem_desc(String item_desc) {
            this.item_desc = item_desc;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }
    }
}
