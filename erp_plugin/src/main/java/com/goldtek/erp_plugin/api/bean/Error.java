package com.goldtek.erp_plugin.api.bean;

import java.util.Map;

public class Error {
    private String message;
    private Object data; // 錯誤數據
    
    //1131009，因read回傳的data是List，query回傳的是String，若用gSon解析會Exception，所以不做 data參數，若有需要再自行取
//    private String data;

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public String getData() {
//        return data;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Error [message=");
		builder.append(message);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
}
