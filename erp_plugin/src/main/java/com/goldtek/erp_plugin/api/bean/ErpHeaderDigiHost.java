package com.goldtek.erp_plugin.api.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.nimbusds.jose.shaded.gson.Gson;


/**
 * 鼎新ERP的共用Header中的digi-host
 * 
 * @author macgyver_chung
 *
 */
public class ErpHeaderDigiHost {

	public ErpHeaderDigiHost() {
		// TODO Auto-generated constructor stub
	}
	
    private String acct;			//用於集成的WF帳號
    private String lang;			//語言，zh_CN：簡體中文		zh_TW：繁體中文		 en：英文
    private long timestamp;			//時間戳，格式：yyyyMMddHHmmssSSS
    private String prod;			//對應鼎新開的調用產品代號

    // Getters and Setters
    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public void setTimestamp() {
        // 當前時間
        Date now = new Date();
        // 定義日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 格式化當前時間為指定格式
        this.timestamp = Long.parseLong(dateFormat.format(now));
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    // 將對象轉換為 JSON 字符串
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
