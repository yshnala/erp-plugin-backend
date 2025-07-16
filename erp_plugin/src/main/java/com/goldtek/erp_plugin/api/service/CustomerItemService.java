package com.goldtek.erp_plugin.api.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldtek.erp_plugin.api.StdDataQueryRequest;
import com.goldtek.erp_plugin.api.StdDataQueryRequest.AllConditions;
import com.goldtek.erp_plugin.api.StdDataQueryRequest.Conditions;
import com.goldtek.erp_plugin.api.StdDataQueryRequest.Field;
import com.goldtek.erp_plugin.api.StdDataQueryRequest.Order;
import com.goldtek.erp_plugin.api.StdDataQueryRequest.ParameterQuery;
import com.goldtek.erp_plugin.api.StdDataQueryRequest.StdDataQuery;
import com.goldtek.erp_plugin.api.bean.ErpApiBean;
import com.goldtek.erp_plugin.api.bean.StdData;
import com.goldtek.erp_plugin.api.bean.Wrapper;
import com.goldtek.erp_plugin.api.entity.CustomerItemData;
import com.goldtek.erp_plugin.api.util.Base64SHA256;
import com.goldtek.erp_plugin.api.util.ErpHttpUrl;
import com.nimbusds.jose.shaded.gson.Gson;

/**
 * 
 * @title: 客戶料號導入作業
 * @author: alan_huang
 * @since: 2024年12月30日 下午4:07:18
 */
@Service
public class CustomerItemService {
	
	@Value("${area}")
	private String area;									
	@Value("${url}")
	private String url;
	@Value("${companyId}")
    String companyId;										
	@Value("${secretKey}")
	String secretKey;							
	@Value("${appKey}")
	String appKey;
	@Value("${sys}")
	String sys; 									
	@Value("${digiReqId}")
	String digiReqId;								
	@Value("${wfAccount}")
	String wfAccount;							
	@Value("${customerItem.create}")
	private String createApiName;
	
	static final Logger logger = LoggerFactory.getLogger(CustomerItemService.class);

	public Map<String,List<String>> create(List<CustomerItemData> receivedHeadList) {
		String json = getCreateRequest(receivedHeadList);
		logger.info(json);
		Map<String,List<String>> successList = null;
//				sendCreateApi(json);
		return successList;
	}
		
	

