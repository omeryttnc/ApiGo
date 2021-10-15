package gorest.utilities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import gorest.pojos.ApiGo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReusableMethods {
    public static JsonPath json;
    public static Response response;
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static ApiGo apiGo;

    public static String endPoint="https://gorest.co.in/public-api/users/";
    public static String token="5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";

    public static Response getresponse(String url) {
        response = given().
                accept(ContentType.JSON).
                when().
                get(url);
        // response.prettyPrint();
        json = response.jsonPath();
        return response;
    }


    public static Response getResponsePojo(String url) throws JsonProcessingException {
        response = given().
                accept(ContentType.JSON).
                when().
                get(url);
        // response.prettyPrint();
        apiGo = objectMapper.readValue(response.asString(), ApiGo.class);
        return response;
    }

    public static void postMethod(Map body) {
        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(body).
                when().
                post(endPoint);
        response.prettyPrint();
    }

}