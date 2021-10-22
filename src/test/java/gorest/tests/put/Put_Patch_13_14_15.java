package gorest.tests.put;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class Put_Patch_13_14_15{
    String endpoint="https://gorest.co.in/public-api/users/100";  //Dikkat id silinmis olabilir.
    String token="5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";
    Response response;
    JsonPath json;
    Map<String,Object> putMaph=new HashMap<>();

    public void putMethod(Map putBody){
        response= given()
                 .contentType(ContentType.JSON)
                 .auth().oauth2(token)
                 .body(putBody)
                 .when()
                 .patch(endpoint);
    }
    protected void update(String key,Object value){
        putMaph.put(key,value);
        putMethod(putMaph);
        response.prettyPrint();
        json=response.jsonPath();

        String actualMessage=json.getString("data.field")+json.getString("data.message");
        String expectedMessage="["+key+"][can't be blank]";
        Assert.assertEquals(actualMessage,expectedMessage);


    }

    @Test  //data update  with wrong type of status
    public void TC_13(){
//
//            putMaph.put("status", "pasif");
//            putMethod(putMaph);
//            response.prettyPrint();
//            json=response.jsonPath();
//
//            String actualMessage=json.getString("data.field")+json.getString("data.message");
//            String expectedMessage="[status][can't be blank]";
//            Assert.assertEquals(actualMessage,expectedMessage);
        update("status","pasif");

    }
    @Test  //data update (NULL name)
    public void TC_14(){

           update("name",null);

    }
    @Test  //data update  (NULL email)
    public void TC_15(){

        update("email",null);
        }






}
