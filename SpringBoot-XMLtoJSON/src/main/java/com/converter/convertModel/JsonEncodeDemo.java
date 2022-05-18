package com.converter.convertModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {XMLtoJSON}
 * @Date: {2022/05/18 && 2:43 PM}
 */
public class JsonEncodeDemo {
    public static void main(String[] args) throws JSONException {

        JSONObject object = new JSONObject();
        object.put("name", "Goo");
        object.put("num", new Integer(10));
        object.put("balance", new Double(10002.1));
        object.put("is_vip", new Boolean(true));


        System.out.println(object);
    }
}
