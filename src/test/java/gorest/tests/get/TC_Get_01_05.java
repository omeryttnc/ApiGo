package gorest.tests.get;

import gorest.utilities.ReusableMethods;
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

public class TC_Get_01_05 {

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
                contentType(ContentType.JSON);

        //  response.prettyPrint();
        // response.prettyPeek();
        json = response.jsonPath();
        json_gender_List = json.getList("data.gender");
        totalPage = json.getInt("meta.pagination.pages");


    }

    //TC_0101 status code assertion  ,//TC_0102 content-type assertion
    @Test
    public void TC01_02() {
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
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

    //id natural order assertion
    @Test
    public void TC05() {

        int totalPage = response.path("meta.pagination.pages");

        for (int i = 1; i <= totalPage; i++) {

            response = given().queryParam("page", i)
                    .when().get(endpoint);

            JsonPath json = response.jsonPath();

            List<Integer> idList = json.getList("data.id");


            boolean check = true;

            for (int j = 0; j < idList.size() - 1; j++) {
                System.out.println("i " + idList.get(j) + " j " + idList.get(j + 1));
                if (idList.get(j) < idList.get(j + 1)) {//kucukten buyuge dogru siralanmiyorsa ilk false oldugunda donguyu kirar
                    check = false;
                    break;
                }
            }
            Assert.assertFalse(check);

        }

        /////2.yol Set ile
//        List<Integer> idList = json.getList("data.id");//orijinal data
//        System.out.println(idList);
//        List<Integer> idList2 = new ArrayList<>(idList);
//        Collections.sort(idList2); //natural order olduktan sonra
//        Assert.assertNotEquals(idList, idList2);

    }
}
