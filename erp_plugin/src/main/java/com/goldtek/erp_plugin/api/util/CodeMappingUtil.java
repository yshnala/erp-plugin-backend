package com.goldtek.erp_plugin.api.util;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CodeMappingUtil {

    private final Map<String, String> codeMap;

    public CodeMappingUtil() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("C007", "C007B");
        map.put("G007", "G007T");
        map.put("G010", "G010T");
        map.put("G014", "G014T");
        map.put("G015", "G015T");
        map.put("G021", "G021T");
        map.put("G030", "G030T");
        map.put("G045", "G045T");
        map.put("G060", "G060T");
        map.put("G075", "G075T");
        map.put("G090", "G090T");
        map.put("G120", "G120T");
        map.put("H030", "H030T");
        map.put("H045", "H045T");
        map.put("H060", "H060T");
        map.put("H075", "H075T");
        map.put("H090", "H090T");
        map.put("H120", "H120T");
        map.put("I030", "I030T");
        map.put("I045", "I045T");
        map.put("I060", "I060T");
        map.put("I075", "I075T");
        map.put("I090", "I090T");
        map.put("I120", "I120T");
        map.put("M030", "M030T");
        map.put("M045", "M045T");
        map.put("M060", "M060T");
        map.put("M075", "M075T");
        map.put("M080", "M080T");
        map.put("M085", "M085T");
        map.put("M090", "M090T");
        map.put("M105", "M105T");
        map.put("M120", "M120T");
        map.put("N007", "N007T");
        map.put("N010", "N010T");
        map.put("N014", "N014T");
        map.put("N015", "N015T");
        map.put("N020", "N020T");
        map.put("N030", "N030T");
        map.put("N035", "N035T");
        map.put("N045", "N045T");
        map.put("N060", "N060T");
        map.put("N075", "N075T");
        map.put("N090", "N090T");
        map.put("N105", "N105T");
        map.put("N120", "N120T");
        map.put("P030", "P030T");
        map.put("P045", "P045T");
        map.put("P060", "P060T");
        map.put("P075", "P075T");
        map.put("P090", "P090T");
        map.put("P120", "P120T");
        map.put("TT00", "TT001");
        map.put("A015", "A015T");
        map.put("A0A7", "A007B");
        map.put("A007", "A007T");
        map.put("A0B0", "A010B");
        map.put("A010", "A010T");
        map.put("A0B4", "A014B");
        map.put("A014", "A014T");
        map.put("A020", "A020T");
        map.put("A0D0", "A030B");
        map.put("A030", "A030T");
        map.put("A0D5", "A035B");
        map.put("A035", "A035T");
        map.put("A0E5", "A045B");
        map.put("A045", "A045T");
        map.put("A0G0", "A060B");
        map.put("A060", "A060T");
        map.put("A075", "A075T");
        map.put("A0J0", "A090B");
        map.put("A090", "A090T");
        map.put("A120", "A120T");
        map.put("B007", "B007T");
        map.put("B010", "B010T");
        map.put("B014", "B014T");
        map.put("B015", "B015T");
        map.put("B030", "B030T");
        map.put("B045", "B045T");
        map.put("B060", "B060T");
        map.put("B075", "B075T");
        map.put("B090", "B090T");
        map.put("B120", "B120T");
        map.put("C000", "C000T");

        this.codeMap = Collections.unmodifiableMap(map);
    }

    public String getValue(String key) {
        return codeMap.getOrDefault(key, key); // 找不到回傳原值
    }

    public Map<String, String> getAllMappings() {
        return codeMap;
    }
}