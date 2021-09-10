package gorest.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.pojos.ApiGo;
import gorest.pojos.Data;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TC_01_05_Pojo {

    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    ObjectMapper objectMapper = new ObjectMapper();
    ApiGo apiGoPojo;

    @BeforeMethod
    public void setup() throws JsonProcessingException {
        response = given().
                accept(ContentType.JSON).
                when().
                get(endpoint);
        response.
                then().
                assertThat().
                contentType(ContentType.JSON);

        apiGoPojo = objectMapper.readValue(response.asString(), ApiGo.class);
       // apiGoPojo = response.body().as(ApiGo.class);

    }

   //status code assertion
    @Test
    public void tc01() {
        int code=apiGoPojo.getCode();
        System.out.println(code);
    }

    //all data count assertion
    @Test
    public void tc03() {
        int total=apiGoPojo.getMeta().getPagination().getTotal();
        System.out.println(total);
        Assert.assertNotEquals(total, "1375");
    }

    //all page assertion
    @Test
    public void tc04() {

        int allPage=apiGoPojo.getMeta().getPagination().getPages();
        System.out.println(allPage);
        Assert.assertNotEquals(allPage,20);
    }


    //id natural order assertion
    @Test
    public void tc05() throws JsonProcessingException {

        List<Integer> allId=new ArrayList<>();

        int totalPage = apiGoPojo.getMeta().getPagination().getTotal();

        for (int i = 1; i <=totalPage ; i++) {

            response = given().queryParam("page", i)
                    .when().get(endpoint);

            apiGoPojo = objectMapper.readValue(response.asString(), ApiGo.class);


            for (Data w : apiGoPojo.getData()){
                allId.add(w.getId());
                System.out.println(w.getId());
            }


        }
        System.out.println("++++++++++");
        List<Integer> idList=new ArrayList<>(allId);
        Collections.sort(idList); ///natural order yapar
        System.out.println(idList);
        Assert.assertNotEquals(allId,idList);
}
}