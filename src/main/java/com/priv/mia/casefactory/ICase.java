package com.priv.mia.casefactory;

import java.util.HashMap;

public abstract class ICase {

    // 各条件下的所有待追加的标签
    public abstract HashMap<String,Object> getToCaseMap(String dataSource,HashMap<String,Object> alreadyMap);
    // 各条件的值转化规则
    protected abstract Object transformAppenedTagValue(Object alreadyTagValue);
}
