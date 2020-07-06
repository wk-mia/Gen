package com.priv.mia.domain;

import org.springframework.stereotype.Component;

@Component
public class ReName {

    private Boolean isNeedReName;

    private String oldExpress;

    private String newExpress;


    public void setIsNeedReName(Boolean isNeedReName) {
        this.isNeedReName = isNeedReName;
    }

    public boolean getIsNeedReName(){
        return isNeedReName;
    }

    public String getOldExpress() {
        return oldExpress;
    }

    public void setOldExpress(String oldExpress) {
        this.oldExpress = oldExpress;
    }

    public String getNewExpress() {
        return newExpress;
    }

    public void setNewExpress(String newExpress) {
        this.newExpress = newExpress;
    }
}
