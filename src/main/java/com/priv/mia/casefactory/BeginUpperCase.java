package com.priv.mia.casefactory;


import com.priv.mia.util.CaseUtil;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 首字母大写
 */
public class BeginUpperCase extends ICase {

    @Override
    public HashMap<String, Object> getToCaseMap(String dataSource,HashMap<String,Object> alreadyMap) {

        HashSet<String> appenedTagKeys = CaseUtil.getAppenedTagKeys(dataSource, CaseFactory.beginUpperCase);
        // 追加的标签
        HashMap<String,Object> appenedTagMap = new HashMap<>();
        if(appenedTagKeys.size() > 0){
            appenedTagKeys.forEach(item -> {
                Object alreadyTagValue = CaseUtil.getAlreadyTagValue(item, alreadyMap, CaseFactory.beginUpperCase);
                appenedTagMap.put(item, transformAppenedTagValue(alreadyTagValue));
            });
            return appenedTagMap;
        }else {
            return null;
        }
    }


    @Override
    protected Object transformAppenedTagValue(Object alreadyTagValue) {
        char[] chars = alreadyTagValue.toString().toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }

}
