package gorest.tests.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import gorest.pojos.ApiGo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Deneme {

    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;
    ApiGo apiGo;
    ObjectMapper objectMapper=new ObjectMapper();
    String token ="5d04fe08a73c74ff19ad6559ca5c4933457919bd915272fc0b55c0c8933f0783";
    Faker faker=new Faker();

    public void postMethod(String gender,String status){
        Map<String,Object> mapBody=new HashMap<>();
        mapBody.put("name",faker.name().fullName());
        mapBody.put("email",faker.internet().emailAddress());
        mapBody.put("gender",gender);
        mapBody.put("status",status);

        response=given().contentType(ContentType.JSON).
                auth().oauth2(token).
                body(mapBody).
                when().
                post(endpoint);
        json=response.jsonPath();
        response.prettyPrint();
    }

    @Test
    public void TC01() {

        String body1=" {\n" +
                " \n" +
                "  \"name\": \"Aysee\",\n" +
                "  \"email\": \"alicaznvezzEzy@grrr\",\n" +
                "  \"gender\": \"male\",\n" +
                "   \"status\": \"INACTIVE\"\n" +
                "  \n" +
                " }";

        response=given().
                contentType(ContentType.JSON).
                auth().oauth2(token).
                body(body1).
                when().
                post(endpoint);

        response.prettyPrint();

    }

    @Test
    public void TC02() {
       postMethod("female","inactive");
        //json=response.jsonPath();
        //String email=json.getString("data.email");
       // System.out.println(email);

    }

    @Test
    public void TC03() {
        Map<String,Object> mapBody=new HashMap<>();
        mapBody.put("name",faker.name().firstName());
        mapBody.put("email",faker.internet().emailAddress());
        mapBody.put("gender","male");
        mapBody.put("status","active");

        response=given().contentType(ContentType.JSON).
                auth().oauth2(token).
                body(mapBody).
                when().
                post(endpoint);
        json=response.jsonPath();
        response.prettyPrint();

//        $gender = $faker->randomElement(['male', 'female']);
//
//        return [
//        'name' => $faker->name($gender),
//                'email' => $faker->safeEmail,
//                'username' => $faker->userName,
//                'phone' => $faker->phoneNumber,
//                'gender' => $gender,
//                'address' => $faker->address,
//                'dob' => $faker->date($format = 'Y-m-d', $max = 'now'),
//                'password' => bcrypt('secret')
//];
    }

}
