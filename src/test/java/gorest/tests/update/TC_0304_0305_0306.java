package gorest.tests.update;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
//import gorest.utilities.ReusableMethods;
//import gorest.utilities.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;

public class TC_0304_0305_0306 extends TestBase {

    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;
    ObjectMapper objectMapper = new ObjectMapper();
    String token = "0568b7dc2b37bc696d140245ccb1f84e7b7682bf90eba7fbf712e9f36689ba2c";
    Faker faker = new Faker();

        int userId =100;

    @Test
    public void TC_0304() {
        //data update (name,email,status)
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("name", faker.name().fullName());
        mapBody.put("email", faker.internet().emailAddress());
        mapBody.put("status", ReusableMethods.RandomStatus());
        response = given().spec(spec01).contentType(ContentType.JSON).auth().oauth2(token).body(mapBody).when().put( "/100");
        response.prettyPrint();
    }
    @Test
    public void TC_0305() {
        //data update (name,email,gender)
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("name", faker.name().fullName());
        mapBody.put("email", faker.internet().emailAddress());
        mapBody.put("gender", ReusableMethods.RandomGender());
        response = given().contentType(ContentType.JSON).auth().oauth2(token).body(mapBody).when().put(endpoint + userId);
        response.prettyPrint();
    }
    @Test
    public void TC_0306() {
        //data update  (name)
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("name", faker.name().fullName());
        response = given().spec(getSpec01).contentType(ContentType.JSON).auth().oauth2(token).body(mapBody).when().put("/users/100");
        response.prettyPrint();
    }
}
