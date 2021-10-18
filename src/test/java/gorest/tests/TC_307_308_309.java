package gorest.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class TC_307_308_309 {

    Response response;
    Map<String, Object> singleData = new HashMap<>();
    String endPoint = "https://gorest.co.in/public-api/users/";
    String token = "84c9d62cb15722fb012abd073aaf715db196438c9559a23e80e26fafda059476";
    JsonPath json;

    public void patchMethod(Map map) {
        response = given()
                .contentType(ContentType.JSON).and().auth().oauth2(token)
                .and().body(map)
                .when().patch(endPoint + "101");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
    }


    @Test
    public void tc_307() {

        singleData.put("email", "galatasaray@gmail.com");

        patchMethod(singleData);

        json = response.jsonPath();

        response.prettyPrint();

        String expected = (String) singleData.get("email");
        String actual = json.getString("data.email");
        System.out.println("actual = " + actual);

        assertEquals(actual, expected);

    }

    @Test
    public void tc_308() {

        singleData.put("gender", "female");

        patchMethod(singleData);

        json = response.jsonPath();

        response.prettyPrint();

        String expected = (String) singleData.get("gender");
        String actual = json.getString("data.gender");
        System.out.println("actual = " + actual);

        assertEquals(actual, expected);

    }
    @Test
    public void tc_309() {

        singleData.put("status", "inactive");

        patchMethod(singleData);

        json = response.jsonPath();

        response.prettyPrint();

        String expected = (String) singleData.get("status");
        String actual = json.getString("data.status");
        System.out.println("actual = " + actual);

        assertEquals(actual, expected);
    }

    @Test
    public void deleteData(){

        singleData.put("email","chakradhar_iyengar@ortiz.name");
        response = given()
                .contentType(ContentType.JSON).and().auth().oauth2(token)
                .and().body(singleData)
                .when().delete(endPoint + "101");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        json= response.jsonPath();

        String actualmessage=json.getString("data.message");
        assertEquals(actualmessage,"Resource not found");

    }
}
