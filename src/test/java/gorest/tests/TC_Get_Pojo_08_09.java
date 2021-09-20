package gorest.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.pojos.ApiGo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TC_Get_Pojo_08_09 {

    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";

    JsonPath json;  //de-serialization
    //List<String> json_gender_List;
    int totalPage;
    ObjectMapper objectMapper = new ObjectMapper();
    ApiGo apiGoPojo;
    List<String> gender_List_Male = new ArrayList<>();
    List<String> gender_List_Female = new ArrayList<>();


    @BeforeMethod
    public void setup() throws JsonProcessingException {
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
        // json_gender_List = json.getList("data.gender");
        // totalPage = json.getInt("meta.pagination.pages");
        // response.prettyPrint();
        apiGoPojo = objectMapper.readValue(response.asString(), ApiGo.class);

    }


    @Test //number of males and females assertion
    public void TC108_TC109() {

        int totalPage_Pojo = apiGoPojo.getMeta().getPagination().getPages();
        for (int i = 0; i <= totalPage_Pojo; i++) {
            response = given().queryParam("page", i)
                    .when().get(endpoint);
            for (int j = 0; j < apiGoPojo.getData().size(); j++) {
                if (apiGoPojo.getData().get(j).getGender().equals("male")) {
                    gender_List_Male.add(apiGoPojo.getData().get(j).getGender());
                } else if (apiGoPojo.getData().get(j).getGender().equals("female")) {
                    gender_List_Female.add(apiGoPojo.getData().get(j).getGender());
                }
            }
            System.out.println(i+1 + ".page male : " + gender_List_Male.size() + " and female is : " + gender_List_Female.size());
        }
        System.out.println("final male : " + gender_List_Male.size() + " and female is : " + gender_List_Female.size());

        Assert.assertNotEquals(gender_List_Male.size(), 483);
        Assert.assertNotEquals(gender_List_Female.size(), 897);

    }
}
