package com.easypost.model.enums.taxidentifiers;

import com.easypost.exception.EasyPostException;
import com.easypost.model.enums.EasyPostEnum;

public enum TaxIDType_FedEx implements EasyPostEnum {
    TIN("TIN");

    private String value;

    TaxIDType_FedEx(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaxIDType_FedEx getEnum(String value) throws EasyPostException {
        return (TaxIDType_FedEx) EasyPostEnum.getEnumFromValue(values(), value);
    }
}
