package com.goldtek.erp_plugin.api.bean;

public class Execution {
    private String code;
    private String sql_code;
    private String description;

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSql_code() {
        return sql_code;
    }

    public void setSql_code(String sql_code) {
        this.sql_code = sql_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Execution [code=");
		builder.append(code);
		builder.append(", sql_code=");
		builder.append(sql_code);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
}