	public String getCreateRequest(List<CustomerItemData> receivedHeadList) {
		String json = "";
		try {
			// 創建參數結構
			Map<String, Object> parameter = new HashMap<>();
			parameter.put("customer_item_data", receivedHeadList);

			// 創建 std_data 結構
			Map<String, Object> stdData = new HashMap<>();
			stdData.put("parameter", parameter);

			// 創建最終的 JSON 結構
			Map<String, Object> finalJson = new HashMap<>();
			finalJson.put("std_data", stdData);

			// 使用 Jackson 轉換為 JSON 字串
			ObjectMapper objectMapper = new ObjectMapper();
			String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(finalJson);

			json = jsonString;
			// 列印查詢的 JSON
			logger.info("JSON = " + json);

		} catch (Exception e) {
			logger.info("Customer Item...getCreateRequest...Exception : " + e);
		}

		return json;
	}
	
	
//	public 	List<PurchaseOrderDTO> query(String vendorCode){
//		logger.info("call PurchaseOrderApiService query API...");
//		StdDataQueryRequest requestQuery = getQueryRequest(vendorCode);
//		String bodyQuery = requestQuery.getJsonResult(requestQuery);
//		
//		List<Map<String, String>> result =sendQueryApi(bodyQuery);
//		List<PurchaseOrderDTO> purchaseOrderDTOList = new ArrayList();
//		
//		for(Map<String, String> map :result) {
//			PurchaseOrderDTO  dto = mapToPurchaseOrder(map);
//			purchaseOrderDTOList.add(dto);
//		}
//		return purchaseOrderDTOList;
//	}
	
	
	
	

	
	public StdDataQueryRequest getQueryRequest(String vendorCode) {
        StdDataQueryRequest request = new StdDataQueryRequest();
		
		try {
	        //創建查詢條件，多個條件就用多個field放到conditions
			
	        List<Field> fields = new ArrayList<>();
	        Field field1 = new Field();
	        if(vendorCode != null) {
	        	field1.setField_name("vendor");
	  	        field1.setOperator("=");
	  	        field1.setValue(vendorCode);
	  	        fields.add(field1);
	        }
	       
	        Field field2 = new Field();
	        field2.setField_name("approval_status_code");
	        field2.setOperator("NOT IN");
  	        field2.setValue("('5','N')");
  	        fields.add(field2);

	        //創建條件
	        Conditions conditions = new Conditions();
	        conditions.setOperator("AND");
	        conditions.setFields(fields);
	        AllConditions  allconditions = new AllConditions();
	        //創建排序列表
	        List<Order> orders = new ArrayList<>();
	        Order order = new Order();
	        order.setField_name("purchase_date");
	        order.setOrder_type("desc");
	        orders.add(order);

	        //創建參數
	        ParameterQuery parameter = new ParameterQuery();
	        parameter.setPage_size(5);
	        parameter.setPage_no(1);
	        parameter.setConditions(conditions);
	        parameter.setOrders(orders);

	        //創建標準資料
	        StdDataQuery stdData = new StdDataQuery();
	        stdData.setParameter(parameter);

	        //創建請求
	        request.setStd_data(stdData);

//	        //轉換為 JSON
//	        ObjectMapper objectMapper = new ObjectMapper();
//	        String jsonResult = objectMapper.writeValueAsString(request);

	        //列印查詢的 JSON
	        logger.info(request.getJsonResult(request));
			
		}catch (Exception e) {
			logger.info( (e).toString());
		}
		
		return request;
	}
	
	
	
	
//	/**
//	 * 查詢品號列表資料，wf.oapi.item.data.query.get
//	 *                                                                          
//	 */
//	public List<Map<String, String>> sendQueryApi(String body) {
//		logger.info("---PurchaseOrderApiService.sendQueryApi...");
//		List<Map<String, String>>  results = new ArrayList();
//		try {
//			//呼叫的 API名稱，查詢品號列表資料，wf.oapi.item.data.query.get
////			String apiName = "wf.oapi.tax.type.data.query.get";
//			
//			//設定header參數
//    		ErpApiBean bean = new ErpApiBean(apiName, companyId, sys, body, appKey, secretKey, wfAccount, area);
//    		
//			//呼叫新 WEB API
//			ErpHttpUrl rest = new ErpHttpUrl();
//			String result = rest.POST(url, bean);
//			
//			//將API回傳的JSON結果轉為明碼
//			String json = bean.getDeCodeResult();
//			logger.info("...apiName = " + apiName + "...鼎新回傳結果...decodeToString.result = " + Base64SHA256.decodeToString(bean.getResult()));
//			
//    		//把呼叫的明碼結果轉為JavaBean
//            Gson gson = new Gson();
//            Wrapper wrapper = gson.fromJson(json, Wrapper.class);
//            StdData stdData = wrapper.getStdData();
//
//            //顯示執行結果
//            logger.info("...stdData.getExecution().getDescription() = " + stdData.getExecution().getDescription());
//    		if ("-1".equals(stdData.getExecution().getCode())) {
//    			logger.info( "...stdData.getExecution().getDescription() = " + stdData.getParameter().getResult().getError().get(0).getMessage());
//    		}
//    		logger.info( "...stdData.toString() = " + stdData.toString());
//    		logger.info( "...查詢結果 = " + stdData.getParameter().getResult().getRows());
//    		ObjectMapper om = new ObjectMapper();
//    		results = stdData.getParameter().getResult().getRows();
//    		
//    		for(Map<String, String> map :results) {
//    			logger.info("==========================");
//    			logger.info(map.toString());
//    		}
//    		
//    		
//    		
//		}catch (Exception e) {
//			logger.info("...Exception : " + e);
//		}
//		return results;
//	}
	
//	public static PurchaseOrderDTO mapToPurchaseOrder(Map<String, String> dataMap) {
//        PurchaseOrderDTO po = new PurchaseOrderDTO();
//        po.setCOMPANY(dataMap.get("COMPANY"));
//        po.setCREATOR(dataMap.get("CREATOR"));
//        po.setUSR_GROUP(dataMap.get("USR_GROUP"));
//        po.setCREATE_DATE(dataMap.get("CREATE_DATE"));
//        po.setMODIFIER(dataMap.get("MODIFIER"));
//        po.setMODI_DATE(dataMap.get("MODI_DATE"));
//        po.setFLAG(dataMap.get("FLAG"));
//        po.setCREATE_TIME(dataMap.get("CREATE_TIME"));
//        po.setCREATE_AP(dataMap.get("CREATE_AP"));
//        po.setCREATE_PRID(dataMap.get("CREATE_PRID"));
//        po.setMODI_TIME(dataMap.get("MODI_TIME"));
//        po.setMODI_AP(dataMap.get("MODI_AP"));
//        po.setMODI_PRID(dataMap.get("MODI_PRID"));
//        po.setIMS_FLOWID(dataMap.get("IMS_FLOWID"));
//        po.setEF_ERPMA001(dataMap.get("EF_ERPMA001"));
//        po.setEF_ERPMA002(dataMap.get("EF_ERPMA002"));
//        po.setPurchase_type_no(dataMap.get("purchase_type_no"));
//        po.setPurchase_no(dataMap.get("purchase_no"));
//        po.setPurchase_date(dataMap.get("purchase_date"));
//        po.setVendor(dataMap.get("vendor"));
//        po.setTrans_currency(dataMap.get("trans_currency"));
//        po.setExchange_rate(dataMap.get("exchange_rate"));
//        po.setPrice_condition(dataMap.get("price_condition"));
//        po.setPayment_condition(dataMap.get("payment_condition"));
//        po.setRemarks(dataMap.get("remarks"));
//        po.setOm_site_id(dataMap.get("om_site_id"));
//        po.setItem_purchaser_code(dataMap.get("item_purchaser_code"));
//        po.setPrint_format(dataMap.get("print_format"));
//        po.setPrint_qty(dataMap.get("print_qty"));
//        po.setConfirm_code(dataMap.get("confirm_code"));
//        po.setPi_date(dataMap.get("pi_date"));
//        po.setPi_no(dataMap.get("pi_no"));
//        po.setTransport_mode_no(dataMap.get("transport_mode_no"));
//        po.setTaxed_code(dataMap.get("taxed_code"));
//        po.setPurchase_amount(dataMap.get("purchase_amount"));
//        po.setTax(dataMap.get("tax"));
//        po.setDelivery_address1(dataMap.get("delivery_address1"));
//        po.setDelivery_address2(dataMap.get("delivery_address2"));
//        po.setTotal_qty(dataMap.get("total_qty"));
//        po.setDoc_date(dataMap.get("doc_date"));
//        po.setConfirmor(dataMap.get("confirmor"));
//        po.setTax_rate(dataMap.get("tax_rate"));
//        po.setPayment_condition_no(dataMap.get("payment_condition_no"));
//        po.setDeposit_rate(dataMap.get("deposit_rate"));
//        po.setTotal_packing_qty(dataMap.get("total_packing_qty"));
//        po.setApproval_status_code(dataMap.get("approval_status_code"));
//        po.setTimes_of_sending(dataMap.get("times_of_sending"));
//        po.setProcess_code(dataMap.get("process_code"));
//        po.setThrow_status(dataMap.get("throw_status"));
//        po.setDownstream_supplier(dataMap.get("downstream_supplier"));
//        po.setEbc_confirm_code(dataMap.get("ebc_confirm_code"));
//        po.setEbc_po_no(dataMap.get("ebc_po_no"));
//        po.setEbc_purchase_version(dataMap.get("ebc_purchase_version"));
//        po.setExport_to_ebc(dataMap.get("export_to_ebc"));
//        po.setVersion(dataMap.get("version"));
//        po.setDeposit_instalment(dataMap.get("deposit_instalment"));
//        po.setMeans_of_transportation_code(dataMap.get("means_of_transportation_code"));
//        po.setTax_code(dataMap.get("tax_code"));
//        po.setTrade_condition(dataMap.get("trade_condition"));
//        po.setManufacturer(dataMap.get("manufacturer"));
//        po.setLock_code(dataMap.get("lock_code"));
//        po.setBody_multi_tax_rate(dataMap.get("body_multi_tax_rate"));
//        po.setContact(dataMap.get("contact"));
//        po.setDescription_a(dataMap.get("description_a"));
//        po.setDescription_b(dataMap.get("description_b"));
//        po.setDescription_c(dataMap.get("description_c"));
//        po.setDescription_d(dataMap.get("description_d"));
//        po.setProperty(dataMap.get("property"));
//        po.setDoc_name(dataMap.get("doc_name"));
//        po.setVendor_name(dataMap.get("vendor_name"));
//        po.setPlant_abbr(dataMap.get("plant_abbr"));
//        po.setPersonnel_name(dataMap.get("personnel_name"));
//        po.setApprover_name(dataMap.get("approver_name"));
//        po.setTerm_name(dataMap.get("term_name"));
//        po.setTax_name(dataMap.get("tax_name"));
//        po.setTrade_terms_name(dataMap.get("trade_terms_name"));
//        return po;
//    }

