package com.goldtek.erp_plugin.api.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldtek.erp_plugin.api.bean.ErpApiBean;

import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class AESCrypt {
	static final Logger logger = LoggerFactory.getLogger(ErpApiBean.class);

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
//    private static final String ENCRYPTION_KEY = "12345678000000000000000000000000"; //WORD測試範例， 32 bytes for AES-256
    private static final String ENCRYPTION_KEY = "82004178210000000000000000000000"; //GOLDTEK32碼客戶代號， 32 bytes for AES-256
//    private static final String IV = "1234567890123456"; // 16 bytes for AES block size

    /**
     * 進行AES加密
     * 
     * @param data	要加密的內容，即鼎新發行的系統APP_KEY
     * @param iv	不重複的16碼數
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String iv) throws Exception {
    	
    	byte[] keyBytes = ENCRYPTION_KEY.getBytes("UTF-8");

        // 印出 key 長度（byte 與 bit）
    	logger.info("AES 金鑰長度（bytes）: " + keyBytes.length);
    	logger.info("AES 金鑰長度（bits）: " + (keyBytes.length * 8));

        // 檢查 JVM 是否允許對應金鑰長度
        int maxKeyLen = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");
        logger.info("JVM 允許的最大 AES 金鑰長度（bits）: " + maxKeyLen);
        
        
        SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ALGORITHM);
        IvParameterSpec ivParams = new IvParameterSpec(iv.getBytes());

        
        
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
        
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return bytesToHex(encrypted);
    }

    public static String decrypt(String encryptedData, String iv) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ALGORITHM);
        IvParameterSpec ivParams = new IvParameterSpec(iv.getBytes());

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);

        byte[] decrypted = cipher.doFinal(hexToBytes(encryptedData));
        return new String(decrypted);
    }

    // 將字節數組轉換為大寫的十六進制字符串
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b).toUpperCase(); // 將十六進制轉為大寫
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // 將十六進制字符串轉換為字節數組
    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                                 + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    // 將十六進制字符串轉換為字節數組
    public static void main(String[] args) {
        try {
        	String area = "test";	//test_測試區，prod_正式區
        	String sys = "pdm";		//呼叫鼎新 WEB API的系統，對照WorkflowERP_APIkey,DBaccount.xlsx
        	String iv = UniqueNumberGenerator.generateUniqueMillis(area, sys);
            String originalData = "E8AE986D-1C9B-454A-BE98-2DF3DB8AC4CB";
            String encryptedData = AESCrypt.encrypt(originalData, iv);
            String decryptedData = AESCrypt.decrypt(encryptedData, iv);

            System.out.println("Original Data: " + originalData);
            System.out.println("Encrypted Data: " + encryptedData);
            System.out.println("Decrypted Data: " + decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
