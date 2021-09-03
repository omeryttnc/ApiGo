package gorest.utilities;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReusableMethods {
    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;

    public static Response getresponse(String url) {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get(url);
        // response.prettyPrint();

        return response;
    }

    public static Response getResponse(String url) {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get(url);
        // response.prettyPrint();

        return response;
    }


}