	public Map<String,List<String>> sendCreateApi(String body) {
		logger.info("CustomerItemApiService.sendCreateApi...");
        List<String> successList = new ArrayList();
        List<String> resultList = new ArrayList();
        List<String> errorList = new ArrayList();
        Map<String,List<String>> resultMap =new HashMap();
		try {
			//呼叫的 API名稱
			String apiName = createApiName;
			
			//設定header參數
    		ErpApiBean bean = new ErpApiBean(apiName, companyId, sys, body, appKey, secretKey, wfAccount, area);
    		
			//呼叫新 WEB API
			ErpHttpUrl rest = new ErpHttpUrl();
			String result = rest.POST(url, bean);
			
			//將API回傳的JSON結果轉為明碼
			String json = bean.getDeCodeResult();
			logger.info("CustomerItemApiService...sendCreateApi...apiName = " + apiName + "...鼎新回傳結果...decodeToString.result = " + Base64SHA256.decodeToString(bean.getResult()));
			
    		//把呼叫的明碼結果轉為JavaBean
            Gson gson = new Gson();
            Wrapper wrapper = gson.fromJson(json, Wrapper.class);
            StdData stdData = wrapper.getStdData();

            
            JSONObject jsonObject = new JSONObject(json);
            // 取得 "code" 節點的值
            String code = jsonObject.getJSONObject("std_data")
                                    .getJSONObject("execution")
                                    .getString("code");
    		
            
            if ("0".equals(code)) {
                // 取得 "success" 節點的值
                JSONArray successArray = jsonObject.getJSONObject("std_data")
                                                   .getJSONObject("parameter")
                                                   .getJSONObject("result")
                                                   .getJSONArray("success");
                // 輸出 success 節點的內容
                for (int i = 0; i < successArray.length(); i++) {
                	
                    JSONObject successItem = successArray.getJSONObject(i);
                    logger.info("po_change_type: " + successItem.getString("po_change_type"));
                    logger.info("po_change_no: " + successItem.getString("po_change_no"));
                    logger.info("msg: " + successItem.getString("msg"));
                    logger.info("version: " + successItem.getString("version"));
                    successList.add(successItem.getString("po_change_type") + "_" + successItem.getString("po_change_no")+"_"+successItem.getString("version"));
                }
                
                
                logger.info("successArray:"+successArray);
                resultMap.put("success", successList);
                
                
                JSONArray errorArray = jsonObject.getJSONObject("std_data")
                        .getJSONObject("parameter")
                        .getJSONObject("result")
                        .getJSONArray("error");
                logger.info("errorArray:"+errorArray);
                
                
              
                
                
                List<JSONArray> detailArrays = new ArrayList<>();
               
				// 遍歷 error array
				for (int i = 0; i < errorArray.length(); i++) {
					// 取得每個 error 物件中的 detail_of_purchase_order_data
					JSONArray detailArray = errorArray.getJSONObject(i).getJSONObject("data")
							.getJSONObject("header_of_purchase_change_order_data")
							.getJSONArray("detail_of_purchase_change_order_data");
					detailArrays.add(detailArray);
				}
				// 遍歷所有明細
				for (JSONArray detailArray : detailArrays) {
				    for (int i = 0; i < detailArray.length(); i++) {
				        JSONObject detail = detailArray.getJSONObject(i);
				        // 取得各個欄位值
		                logger.info("錯誤SAP單號&項次:"+detail.getString("new_remark"));
		                errorList.add(detail.getString("new_remark"));
				    }
				}
				 resultMap.put("error", errorList);
				
            }

           //顯示執行結果
            System.out.println("PurchaseOrderApiService...sendCreateApi...stdData.getExecution().getDescription() = " + stdData.getExecution().getDescription());
    		if ("-1".equals(stdData.getExecution().getCode())) {
                System.out.println("PurchaseOrderApiService...sendCreateApi...stdData.getExecution().getDescription() = " + stdData.getParameter().getResult().getError().get(0).getMessage());
    		}
    		System.out.println("PurchaseOrderApiService...sendCreateApi...stdData.toString() = " + stdData.toString());
    		
		}catch (Exception e) {
    		System.out.println("PurchaseOrderApiService...sendCreateApi...Exception : " + e);
		}
		return resultMap;
	}
}
