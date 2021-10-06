package gorest.tests.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import gorest.pojos.ApiGo;
import gorest.pojos.Data;
import gorest.utilities.ReusableMethods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TC_Post_Pojo_13_14_15 {

    Response response;
    String endPoint="https://gorest.co.in/public-api/users/";
    JsonPath json;
    Faker faker=new Faker();
    String token="5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";


    @Test
    public void TC13() { //new data creation with (name,email,gender,status) with wrong type of status

       Data data = new Data(faker.name().fullName(),faker.internet().emailAddress(), ReusableMethods.RandomGender(),"??");

        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(data).
                when().
                post(endPoint);
        response.prettyPrint();
        json= response.jsonPath();

       String message = json.getString("data.message");
       String field=json.getString("data.field");

        Assert.assertEquals(message, "[can't be blank]");
        Assert.assertEquals(field,"[status]");

        System.out.println(field +" bolumu mesaji : " +message);

    }
    @Test
    public void TC14() { //new data creation with (NULL name,email,gender,status)

        Data data=new Data(null,faker.internet().emailAddress(), ReusableMethods.RandomGender(),ReusableMethods.RandomStatus());

        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(data).
                when().
                post(endPoint);
        response.prettyPrint();
        json= response.jsonPath();

        String message = json.getString("data.message");
        String field=json.getString("data.field");

        Assert.assertEquals(message, "[can't be blank]");
        Assert.assertEquals(field,"[name]");

        System.out.println(field +" bolumu mesaji : " +message);

    }
    @Test
    public void TC15() { //new data creation with (name,NULL email,gender,status)

        Data data=new Data(faker.name().fullName(),null, ReusableMethods.RandomGender(),ReusableMethods.RandomStatus());

        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(data).
                when().
                post(endPoint);
        response.prettyPrint();
        json= response.jsonPath();

        String message = json.getString("data.message");
        String field=json.getString("data.field");

        Assert.assertEquals(message, "[can't be blank]");
        Assert.assertEquals(field,"[email]");

        System.out.println(field +" bolumu mesaji : " +message);

    }
}
