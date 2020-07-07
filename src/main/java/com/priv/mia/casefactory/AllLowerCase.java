package com.priv.mia.casefactory;

import com.priv.mia.util.CaseUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 全字母小写
 */
public class AllLowerCase extends ICase {

    @Override
    public HashMap<String, Object> getToCaseMap(String dataSource,HashMap<String,Object> alreadyMap) {

        HashSet<String> appenedTagKeys = CaseUtil.getAppenedTagKeys(dataSource, CaseFactory.allLowerCase);
        // 追加的标签
        HashMap<String,Object> appenedTagMap = new HashMap<>();
        if(appenedTagKeys.size() > 0){
            appenedTagKeys.forEach(item -> {
                Object alreadyTagValue = CaseUtil.getAlreadyTagValue(item, alreadyMap, CaseFactory.allLowerCase);
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
        for (int i = 0 ; i < chars.length ; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = (char) (chars[i] + 32);
            }
        }
        return new String(chars);
    }
}
