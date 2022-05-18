package com.converter.convertModel.user;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Company: {}
 * @Author: {urunov}
 * @Project: {XMLtoJSON}
 * @Date: {2022/05/18 && 4:25 PM}
 */
public class MainUser {
    //
    public final static String XML_FILE = "users.xml";

    public static void main(String[] args) throws FileNotFoundException, JAXBException{
        Users users = convertXmlToObject(new FileInputStream(new File(XML_FILE)));
        System.out.println(convertObjectToJson(users));
    }

    public static Users convertXmlToObject(InputStream in) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (Users) unmarshaller.unmarshal(in);
    }

    public static String convertObjectToJson(Users user){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.disableHtmlEscaping();

        Gson gson = gsonBuilder.create();
        return gson.toJson(user, Users.class);
    }
}
