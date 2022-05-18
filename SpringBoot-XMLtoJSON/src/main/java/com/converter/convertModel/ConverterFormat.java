package com.converter.convertModel;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {XMLtoJSON}
 * @Date: {2022/05/18 && 2:36 PM}
 */
public class ConverterFormat {

    public static void main(String[] args) {

        String examplexml = "<student>\r\n " +
                "<firstName> Hamdamboy</firstName> \r\n" +
                "<id> 1 </id>\r\n"+
                "<lastName>Urunov</lastName> \r\n" +
                "</student>";


        System.out.println(examplexml); //printing Example of XML

        System.out.println("JSON Format is:  ");
        try {
            JSONObject jsonObject = XML.toJSONObject(examplexml);
            System.out.println(jsonObject);
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
