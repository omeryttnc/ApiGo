package gorest.tests.delete;

import com.github.javafaker.Faker;
import com.sun.tools.jxc.ConfigReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TC01 {

    Response response;
    String endPoint="https://gorest.co.in/public-api/users/";
    String token="5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";
    JsonPath jsonPath;


    public void deleteMethod(int queryId){
        response = given().
                accept(ContentType.JSON).
                auth().oauth2(token).
                when().
                delete(endPoint+queryId);
        response.prettyPrint();
    }
    public void getMethod(int queryId){
        response = given().accept(ContentType.JSON).when().get(endPoint+queryId);
        response.prettyPrint();
    }


    //herhangi bir id ye gore silelim
    @Test
    public void TC01() {
        response = given().accept(ContentType.JSON).when().get(endPoint);
        jsonPath = response.jsonPath();
        deleteMethod(99);
        jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.getString("data"),null);
        Assert.assertEquals(jsonPath.getString("meta"),null);
        Assert.assertEquals(jsonPath.getInt("code"),204);
        getMethod(99);
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("data.message"),"Resource not found");

    }
}
