package gorest.tests.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import gorest.pojos.ApiGo;
import gorest.pojos.Data;
import gorest.pojos.Meta;
import gorest.utilities.ConfigurationReader;
import gorest.utilities.ReusableMethods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TC_01_02_03 {

    Response response;
    String endPoint="https://gorest.co.in/public-api/users/";
    JsonPath json;
    Map<String,Object> postMap = new HashMap<>();
    Faker faker=new Faker();
    String token="5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";

    public void postMethod(Map body) {
        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(postMap).
                when().
                post(endPoint);
        response.prettyPrint();
    }
    //yeni bir data oluşturun
    @Test
    public void TC01() {
        postMap.put("name",faker.name().fullName());
        postMap.put("email",faker.internet().emailAddress());
        postMap.put("gender","female");
        postMap.put("status","active");

        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(postMap).
                when().
                post(endPoint);
       json = response.jsonPath();
        response.prettyPrint();



    }


    //yeni bir data oluşturun/name eksik
    @Test
    public void TC02() throws JsonProcessingException {
        Data data=new Data(faker.internet().emailAddress(),"male","active");

        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(data).
                when().
                post(endPoint);
        response.prettyPrint();
        json= response.jsonPath();

        String message=json.getString("data.message");
        System.out.println(message);
        Assert.assertEquals(message,"[can't be blank]");

    }

    //yeni bir data oluşturun/email eksik
    @Test
    public void TC03() {
        postMap.put("name", "aaabza");
        postMap.put("gender", "male");
        postMap.put("status", "active");

        postMethod(postMap);

        response.prettyPrint();
        json= response.jsonPath();
        String message=json.getString("data.message");
        String field=json.getString("data.field");
        System.out.println(field +" bolumu mesaji : " +message);

    }

}
