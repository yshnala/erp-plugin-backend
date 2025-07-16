package com.goldtek.erp_plugin.api.bean;

import java.util.*;


public class StdData {
    private Execution execution;
    private Parameter parameter;

    // Getters and Setters
    public Execution getExecution() {
        return execution;
    }

    public void setExecution(Execution execution) {
        this.execution = execution;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StdData [execution=");
		builder.append(execution.toString());
		builder.append(", parameter=");
		builder.append(parameter.toString());
		builder.append(", getExecution()=");
		builder.append(getExecution());
		builder.append(", getParameter()=");
		builder.append(getParameter());
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	

//    public static void main(String[] args) {
//        // 創建 StdData 對象並設定值
//        StdData stdData = new StdData();
//        
//        // 設定 Execution
//        Execution execution = new Execution();
//        execution.setCode("0");
//        execution.setSql_code("");
//        execution.setDescription("執行成功");
//        stdData.setExecution(execution);
//        
//        // 設定 Parameter 和 Result
//        Parameter parameter = new Parameter();
//        Result result = new Result();
//        
//        // 設定 Success
//        List<Success> successList = new ArrayList<>();
//        Success success = new Success();
//        
//        // 設定 ItemBasicData
//        List<ItemBasicData> itemBasicDataList = new ArrayList<>();
//        ItemBasicData itemBasicData = new ItemBasicData();
//        itemBasicData.setCOMPANY("Goldtek");
//        itemBasicData.setItem_no("2T71DDL00-JUB-G");
//        itemBasicDataList.add(itemBasicData);
//        
//        success.setItem_basic_data(itemBasicDataList);
//        successList.add(success);
//        
//        result.setSuccess(successList);
//        result.setError(new ArrayList<>()); // 無錯誤
//        parameter.setResult(result);
//        stdData.setParameter(parameter);
//
//        // 將 StdData 轉換為 JSON
//        Gson gson = new Gson();
//        String json = gson.toJson(stdData);
//        System.out.println("轉換為 JSON: " + json);
//        
//        // 將 JSON 轉換回 StdData 對象
//        StdData parsedStdData = gson.fromJson(json, StdData.class);
//
//        System.out.println("轉成 JAVABEAN: " + parsedStdData.toString());
//        // 印出結果
//        System.out.println("Execution Code: " + parsedStdData.getExecution().getCode());
//        System.out.println("Execution Description: " + parsedStdData.getExecution().getDescription());
//        if (!parsedStdData.getParameter().getResult().getSuccess().isEmpty()) {
//            ItemBasicData parsedItem = parsedStdData.getParameter().getResult().getSuccess().get(0).getItem_basic_data().get(0);
//            System.out.println("Company: " + parsedItem.getCOMPANY());
//            System.out.println("Item No: " + parsedItem.getItem_no());
//        }
//    }
}
