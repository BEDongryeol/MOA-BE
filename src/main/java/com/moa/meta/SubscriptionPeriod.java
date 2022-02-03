package com.moa.meta;

public enum SubscriptionPeriod {
    TWELVE("12개월"), TWENTY_FOUR("24개월");

    private final String value;

    SubscriptionPeriod(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
