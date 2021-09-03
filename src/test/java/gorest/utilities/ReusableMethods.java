package gorest.utilities;


import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReusableMethods {

    public static Response getResponse(String url) {
        Response response = given().
                accept(ContentType.JSON).
                when().
                get(url);
        // response.prettyPrint();

        return response;
    }


}