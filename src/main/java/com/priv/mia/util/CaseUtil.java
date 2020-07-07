package com.priv.mia.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaseUtil {

    // 匹配需要追加标签的名称
    public static HashSet<String> getAppenedTagKeys(String dataSource, String toCase) {
        // \$\{[^}]*AllUpper\}
        // 表达式
        String str = "\\$\\{[^}]*".concat(toCase).concat("\\}");
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(dataSource);
        // 匹配结果
        HashSet<String> appenedTagKeys = new HashSet<String>();
        while (matcher.find()){
            appenedTagKeys.add(matcher.group().replace("${","").replace("}",""));
        }
        return appenedTagKeys;
    }

    // 获取已有标签原值
    public static Object getAlreadyTagValue(String appenedTagKey,HashMap<String,Object> alreadyMap,String toCase){
        // 追加标签的值=条件处理(已有标签源值)
        String alreadyTagKey = appenedTagKey.replace(toCase,"");
        Object alreadyTagValue = alreadyMap.get(alreadyTagKey);
        if(alreadyTagValue == null){
            throw new NullPointerException(
                    new StringBuffer("No tags configured of:").
                            append("\r\n").
                            append(appenedTagKey).
                            append("\r\n").
                            append("please check the properties file.").toString());
        }
        return alreadyTagValue;
    }

}
