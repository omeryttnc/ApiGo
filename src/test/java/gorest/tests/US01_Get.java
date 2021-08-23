package gorest.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class US01_Get {

    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;

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


        //  response.prettyPrint();
        // response.prettyPeek();
        // System.out.println(response.statusCode());
        json = response.jsonPath();

    }

    //TC_0101 status code assertion  ,//TC_0102 content-type assertion
    @Test
    public void TC01_02() {
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
    }

    //all data count assertion
    @Test
    public void TC03() {
        int total = json.getInt("meta.pagination.total");
        System.out.println(total);

        Assert.assertNotEquals(total, "2000");
        //assertion2
        // response.then().assertThat().body("meta.pagination.total", Matchers.equalTo(200));//not equals kullanamiyoruz

        //Matchers classinin importunu "import static org.hamcrest.Matchers.*;"
        // olarak duzenlersek her seferinde Matchers kullanmayabiliriz

    }

    //all page assertion
    @Test
    public void TC04() {
        int pageSize = json.getInt("meta.pagination.pages");
        System.out.println(pageSize);
        Assert.assertNotEquals(pageSize, 20);

        //response.then().assertThat().body("meta.pagination.pages",equalTo(20));//not equals kullanamiyoruz
    }
}