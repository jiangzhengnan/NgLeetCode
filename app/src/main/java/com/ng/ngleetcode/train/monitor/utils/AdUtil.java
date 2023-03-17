package com.ng.ngleetcode.train.monitor.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : 
 * @creation : 2022/12/19
 * @description :
 */
public class AdUtil {

    /**
     * 通过映射关系得到adnId
     */
    @Nullable
    public static String getAdnIdFromFeatureName(@Nullable String featureName) {
        if (StringUtils.isEmpty(featureName)) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        map.put("adn_tanx_sdk", "18");
        map.put("adn_huawei_sdk", "10");
        map.put("adn_tencent_sdk", "3");
        map.put("adn_pangolin_sdk", "2");
        map.put("adn_kaijia_sdk", "6");
        map.put("adn_hongshun_sdk", "4");
        map.put("adn_baidu_sdk", "7");
        map.put("adn_kuaishou_sdk", "8");
        map.put("adn_jingdong_sdk", "11");
        map.put("adn_yky_sdk", "17");
        return map.get(featureName);
    }

    @NonNull
    public static String getModulesName(@Nullable List<String> moduleNameList) {
        StringBuilder res = new StringBuilder();
        if (moduleNameList == null || moduleNameList.size() == 0) {
            return res.toString();
        }
        for (String moduleName : moduleNameList) {
            res.append(moduleName).append(",");
        }
        return res.toString();
    }

    public static boolean isContainsInModules(int adnId, @Nullable final String modulesName) {
        if (StringUtils.isEmpty(modulesName)) {
            return false;
        }
        String[] moduleNames = modulesName.split(",");
        if (moduleNames.length == 0) {
            return false;
        }
        for (String name : moduleNames) {
            String tempAdnId = getAdnIdFromFeatureName(name);
            if (StringUtils.isNotEmpty(tempAdnId) && tempAdnId.equals(String.valueOf(adnId))) {
                return true;
            }
        }
        return false;
    }
}
