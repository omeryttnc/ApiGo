package gorest.tests.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import gorest.pojos.ApiGo;
import gorest.utilities.ExcelUtil;
import gorest.utilities.ReusableMethods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class aaa {

    Response response;
    String endPoint="https://gorest.co.in/public-api/users/";
    JsonPath json;
    ObjectMapper objectMapper=new ObjectMapper();
    ApiGo apigo;
    Map<String,Object> postMap = new HashMap<>();
    Faker faker=new Faker();
    String token="5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";

// * PostRequest olusturmak icin gerekenler:
//	 * 1-)Endpoint sart
//	 * 2-)Request Body sart  ((data verilmeli))
//	 * 3-)Authorization sart  ((acces izni))--(erisim izni)
//Authorization gerekirse de ayarlamamiz gerekir.erisim izni olmazsa yapamam
//contentType=icerik==data base e yolladigim datanin  icerigi json///icerik json olsun diyoruz

    public void postMethod(Map body){
       response =  given().
               contentType(ContentType.JSON).
               auth().oauth2(token).
               body(postMap).
               when().
               post(endPoint);
       response.prettyPrint();
   }


    @Test
    public void post1() {
        String post=" {\n" +
                "      \"name\": \"Pinn\",\n" +
                "            \"email\": \"pinn@15ce.com\",\n" +
                "            \"gender\": \"female\",\n" +
                "            \"status\": \"active\"\n" +
                " }";

        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(post).
                when().
                post(endPoint);
        json = response.jsonPath();
        response.prettyPrint();
    }

    @Test
    public void post2() {
        postMap.put("name","Alii");
        postMap.put("email","kjkhnvknvb@gm");
        postMap.put("gender","female");
        postMap.put("status","active");

        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(postMap).
                when().
                post(endPoint);
        response.prettyPrint();
    }
    @Test
    public void post3() {
       // postMap.put("id",1000000);
        postMap.put("name", "aaaa");
        postMap.put("email", "kaegxxm@px");
        postMap.put("gender", "male");
        postMap.put("status", "active");
        postMethod(postMap);
        response.prettyPrint();
       json= response.jsonPath();
       String name=json.getString("data.name");
        System.out.println(name);

    }
}