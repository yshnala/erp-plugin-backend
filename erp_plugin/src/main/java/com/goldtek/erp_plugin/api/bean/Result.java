package com.goldtek.erp_plugin.api.bean;

import java.util.List;
import java.util.Map;

public class Result {
    private List<Success> success;
    private List<Error> error;
    private int cnt;
    private List<Map<String, String>> rows; // Using List of Map for rows


    // Getters and Setters
    public List<Success> getSuccess() {
        return success;
    }

    public void setSuccess(List<Success> success) {
        this.success = success;
    }

    public List<Error> getError() {
        return error;
    }

    public void setError(List<Error> error) {
        this.error = error;
    }
    
    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<Map<String, String>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, String>> rows) {
        this.rows = rows;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Result [success=");
		builder.append(success);
		builder.append(", error=");
		builder.append(error);
		builder.append(", cnt=");
		builder.append(cnt);
		builder.append(", rows=");
		builder.append(rows);
		builder.append("]");
		return builder.toString();
	}
}
