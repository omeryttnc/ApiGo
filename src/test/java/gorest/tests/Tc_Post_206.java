package gorest.tests;

import gorest.pojos.Data;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

public class Tc_Post_206 {

    Response response;
    Map<String,String> name=new HashMap<>();
    String token="84c9d62cb15722fb012abd073aaf715db196438c9559a23e80e26fafda059476";
    String endPoint="https://gorest.co.in/public-api/users/";
    JsonPath json;


    public void postNew(Map<String,String> data){

        response= RestAssured.given().contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(name)
                .when().post(endPoint);



    }
    @Test
    public void tc206(){
        name.put("name","Can");

    //    postNew(name);

        response= RestAssured.given().contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(name)
                .when().post(endPoint);

        json=response.jsonPath();
        response.prettyPrint();


        String expectedMessage="can't be blank";



        List<String> result= json.getList("data.message");
        System.out.println("result.size() = " + result.size());

        for (String s : result) {
            Assert.assertEquals(s,expectedMessage);
            System.out.println(s);
        }

        int actualCode=json.getInt("code");
        System.out.println("actualCode = " + actualCode);
        int expectedCode=422;
        System.out.println(response.statusCode());
        Assert.assertEquals(actualCode,expectedCode);

    }

    @Test
    public void tc_207(){
        name.put("email","erk@sah.com");
        postNew(name);

        json=response.jsonPath();
        response.prettyPrint();

        String expectedMessage="can't be blank";

        List<String> result= json.getList("data.message");
        System.out.println("result.size() = " + result.size());

        for (String s : result) {
            Assert.assertEquals(s,expectedMessage);
            System.out.println(s);
        }

        int actualCode=json.getInt("code");
        System.out.println("actualCode = " + actualCode);
        int expectedCode=422;

        Assert.assertEquals(actualCode,expectedCode);

    }

    @Test
    public void tc208(){
        Data data= new Data("male");
        
        response= RestAssured.given().contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(data)
                .when().post(endPoint);

        json=response.jsonPath();
        response.prettyPrint();

        String expectedMessage="can't be blank";

        List<String> result= json.getList("data.message");

        //lambda ile cozum
        System.out.println((result.stream().allMatch(t->t.equals(expectedMessage))));


        //2.yol

//        Set<String> setResult= new HashSet<>(result);
//        System.out.println("setResult.size() = " + setResult.size());
//        System.out.println("result.size() = " + result.size());
//
        List<String> field= json.getList("data.field");

        System.out.println(field.stream().noneMatch(t->t.equals("gender")));
        field.forEach(System.out::println);
//
//        for (String s : field) {
//            System.out.println("s = " + s);
//        }
//
//      Assert.assertEquals(setResult.toString(),expectedMessage);
//
//        int actualCode=json.getInt("code");
//        System.out.println("actualCode = " + actualCode);
//        int expectedCode=422;
//
//        Assert.assertEquals(actualCode,expectedCode);

    }


    }

