package gorest.tests.put;

import com.github.javafaker.Faker;
import gorest.pojos_lombok.Data;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

public class put_patch {
    public static class TC_01_02_03_Put{
        Response response;
        String endPoint="https://gorest.co.in/public-api/users/100";
        String token="5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";
        Map<String,Object> putMap = new HashMap<>();
        JsonPath json;
        Faker faker = new Faker();

        public void  putMethod(Map putBody){
            response= given().contentType(ContentType.JSON).auth().oauth2(token).body(putMap).when().put(endPoint);
            response.then().assertThat().statusCode(200);
            Assert.assertEquals(response.statusCode(),200);
        }
        @Test
        public void TC_01() {
            putMap.put("name", "Sarah");
            putMap.put("email", "Sarah@gmail.com");
            putMap.put("gender", "female");
            putMap.put("status", "active");

            putMethod(putMap);
            response.prettyPrint();
            json= response.jsonPath();

            Assert.assertEquals(json.get("data.name"),putMap.get("name"));
            Assert.assertEquals(json.get("data.email"),putMap.get("email"));
            Assert.assertEquals(json.get("data.gender"),putMap.get("gender"));
            Assert.assertEquals(json.get("data.status"),putMap.get("status"));



        }
        @Test
        public void Tc_02() {
            putMap.put("email", "Sarah@gmail.com");
            putMap.put("gender", "female");
            putMap.put("status", "active");

            putMethod(putMap);
            response.prettyPrint();
            json= response.jsonPath();
        }



    }


}
