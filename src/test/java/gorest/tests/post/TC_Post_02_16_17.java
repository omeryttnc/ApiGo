package gorest.tests.post;

import com.github.javafaker.Faker;
import gorest.utilities.ReusableMethods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TC_Post_02_16_17 {

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

    @Test //new data creation with (name,email,NULL gender,status)
    public void TC_02_16() {
        postMap.put("name",faker.name().fullName());
        postMap.put("email",faker.internet().emailAddress());
        postMap.put("gender",null);
        postMap.put("status", ReusableMethods.RandomStatus());

        postMethod(postMap);
        json = response.jsonPath();
        response.prettyPrint();

        String message = json.getString("data.message");
        String field = json.getString("data.field");
        System.out.println("field = " + field);
        Assert.assertEquals(message,"[can't be blank]");
        Assert.assertEquals(field,"[gender]");
    }

    @Test // new data creation with (name,email,gender,NULL status)
    public void TC_02_17() {
        postMap.put("name",faker.name().fullName());
        postMap.put("email",faker.internet().emailAddress());
        postMap.put("gender",ReusableMethods.RandomGender());
        postMap.put("status", null);

        postMethod(postMap);
        json = response.jsonPath();
        response.prettyPrint();

        String message = json.getString("data.message");
        String field = json.getString("data.field");
        System.out.println("field = " + field);
        Assert.assertEquals(message,"[can't be blank]");
        Assert.assertEquals(field,"[status]");
    }

    }

