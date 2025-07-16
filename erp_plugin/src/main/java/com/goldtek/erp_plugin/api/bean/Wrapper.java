package com.goldtek.erp_plugin.api.bean;

public class Wrapper {
    private StdData std_data;

    public StdData getStdData() {
        return std_data;
    }

    public void setStdData(StdData std_data) {
        this.std_data = std_data;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Wrapper [std_data=");
		builder.append(std_data);
		builder.append("]");
		return builder.toString();
	}

}
