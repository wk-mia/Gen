package com.priv.mia.casefactory;

public class CaseFactory {

    protected static final String beginUpperCase = "BeginUpper";

    protected static final String allUpperCase = "AllUpper";

    protected static final String beginLowerCase = "BeginLower";

    protected static final String allLowerCase = "AllLower";

    public static ICase createCase(String toCase){
        switch (toCase){
            case beginUpperCase:
                return new BeginUpperCase();
            case allUpperCase:
                return new AllUpperCase();
            case beginLowerCase:
                return new BeginLowerCase();
            case allLowerCase:
                return new AllLowerCase();
            default:
                return null;
        }
    }

}
