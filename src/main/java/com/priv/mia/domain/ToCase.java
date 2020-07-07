package com.priv.mia.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ToCase {

    // 是否开启字符串条件转换
    private boolean enableToCase = false;
    // 字符串转换类型
    private ArrayList<String> toCaseList;


    public boolean isEnableToCase() {
        return enableToCase;
    }

    public void setEnableToCase(boolean enableToCase) {
        this.enableToCase = enableToCase;
    }

    public ArrayList<String> getToCaseList() {
        return toCaseList;
    }

    public void setToCaseList(ArrayList<String> toCaseList) {
        this.toCaseList = toCaseList;
    }
}
