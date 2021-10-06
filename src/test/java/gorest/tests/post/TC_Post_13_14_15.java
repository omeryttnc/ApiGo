package gorest.tests.post;

import com.github.javafaker.Faker;
import gorest.utilities.ReusableMethods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TC_Post_13_14_15 {

    Response response;
    String endPoint="https://gorest.co.in/public-api/users/";
    JsonPath json;
    Map<String, Object> mapBody = new HashMap<>();
    Faker faker=new Faker();
    String token="5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";

    @Test  //new data creation with (name,email,gender,status) with wrong type of status
    public void TC_Post_13() {

        mapBody.put("name", faker.name().fullName());
        mapBody.put("email", faker.internet().emailAddress());
        mapBody.put("gender", ReusableMethods.RandomGender());
        mapBody.put("status","Aktif");
        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(mapBody).
                when().
                post(endPoint);
        // response.prettyPrint();
        json = response.jsonPath();
        String message = json.getString("data.message");
        String field=json.getString("data.field");
        System.out.println(field +" bolumu mesaji : " +message);

        Assert.assertEquals(message, "[can't be blank]");
        Assert.assertEquals(field,"[status]");

        System.out.println(json.getString("data.name"));
        System.out.println(json.getString("data.email"));
        System.out.println(json.getString("data.gender"));
        System.out.println(json.getString("data.status"));

    }
    @Test  //new data creation with (NULL name,email,gender,status)
    public void TC_Post_14() {

        mapBody.put("name", "");
        mapBody.put("email", faker.internet().emailAddress());
        mapBody.put("gender", ReusableMethods.RandomGender());
        mapBody.put("status",ReusableMethods.RandomStatus());
        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(mapBody).
                when().
                post(endPoint);
        // response.prettyPrint();
        json = response.jsonPath();
        String message = json.getString("data.message");
        String field=json.getString("data.field");
        System.out.println(field +" bolumu mesaji : " +message);

       Assert.assertEquals(message, "[can't be blank]");
       Assert.assertEquals(field,"[name]");

    }
    @Test //new data creation with (name,NULL email,gender,status)
    public void TC_Post_15() {

        mapBody.put("name", faker.name().fullName());
        mapBody.put("email"," ");
        mapBody.put("gender", ReusableMethods.RandomGender());
        mapBody.put("status",ReusableMethods.RandomStatus());
        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(mapBody).
                when().
                post(endPoint);
        // response.prettyPrint();
        json = response.jsonPath();

        String message = json.getString("data.message");
        String field=json.getString("data.field");
        System.out.println(field +" bolumu mesaji : " +message);

        Assert.assertEquals(message, "[can't be blank]");
        Assert.assertEquals(field,"[email]");

    }

}
