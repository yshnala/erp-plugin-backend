package com.goldtek.erp_plugin.api.util;
import java.util.HashSet;
import java.util.Random;



/**
 * 產16碼數字，為避免不同系統都使用毫秒可能發生的相同情況，故編16碼規則如下
 * 第1碼：1_測試區，2_正式區
 * 第2~3碼：10_未指定，11_gss, 12_gtscm, 13_kingdee, 14_pdm, 15_qs, 16_workflow
 *第4~16碼：時間毫秒
 * 
 * @author macgyver_chung
 * @version	1131001，第一版
 */
public class UniqueNumberGenerator {
    static String str1 = "1";			//第1碼：1_測試區，2_正式區
    static String str2 = "10";			//第2~3碼：10_未指定，11_gss, 12_gtscm, 13_kingdee, 14_pdm, 15_qs, 16_workflow
	
	public static void main(String[] args) {
        System.out.println("Unique 16-digit Number...");
        String uniqueNumber = generateUniqueMillis();
        System.out.println("---UniqueNumberGenerator.main...Unique 16-digit Number: " + uniqueNumber);
    }
    
	/**產16碼數字，為避免不同系統都使用毫秒可能發生的相同情況，故編16碼規則如下
 * 第1碼：1_測試區，2_正式區
 * 第2~3碼：10_未指定，11_gss, 12_gtscm, 13_kingdee, 14_pdm, 15_qs, 16_workflow
 *第4~16碼：時間毫秒
	 * 
	 * @return
	 */
    private static String generateUniqueMillis() {
    	String num16 = str1 + str2;
    	
    	try {
            // 獲取當前毫秒級時間戳
            long millis = System.currentTimeMillis();

            // 將時間戳轉換為字符串
            String millisString = String.valueOf(millis);
            num16 += millisString;
//            System.out.println("---UniqueNumberGenerator.generateUniqueMillis...num16 = " + num16);
            System.out.println("UniqueNumberGenerator...generateUniqueMillis...num16 = " + num16);
    		
    	}catch (Exception e) {
    		System.out.println("UniqueNumberGenerator...generateUniqueMillis...Exception : " + (e));
		}
        return num16;
    }
    
    /**
     * 第1碼：1_測試區，2_正式區
     * 第2~3碼：10_未指定，11_gss, 12_gtscm, 13_kingdee, 14_pdm, 15_qs, 16_workflow
     * 
     * @param area	區域，test_測試區，prod_正式區
     * @param sys
     * @return
     */
    public static String generateUniqueMillis(String area, String sys) {
    	String num16 = "";
    	
    	try {
    		System.out.println("generateUniqueMillis...area = " + area + "...sys = " + sys);
    		
    		//設定區域
    		if ("prod".equals(area)) {
    			//正式區
    			str1 = "2";
    			
    		}else {
    			//測試區
    			str1 = "1";
    		}
    		
    		//設定系統別
    		if ("gss".equals(sys)) {
    			//11_gss
    			str2 = "12";
    			
    		}else if ("gtscm".equals(sys)) {
    			//12_gtscm
    			str2 = "12";
    			
    		}else if ("kingdee".equals(sys)) {
    			//13_kingdee
    			str2 = "13";
    			
    		}else if ("pdm".equals(sys)) {
    			//14_pdm
    			str2 = "42";
    			
    		}else if ("qs".equals(sys)) {
    			//15_qs
    			str2 = "15";
    			
    		}else if ("workflow".equals(sys)) {
    			//16_workflow
    			str2 = "16";
    		}
    		
    		num16 = generateUniqueMillis();
    	}catch (Exception e) {
    		System.out.println("UniqueNumberGenerator...Exception : " + (e));
		}
    	
    	return num16;
    }
}
