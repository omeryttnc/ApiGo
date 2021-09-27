package gorest.tests.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import gorest.pojos.ApiGo;
import gorest.utilities.ConfigurationReader;
import gorest.utilities.ReusableMethods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TC_01_02_03 {

    Response response;
    String endPoint="https://gorest.co.in/public-api/users/";
    JsonPath json;
    ObjectMapper objectMapper=new ObjectMapper();
    ApiGo apigo;
    Map<String,Object> postMap = new HashMap<>();
    Faker faker=new Faker();
    String token="5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";

    //yeni bir data oluşturun
    @Test
    public void TC01() {
        postMap.put("name",faker.name().firstName());
        postMap.put("email",faker.internet().emailAddress());
        postMap.put("gender","female");
        postMap.put("status","active");
        response =  given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(postMap).
                when().
                post(endPoint);
       json = response.jsonPath();
        response.prettyPrint();



    }


    //yeni bir data oluşturun/name eksik
    @Test
    public void TC02() throws JsonProcessingException {
        //apigo=objectMapper.readValue(response.asString(),ApiGo.class);
     ApiGo apigo=new ApiGo("ali","emell@g","mail","active");

    }

    //yeni bir data oluşturun/email eksik
    @Test
    public void TC03() {
    }

}
