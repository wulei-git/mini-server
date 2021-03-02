package com.init.mini.web.dto;

public class GoodsTargetRedisPO {

    private String targetType;

    GoodPO[][] goodPOS;

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public GoodPO[][] getGoodPOS() {
        return goodPOS;
    }

    public void setGoodPOS(GoodPO[][] goodPOS) {
        this.goodPOS = goodPOS;
    }
}
