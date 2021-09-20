package gorest.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.pojos.ApiGo;
import gorest.pojos.Data;
import gorest.utilities.ConfigurationReader;
import gorest.utilities.ReusableMethods;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.module.Configuration;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TC_Get_Pojo_11 {
    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    ObjectMapper objectMapper = new ObjectMapper();
    ApiGo apiGoPojo;

    @BeforeMethod
    public void setup() throws JsonProcessingException {
        response = ReusableMethods.getResponsePojo(endpoint);
        apiGoPojo = objectMapper.readValue(response.asString(), ApiGo.class);


    }


    @Test
    public void tcGet111() throws JsonProcessingException {
        int totalPage = apiGoPojo.getMeta().getPagination().getPages();
        int countFemale = 0;
        int countMale = 0;
        int countNotr = 0;

        for (int i = 1; i <= totalPage; i++) {
            response = given().queryParam("page", i). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    when().get(endpoint);
           apiGoPojo = objectMapper.readValue(response.asString(), ApiGo.class);


        List<Data> genderList = apiGoPojo.getData();
        for(Data w : genderList){
            if (w.getGender().equals("male")){
                countMale++;
            }else if (w.getGender().equals(("female"))){
                countFemale++;
            }else {
                countNotr++;
            }

        }

        }
        System.out.println("female:" + countFemale);
        System.out.println("male:" +countMale);
        System.out.println("Notr:" +countNotr);
//        Assert.assertEquals(countFemale, countMale);
        Assert.assertFalse(countFemale>countMale && countFemale<countNotr);
    }

}
