package gorest.utilities;

import java.io.FileInputStream;
import java.util.Properties;
//
//public class ConfigurationReader {
//
//    private static Properties properties;
//
//    //path of the configuration.properties file
//    String path = "C:/Users/pinar/IdeaProjects/ApiGo/configuration.properties";
//
//
//    FileInputStream fileInputStream;
//    {
//        try {
//            //Opening the configuration.properties file
//            fileInputStream=new FileInputStream(path);
//            //Loading and reading the file
//            properties = new Properties();
//            properties.load(fileInputStream);
//            //closing the file
//            fileInputStream.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    //Create a method to READ
//    //This method will get the KEY and return the VALUE
//    public static String getProperty(String key){
//        return properties.getProperty(key);
//    }
//
//    public static void main(String[] args) {
//        String str = getProperty("token");
//        System.out.println("str = " + str);
//    }
//}




//ustteki calismazsa bu denenebilir
public class ConfigurationReader {


    static Properties properties;
    static{
        String path = "configuration.properties";
        try{
            FileInputStream file = new FileInputStream(path);
            properties = new Properties();

            properties.load(file);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  static String getProperty(String key){
        return properties.getProperty(key);
    }
}

