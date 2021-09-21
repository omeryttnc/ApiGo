package gorest.tests;

import gorest.utilities.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.*;

import static io.restassured.RestAssured.*;


public class TC_Get_10_11 extends TestBase {

    Response response;
    String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;
    int json_code;
    int json_allPages;
    int json_allIds;
    List<Integer> json_idList;
    List<String> json_name_List;
    List<String> json_gender_List;


    //Burada obje olusturuyorsun List veya int objeleri


    @BeforeMethod //TC_0101 status code assertion
    public void setup() {

        response = given().
                spec(spec01).
                accept(ContentType.JSON).
                when().
                get();
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);

        // response.prettyPrint();
        // response.prettyPeek();
        json = response.jsonPath();
        json_code = json.getInt("code");
        json_allPages = json.getInt("meta.pagination.pages");
        json_idList = json.getList("data.id");
        json_name_List = json.getList("data.name");
        json_gender_List = json.getList("data.gender");
        //yukardaki objelere değer atadık


    }

    @Test
    //Bu TC de sadece duplicate isimleri veriyor (id lerle birlikte gelmiyor * Not: Get_Pojo_10 da idlerle birlikte geliyor
    public void getTc110() {
        List<String> allNames = new ArrayList<>();



        for (int i = 1; i <= 3; i++) {      //burada her sayfadaki 20 er sayfa sayisini i ye atadik
            spec01.queryParam("page", i); // i 1,2 ... artarak sayfalari cekiyor
            response = given(). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    spec(spec01).
                    when().get();


            json = response.jsonPath();
            json_name_List = json.getList("data.name");
            json_idList = json.getList("data.id");

            System.out.println("page::::::::: " + i);
            allNames.addAll(json_name_List);

        }
                Collections.sort(allNames);
                for (int j = 1; j < allNames.size() - 1; j++) {
                    if (allNames.get(j).equals(allNames.get(j + 1))) {
                        System.out.println(" first name : " + allNames.get(j) + " second name : " + allNames.get(j + 1));
                    }
                }
        }



    @Test //more females assertion (female sayısı daha mı fazla)
    public void get111() {

        int countFemale = 0;
        int countMale = 0;
        int countNotr = 0;
        for (int i = 1; i <= json_allPages; i++) {
            response=given().queryParam("page", i). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    when().get(endPoint);

            json = response.jsonPath();
            json_gender_List = json.getList("data.gender");
            for (String gender : json_gender_List) {
                if (gender.equals("female")) {
                    countFemale++;
                } else if (gender.equals("male")) {
                    countMale++;
                } else {
                    countNotr++;
                }
            }
         }
        System.out.println("female:" + countFemale);
        System.out.println("male:" +countMale);
        System.out.println("notr:" +countNotr);
//        Assert.assertEquals(countFemale, countMale);
        Assert.assertFalse(countFemale>countMale && countFemale<countNotr);


    }

}


