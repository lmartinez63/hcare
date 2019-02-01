package com.landl.hcare.entity;

import java.util.HashMap;
import java.util.Map;

public class DataContent {
    Map<String, Object> dataMap;

    public DataContent() {
        this.dataMap = new HashMap<String, Object>();
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
    public void addData(String stringData, Object entityData) {
        this.dataMap.put(stringData, entityData);
    }
}
