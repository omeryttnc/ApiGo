package gorest.tests.get;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TC_Get_08_09 {

    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";

    JsonPath json;  //de-serialization
    List<String> json_gender_List;
    int totalPage;




    @BeforeMethod
    public void setup() {
        response = given().
                accept(ContentType.JSON).
                when().
                get(endpoint);
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        json = response.jsonPath();
        json_gender_List = json.getList("data.gender");
        totalPage = json.getInt("meta.pagination.pages");



    }


    @Test //number of males assertion
    public void TC108() {
        int count = 0;
        for (int i = 0; i < totalPage; i++) {
            given().queryParam("page,i").when().get(endpoint);
            for (String gender : json_gender_List
            ) {
                if (gender.equals("male")) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Test //number of females assertion
    public void TC109() {
        int count = 0;
        for (int i = 0; i < totalPage; i++) {
            given().queryParam("page,i").when().get(endpoint);
            for (String gender : json_gender_List
            ) {
                if (gender.equals("female")) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }








}
