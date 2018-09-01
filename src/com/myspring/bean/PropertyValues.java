package com.myspring.bean;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

    public void add(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }
}
