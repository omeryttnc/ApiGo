package gorest.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    //path of the configuration.properties file
    String path = "configuration.properties";



    FileInputStream fileInputStream;
    {
        try {
            //Opening the configuration.properties file
            fileInputStream=new FileInputStream(path);
            //Loading and reading the file
            properties = new Properties();
            properties.load(fileInputStream);
            //closing the file
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Create a method to READ
    //This method will get the KEY and return the VALUE
    public static String getProperty(String key){
        return properties.getProperty(key);
    }


    public String getBaseUrl() {
        String baseUrl = properties.getProperty("base_Url");
        if(baseUrl != null) return baseUrl;
        else throw new RuntimeException("base_Url not specified in the Configuration.properties file.");
    }
}
