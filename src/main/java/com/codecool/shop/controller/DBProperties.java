package com.codecool.shop.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBProperties {
    public static Properties getProperties(){
        // create and load default properties
        Properties defaultProps = new Properties();
        FileInputStream in = null;
        String filename = "src/main/resources/connection.properties";
        try {
            in = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(in==null){
            System.out.println("Sorry, unable to find " + filename);
        }

        try {
            defaultProps.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultProps;
    }

    public static String getDatabase(){
        Properties prop = getProperties();
        return "jdbc:postgresql://"
                + prop.getProperty("url") + "/"
                + prop.getProperty("database");
    }

    public static String getDBUser(){
        Properties prop = getProperties();
        return prop.getProperty("user");

    }

    public static String getDBPassword(){
        Properties prop = getProperties();
        return prop.getProperty("password");

    }
}

