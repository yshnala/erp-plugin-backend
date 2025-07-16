package com.goldtek.erp_plugin.api.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Base64SHA256 {
    public static void main(String[] args) {
        String input = "{\"std_data\":{\"parameter\":{\"page_size\":10,\"page_no\":1,\"conditions\":{\"operator\":\"AND\",\"fields\":[{\"field_name\":\"customer_no\",\"operator\":\"=\",\"value\":\"001\"}]},\"orders\":[{\"field_name\":\"customer_no\",\"order_type\":\"asc\"}]}}}"; // 替換為你要加密的字符串

        String secretKey = "8200417821"; // 替換為你的Secret Key

        String base64Encoded = encodeToBase64(input);
        String hmacSha256 = hmacSHA256(base64Encoded, secretKey);

        System.out.println("Base64 編碼: " + base64Encoded);
        System.out.println("HMAC-SHA256: " + hmacSha256);
    }
    
    /**
     * Base64 編碼
     * 
     * @param input
     * @return
     */
    public static String encodeToBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    /**
     * 解碼
     * 
     * @param str
     * @return
     */
    public static String decodeToString(String str) {
        // 解碼
        byte[] decodedBytes = Base64.getDecoder().decode(str);

        // 將解碼的字節數組轉換為字符串
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    // 使用自訂 Secret Key 計算 HMAC-SHA256
    public static String hmacSHA256(String data, String secretKey) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            byte[] hmacBytes = mac.doFinal(data.getBytes());

            // 將 HMAC 結果轉換為十六進制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hmacBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {
            System.err.println("---Base64SHA256.getVSign...計算 HMAC 錯誤: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * BODY進行BASE64加密，用鼎新系統控制員授權資料的客戶代號當Secret KEY，來進行SHA256雜湊，用hash_function轉字串
     * 
     * @param data
     * @param secretKey
     * @return
     */
    public static String getVSign(String data, String secretKey) {
    	String vSign = "";
    	
        try {
        	//BODY進行BASE64加密
            String base64Encoded = encodeToBase64(data);
            
            //用鼎新系統控制員授權資料的客戶代號當Secret KEY，來進行SHA256雜湊，用hash_function轉字串
            vSign = hmacSHA256(base64Encoded, secretKey);
            
            System.out.println("---Base64SHA256.getVSign...Base64 編碼: " + base64Encoded);
            System.out.println("---Base64SHA256.getVSign...vSign = " + vSign);
            
        } catch (Exception e) {
            System.err.println("---Base64SHA256.getVSign...計算 HMAC 錯誤: " + e.getMessage());
            e.printStackTrace();
        }
        
        return vSign;
    }
}