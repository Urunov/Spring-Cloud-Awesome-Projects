package com.qqb.travelclub.shared;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NameValueList {
    //
    private List<NameValue> nameValues;

    public NameValueList() {
        //
        this.nameValues = new ArrayList<>();
    }

    public void addNameValue(String name, String value) {
        //
        NameValue nameValue = new NameValue(name, value);
        nameValues.add(nameValue);
    }
}
