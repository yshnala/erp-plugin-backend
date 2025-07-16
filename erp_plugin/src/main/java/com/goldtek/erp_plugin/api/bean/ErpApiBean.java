package com.goldtek.erp_plugin.api.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldtek.erp_plugin.api.util.AESCrypt;
import com.goldtek.erp_plugin.api.util.Base64SHA256;
import com.goldtek.erp_plugin.api.util.UniqueNumberGenerator;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.JsonObject;


/**
 * 呼叫鼎新ERP的共用Header
 * 
 * @author macgyver_chung
 *
 */
public class ErpApiBean {
	static final Logger logger = LoggerFactory.getLogger(ErpApiBean.class);

    Gson gson = new Gson();			//轉 JSON用
    
	/**
	 * WF集成服務主機資訊 
	 *
	 */
	public class digiService{
		private String prod = "WEGP";
		private String name = "";
		
		public String getProd() {
			return prod;
		}
		public void setProd(String prod) {
			this.prod = prod;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	/**
	 * WF 公司代號
	 * @author macgyver_chung
	 *
	 */
	public class digiDataKey{
		private String companyId;	//WF公司代號，對應DSCSYS.DSCMB中的 company

		public String getCompanyId() {
			return companyId;
		}

		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}
		
	}
	
	/**
	 * 建構子
	 */
	public ErpApiBean() {
		
	}
	
	/**
	 * 
	 * @param apiName		呼叫的 API名稱
	 * @param companyId		WF公司代號，對應DSCSYS.DSCMB中的 company
	 * @param sys			查DEBUG LOG用的代碼，直接以系統名稱代替
	 * @param body			給 API的內容
	 * @param appKey		鼎新發行的APP KEY(每個系統的APP KEY都不一樣)，對照WorkflowERP_APIkey,DBaccount.xlsx
	 * @param secretKey		替換為你的Secret Key，對照WorkflowERP_APIkey,DBaccount.xlsx
	 * @param wfAccount		用於集成的WF帳號
	 * @param area			區域，test_測試區，prod_正式區
	 */
	public ErpApiBean(String apiName, String companyId, String sys, String body, String appKey, String secretKey, String wfAccount, String area) {
		
		try {
			//替換為你要加密的字符串
			String iv = UniqueNumberGenerator.generateUniqueMillis(area, sys);	//不重複的16碼數，記得要設定str1, str2
			String vKey = AESCrypt.encrypt(appKey, iv);				//AES加密後的APP_KEY
	        String vSign = Base64SHA256.getVSign(body, secretKey);	//BODY進行BASE64加密，用鼎新系統控制員授權資料的客戶代號當Secret KEY，來進行SHA256雜湊，用hash_function轉字串
			
			this.getDigiService().setName(apiName);					//操作的功能，對應DSCSYS.dbo.OAPMA的 MA001欄
			this.getDigiDataKey().setCompanyId(companyId);			//WF 公司代號
			this.setDigiReqId(digiReqId);							//調用端識別碼，建議使用GUID。供DEBUG LOG查找。
			
			ErpHeaderDigiHost digiHost = new ErpHeaderDigiHost();
			digiHost.setAcct(wfAccount);							//用於集成的WF帳號
			digiHost.setLang("zh_TW");								//用於集成的WF帳號
			digiHost.setTimestamp();								//時間戳，格式：yyyyMMddHHmmssSSS
			digiHost.setProd(sys);									//對應鼎新開的調用產品代號
			this.setDigiHost(digiHost);
			this.setvKey(vKey);
			this.setvSign(vSign);
			this.setIv(iv);
			this.setBody(body);

			logger.info("ErpApiBean ...iv = " + iv);
			logger.info("ErpApiBean...vKey = " + vKey);
			logger.info("ErpApiBean...vSign = " + vSign);
		} catch (Exception e) {
			logger.error("ErpHttpUrl...Exception : " + (e));
		}
	}
	
