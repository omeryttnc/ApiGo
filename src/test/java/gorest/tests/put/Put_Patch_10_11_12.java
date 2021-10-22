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

public class Put_Patch_10_11_12{
    String endpoint="https://gorest.co.in/public-api/users";
    String token="127e45713e989102f2953435d4eb235fdf2eb8cbd8253ee59066e964e3c27bcd";
    Response response;
    JsonPath json;
    Map<String,Object> putMaph=new HashMap<>();
    List<Object> names=new ArrayList<>();
    List<Object> emails=new ArrayList<>();
    List<String> genders=new ArrayList<>();
    String idNo="/100";
    Faker faker=new Faker();

    public void putMethod(Map putBody){
        response= given().contentType(ContentType.JSON).auth().oauth2(token).body(putBody).when().put(endpoint+idNo);
    }

    @Test  //data update with wrong type of name
    public void TC_10(){
        names.add(986798);
        names.add("7856");
        names.add(faker.name().fullName());
        names.add('L');
        for (int i = 0; i < 4; i++) {
            putMaph.put("name",names.get(i));
            putMethod(putMaph);
            response.prettyPrint();
            json=response.jsonPath();
            Assert.assertEquals(json.getString("code"),"200");

        }


    }
    @Test  //data update with wrong type of email
    public void TC_11(){
        emails.add("gmail.com");
        emails.add("klm@");
        emails.add("a@.com");
        emails.add("@gmail.com");
        emails.add("k.gmail.com");
        for (int i = 0; i < 5; i++) {
            putMaph.put("email",emails.get(i));
            putMethod(putMaph);
            response.prettyPrint();
            json=response.jsonPath();
            String actualMessage=json.getString("data.field")+json.getString("data.message");
            String expectedMessage="[email][is invalid]";

            Assert.assertEquals(actualMessage,expectedMessage);

        }



    }
    @Test  //data update  with wrong type of gender
    public void TC_12(){
        genders.add("f");
        genders.add("m");
        for (int i = 0; i <2 ; i++) {
            putMaph.put("gender",genders.get(i));
            putMethod(putMaph);
            response.prettyPrint();
            json=response.jsonPath();
            String actualMessage=json.getString("data.field")+json.getString("data.message");
            String expectedMessage="[gender][can't be blank]";

            Assert.assertEquals(actualMessage,expectedMessage);
        }


    }



        }
