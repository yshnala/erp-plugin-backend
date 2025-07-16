package com.goldtek.erp_plugin.api.bean;

public class Parameter {
    private int totalResult;
    private int totalResults;
    private boolean hasNext;
    private Result result;
    
    // Getters and Setters
    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
    
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Parameter [totalResult=");
		builder.append(totalResult);
		builder.append(", totalResults=");
		builder.append(totalResults);
		builder.append(", hasNext=");
		builder.append(hasNext);
		builder.append(", result=");
		builder.append(result.toString());
		builder.append("]");
		return builder.toString();
	}
}
