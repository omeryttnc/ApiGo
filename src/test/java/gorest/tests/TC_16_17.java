package gorest.tests;
import com.fasterxml.jackson.core.JsonProcessingException;
import gorest.pojos.ApiGo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
public class TC_16_17 {


    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;  //De-serialization
    ApiGo apiGo;

    @BeforeMethod
    public void setup() throws JsonProcessingException {
        response = given().
                accept(ContentType.JSON).
                when().
                get(endpoint);
          json = response.jsonPath();


    }

    //search for email("aliveli@gmail.com")assertion
    @Test
    public void TC16() {
        int totalPage = response.path("meta.pagination.pages");
        int controlCode = 0;

        for (int i = 1; i <= totalPage; i++) {
            response = given().queryParam("page", i)
                    .when().get(endpoint);

            json = response.jsonPath();

            List<String> emailList = json.getList("data.email");
            System.out.println(emailList);
            String searchEmail = "aliveli@gmail.com";

            for (int j = 0; j < emailList.size(); j++) {
                if (emailList.get(j).equals(searchEmail)) {
                    System.out.println(i + " .page, " + j + " index name is " + searchEmail);
                    controlCode++;
                }
            }
        }
        Assert.assertEquals(controlCode, 0);
    }

    //count emails "@gmail.com" assertion
    @Test
    public void TC17() {
        int totalPage = response.path("meta.pagination.pages");

        List<String> emailList = json.getList("data.email");
        String searchEmail = "@gmail.com";
        int count = 0;

        for (int i = 1; i < totalPage; i++) {
            response = given().queryParam("page", i)
                    .when().get(endpoint);
            json = response.jsonPath();


            for (int j = 0; j < emailList.size(); j++) {
                if (emailList.get(j).contains(searchEmail)) {
                    System.out.println(i + " . page, " + j + " . index email is " + searchEmail);
                    count++;
                }
            }
        }
        System.out.println(count);
        Assert.assertNotEquals(count, 15);


    }
}
