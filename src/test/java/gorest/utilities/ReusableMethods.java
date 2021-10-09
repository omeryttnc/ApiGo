package gorest.utilities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.pojos.ApiGo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class ReusableMethods {
    public static JsonPath json;
    public static Response response;
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static ApiGo apiGo;


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
    public static String RandomStatus(){
        Random random = new Random();
        String[] status = {"active", "inactive"};
        int number = random.nextInt(2);
        return status[number];
    }
    public static String RandomGender(){
        Random random = new Random();
        String[] gender = {"male", "female"};
        int number = random.nextInt(2);  //0 1
        return gender[number];
    }
    public static List<Integer> getId()  {
        Response response;
        JsonPath jsonPath;
        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigurationReader.getProperty("token")).
                when().get(ConfigurationReader.getProperty("endPoint"));
        jsonPath = response.jsonPath();
        return jsonPath.getList("data.id");
    }

}