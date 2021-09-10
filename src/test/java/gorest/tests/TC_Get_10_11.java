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
import static org.testng.Assert.assertTrue;

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

//        response.prettyPrint();
        // response.prettyPeek();
        json = response.jsonPath();
        json_code = json.getInt("code");
        json_allPages = json.getInt("meta.pagination.pages");
        json_idList = json.getList("data.id");
        json_name_List = json.getList("data.name");
        json_gender_List = json.getList("data.gender");
        //yukardaki objelere değer atadık
        //RestAssured.baseURI = ConfigurationReader.get("goRest.uri");

    }

    @Test //give duplicate names with their ids
    public void getTc110() {


        List<Map<String, Object>> users = new ArrayList<>(); //once bos liste olusturdum

        System.out.println("Total Page: " + json_allPages); // sayfanin basindaki all pagesdan aliyor

        for (int i = 1; i <= json_allPages; i++) {      //burada her sayfadaki 20 er sayfa sayisini i ye atadik

            spec01.queryParam("page", i); // i 1,2 ... artarak sayfalari cekiyor

            response = given(). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    spec(spec01).
                    when().get();
            System.out.println("page::::::::: "+i); //104

            json = response.jsonPath();
            json_name_List = json.getList("data.name");
            json_idList = json.getList("data.id");
            for (int j=0; j < json_name_List.size(); j++) {

                Map<String, Object> user = new HashMap<>();        // bu ikinci Map ismin üzerine yazmaması için
                user.put("name", json_name_List.get(j));           // bu listi olusturdugumuz bos "user" listin icine koyduk.
                user.put("id", json_idList.get(j));

                //System.out.println("users = " + user);
                users.add(user);
            }
            Set<Map<String, Object>> store = new HashSet<>();

            for (Map<String, Object> name : users) {
                if (store.add(name) == false) {
                    System.out.println("found a duplicate element in array : " + users);

                } else {
                    System.out.println("No duplicate names");
                }

            }
        }
        //System.out.println("users = " + users);


    }
    @Test //more females assertion (female sayısı daha mı fazla)
    public void get111() {

        int countFemale = 0;
        int countMale = 0;
        for (int i = 0; i < json_allPages; i++) {
            given().queryParam("page", i). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    when().get(endPoint);
            for (String genderF : json_gender_List) {
                if (genderF.equals("female")) {
                    countFemale++;
                }
            }
            for (String genderM : json_gender_List) {
                if (genderM.equals("male")) {
                        countMale++;
                }
            }

        }
        System.out.println("female:" + countFemale);
        System.out.println("male:" +countMale);
        Assert.assertEquals(countFemale, countMale);
//        Assert.assertTrue(countFemale>countMale);


    }

}


