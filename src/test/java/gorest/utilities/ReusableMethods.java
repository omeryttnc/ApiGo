package gorest.utilities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.pojos.ApiGo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

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
        json=response.jsonPath();
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
}
