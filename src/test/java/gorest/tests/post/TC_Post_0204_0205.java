package gorest.tests.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import gorest.utilities.ReusableMethods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;

public class TC_Post_0204_0205 {
    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;
    ObjectMapper objectMapper = new ObjectMapper();
    String token = "5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";
    Faker faker = new Faker();


    @Test
    public void TC_Post_0204() {
        /*
        can't be blank"" message assertion"
         */
//        Random random = new Random();
//        String[] status = {"active", "inactive"};
//        int number = random.nextInt(2);
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("name", faker.name().fullName());
        mapBody.put("email", faker.internet().emailAddress());
        mapBody.put("status", ReusableMethods.RandomStatus());
        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(mapBody).
                when().
                post(endpoint);
       // response.prettyPrint();
        json = response.jsonPath();
        String message = json.getString("data.message");
       // System.out.println(message);
        Assert.assertEquals(message, "[can't be blank]");//koseli parantezi koymasam hata veriyordu

    }

    @Test
    public void TC_Post_0205() {
       // Random random = new Random();
//        String[] status = {"active", "inactive"};
//        String[] gender = {"male", "female"};
//        int number = random.nextInt(2);
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("name", faker.name().firstName());
        mapBody.put("email", faker.internet().emailAddress());
        mapBody.put("gender", ReusableMethods.RandomGender());
        mapBody.put("status", ReusableMethods.RandomStatus());

        response = given().contentType(ContentType.JSON).
                auth().oauth2(token).
                body(mapBody).
                when().
                post(endpoint);
        json = response.jsonPath();
        response.prettyPrint();
        System.out.println(mapBody.get("name"));
        System.out.println(json.getString("data.name"));
        Assert.assertEquals(json.getString("data.name"), mapBody.get("name"));
        Assert.assertEquals(json.getString("data.email"), mapBody.get("email"));
        Assert.assertEquals(json.getString("data.gender"), mapBody.get("gender"));
        Assert.assertEquals(json.getString("data.status"), mapBody.get("status"));
    }

}

