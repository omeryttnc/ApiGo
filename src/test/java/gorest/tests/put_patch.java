package gorest.tests;

import com.github.javafaker.Faker;
import gorest.pojos.Data;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class put_patch {
    public static class TC_01_02_03_Put {

        Response response;
        String endPoint="https://gorest.co.in/public-api/users/100";
        String getEndPoint = "https://gorest.co.in/public-api/users/";
        String token="5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";
        Map<String,Object> putMap=new HashMap<>();
        JsonPath json;
        Faker faker=new Faker();

        public void putMethod(Map putBody){
            response=given().
                    contentType(ContentType.JSON).
                    auth().oauth2(token).
                    body(putBody).
                    when().
                    put(endPoint);
            response.then().assertThat().statusCode(200);
            Assert.assertEquals(response.statusCode(),200);
        }

        //data update (name=Sarah,email=Sarah@gmail.com,gender=Female,status=active)
        @Test
        public void TC01() {
            putMap.put("name","Sarah");
            putMap.put("email","Sarah@gmail.com");
            putMap.put("gender","female");
            putMap.put("status","active");

            putMethod(putMap);
            response.prettyPrint();
            json=response.jsonPath();

            Assert.assertEquals(json.getString("data.name"),putMap.get("name"));
            Assert.assertEquals(json.getString("data.email"),putMap.get("email"));
            Assert.assertEquals(json.getString("data.gender"),putMap.get("gender"));
            Assert.assertEquals(json.getString("data.status"),putMap.get("status"));
        }
        //data update (email,gender,status)
        @Test
        public void TC02() {
            putMap.put("email","Sarah123@gmail.com");
            putMap.put("gender","male");
            putMap.put("status","inactive");

            putMethod(putMap);
            response.prettyPrint();

            json=response.jsonPath();
            Assert.assertEquals(json.getString("data.email"),putMap.get("email"));
            Assert.assertEquals(json.getString("data.gender"),putMap.get("gender"));
            Assert.assertEquals(json.getString("data.status"),putMap.get("status"));

        }

       // data update (name,gender,status)
        @Test
        public void TC03() {
            String fakerName=faker.name().fullName();
         Data data=new Data(fakerName,"male","active");
            response=given().
                    contentType(ContentType.JSON).
                    auth().oauth2(token).
                    body(data).
                    when().
                    put(endPoint);
            response.then().assertThat().statusCode(200);
            response.prettyPrint();

            response = given().
                    accept(ContentType.JSON).
                    when().
                    get(getEndPoint);
            json=response.jsonPath();
            String nameControl=json.getString("data[100].name");
            System.out.println(nameControl);
           // Assert.assertEquals(nameControl,fakerName);
        }


    }
}
