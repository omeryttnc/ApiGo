package gorest.tests.update;

import com.github.javafaker.Faker;
import gorest.pojos_lombok.Data;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;

class Put {
    public static class TC_03_16_17_Put {
        Response response;
        String endPoint = "https://gorest.co.in/public-api/users/43";
        String token = "5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";
        Map<String, Object> putMap = new HashMap<>();
        JsonPath json;
        Faker faker = new Faker();

        public void putMethod(Map putBody) {
            response = given().contentType(ContentType.JSON).auth().oauth2(token).body(putMap).when().put(endPoint);
            //response.then().assertThat().statusCode(200);
            //Assert.assertEquals(response.statusCode(), 200);
        }

        @Test
        public void TC_16_17() {

            putMap.put("gender", null);
            putMap.put("status", "");

            putMethod(putMap);
            response.prettyPrint();
            json = response.jsonPath();

            System.out.println(json.getString("data.field"));
            System.out.println(json.getString("data.message"));
            List<String> expectedField = Arrays.asList("gender", "status");
            System.out.println("expectedField = " + expectedField);
            List<String> expectedMessage = Arrays.asList("can't be blank", "can't be blank");
            System.out.println("expectedMessage = " + expectedMessage);
            Assert.assertEquals(json.get("data.field"),expectedField);
            //Assert.assertTrue(expectedField.stream().equals(t->t.equals(json.getString("data.field"))));



            //Assert.assertEquals(json.get("data.field"),putMap.get("gender"));
            //Assert.assertEquals(json.get("data.status"),putMap.get("status"));

        }

    }
}


