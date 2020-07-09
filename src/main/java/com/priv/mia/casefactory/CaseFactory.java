package com.priv.mia.casefactory;

/**
 * CaseFactory
 *
 * @author : wuyz
 * @date : 2020/07/09
 */
public class CaseFactory {

    private CaseFactory(){}

    protected static final String BEGIN_UPPER_CASE = "BeginUpper";

    protected static final String ALL_UPPER_CASE = "AllUpper";

    protected static final String BEGIN_LOWER_CASE = "BeginLower";

    protected static final String ALL_LOWER_CASE = "AllLower";

    public static AbstractCase createCase(String toCase){
        switch (toCase){
            case BEGIN_UPPER_CASE:
                return new BeginUpperCase();
            case ALL_UPPER_CASE:
                return new AllUpperCase();
            case BEGIN_LOWER_CASE:
                return new BeginLowerCase();
            case ALL_LOWER_CASE:
                return new AllLowerCase();
            default:
                return null;
        }
    }

}
