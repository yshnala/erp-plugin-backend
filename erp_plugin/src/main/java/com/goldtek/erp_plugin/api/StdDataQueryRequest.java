package com.goldtek.erp_plugin.api;

import java.util.List;

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
public class StdDataQueryRequest {
    private StdDataQuery std_data;
    String jsonResult = "";			//把BEAN內容轉為 JSON

    // Getter 和 Setter
    public StdDataQuery getStd_data() {
        return std_data;
    }

    public void setStd_data(StdDataQuery std_data) {
        this.std_data = std_data;
    }

    public String getJsonResult(StdDataQueryRequest request) {
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

    public static class ALlParameterQuery {
        private int page_size; // 每頁大小
        private int page_no; // 頁碼
        private String conditions ="{}"; // 查詢條件
        private List<Order> orders; // 排序

        // Getters 和 Setters
        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public int getPage_no() {
            return page_no;
        }

        public void setPage_no(int page_no) {
            this.page_no = page_no;
        }

  
        public List<Order> getOrders() {
            return orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }
    }
    public static class ParameterQuery {
        private int page_size; // 每頁大小
        private int page_no; // 頁碼
        private AllConditions conditions; // 查詢條件
        private List<Order> orders; // 排序

        // Getters 和 Setters
        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public int getPage_no() {
            return page_no;
        }

        public void setPage_no(int page_no) {
            this.page_no = page_no;
        }

        public AllConditions getConditions() {
            return conditions;
        }

        public void setConditions(AllConditions conditions) {
            this.conditions = conditions;
        }

        public List<Order> getOrders() {
            return orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }
    }
    public static class AllConditions {
   
        
    }
    public static class Conditions extends AllConditions{
        private String operator; // 邏輯運算符
        private List<Field> fields; // 欄位列表

        // Getters 和 Setters
        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public List<Field> getFields() {
            return fields;
        }

        public void setFields(List<Field> fields) {
            this.fields = fields;
        }

		@Override
		public String toString() {
			return "Conditions [operator=" + operator + ", fields=" + fields + "]";
		}
        
        
    }

    public static class Field {
        private String field_name; // 欄位名稱
        private String operator; // 運算符
        private String value; // 值

        // Getters 和 Setters
        public String getField_name() {
            return field_name;
        }

        public void setField_name(String field_name) {
            this.field_name = field_name;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class Order {
        private String field_name; // 欄位名稱
        private String order_type; // 排序類型

        // Getters 和 Setters
        public String getField_name() {
            return field_name;
        }

        public void setField_name(String field_name) {
            this.field_name = field_name;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }
    }
}