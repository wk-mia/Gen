package com.priv.mia.casefactory;

import java.util.HashMap;


/**
 * AbstractCase
 *
 * @author : wuyz
 * @date : 2020/07/09
 */
public abstract class AbstractCase {

    /**
     * 各条件下的所有待追加的标签
     * @param dataSource
     * @param alreadyMap
     * @return
     */
    public abstract HashMap<String,Object> getToCaseMap(String dataSource,HashMap<String,Object> alreadyMap);

    /**
     * 各条件的值转化规则
     * @param alreadyTagValue
     * @return
     */
    protected abstract Object transformAppenedTagValue(Object alreadyTagValue);
}