	//Header
    private String contentType;		//一律為application/json; charset=utf-8
    private digiService digiService = new digiService();	//API名稱，WF集成服務主機資訊，{"prod":"WFGP","name":"wf.oapi.item.data.query.get"}
    private String digiServiceJson;
    private digiDataKey digiDataKey = new digiDataKey();		//WF 公司代號，{"CompanyId":"please fill your ERP Company Id"}
    private String digiDataKeyJson;
    private ErpHeaderDigiHost digiHost;		//服務調用端主機資訊。 {"acct":"please fill your ERP Account","lang":"en","timestamp":"20241231235959","prod":"please fill caller id"}
    private String digiHostJson;	//把digiHost轉成 json字串
    private String digiReqId;		//調用端識別碼，建議使用GUID。供DEBUG LOG查找。
    private String vKey;			//使用AES256加密的密文AES256(app_key, key, iv, padding, clipher_mode)
    private String vSign;			
    private String iv;
    private String apiReturnJson;
    private String result;
    private String deCodeResult;
    private String body;
    private String bodyJson;
    
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Gson getGson() {
		return gson;
	}
	public void setGson(Gson gson) {
		this.gson = gson;
	}
	public digiService getDigiService() {
		return digiService;
	}
	public void setDigiService(digiService digiService) {
		this.digiService = digiService;
	}
	public String getDigiServiceJson() {
		digiServiceJson = gson.toJson(digiService);
		return digiServiceJson;
	}
	public void setDigiServiceJson(String digiServiceJson) {
		this.digiServiceJson = digiServiceJson;
	}
	public digiDataKey getDigiDataKey() {
		return digiDataKey;
	}
	public void setDigiDataKey(digiDataKey digiDataKey) {
		this.digiDataKey = digiDataKey;
	}
	public String getDigiDataKeyJson() {
		digiDataKeyJson = gson.toJson(digiDataKey);
		digiDataKeyJson = digiDataKeyJson.replaceAll("companyId", "CompanyId");
		return digiDataKeyJson;
	}
	public void setDigiDataKeyJson(String digiDataKeyJson) {
		this.digiDataKeyJson = digiDataKeyJson;
	}
	public ErpHeaderDigiHost getDigiHost() {
		return digiHost;
	}
	public void setDigiHost(ErpHeaderDigiHost digiHost) {
		this.digiHost = digiHost;
	}
	public String getDigiHostJson() {
        digiHostJson = gson.toJson(digiHost);
		return digiHostJson;
	}
	public void setDigiHostJson(String digiHostJson) {
		this.digiHostJson = digiHostJson;
	}
	public String getDigiReqId() {
		return digiReqId;
	}
	public void setDigiReqId(String digiReqId) {
		this.digiReqId = digiReqId;
	}
	public String getvKey() {
		return vKey;
	}
	public void setvKey(String vKey) {
		this.vKey = vKey;
	}
	public String getvSign() {
		return vSign;
	}
	public void setvSign(String vSign) {
		this.vSign = vSign;
	}
	public String getIv() {
		return iv;
	}
	public void setIv(String iv) {
		this.iv = iv;
	}
	public String getApiReturnJson() {
		return apiReturnJson;
	}
	public void setApiReturnJson(String apiReturnJson) {
		this.apiReturnJson = apiReturnJson;
	}
	public String getResult() {
        // 解析 JSON 字符串
        JsonObject jsonObject = gson.fromJson(this.apiReturnJson, JsonObject.class);

        // 提取 result 欄位
        result = jsonObject.get("result").getAsString();
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDeCodeResult() {
		deCodeResult = Base64SHA256.decodeToString(getResult());
		return deCodeResult;
	}
	public void setDeCodeResult(String deCodeResult) {
		this.deCodeResult = deCodeResult;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBodyJson() {
		String bodyBase64 = Base64SHA256.encodeToBase64(this.body);
		
        // 創建 JSON 對象
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("value", bodyBase64);

        // 使用 Gson 轉換成 JSON 字符串
        Gson gson = new Gson();
        bodyJson = gson.toJson(jsonObject);
		return bodyJson;
	}
	public void setBodyJson(String bodyJson) {
		this.bodyJson = bodyJson;
	}
    
	
}
