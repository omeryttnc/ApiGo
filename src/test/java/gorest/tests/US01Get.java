package gorest.tests;

import gorest.utilities.ConfigurationReader;
import gorest.utilities.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

public class US01Get extends TestBase {

    Response response;
    //String endPoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;
    int json_code;
    int json_allPages;
    List<Integer> json_idList;
    List<String> json_name_List;
    List<String> json_gender_List;
    int json_totalPage;
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

        response.prettyPrint();
        // response.prettyPeek();
        json = response.jsonPath();
        json_code = json.getInt("code");
        json_allPages = json.getInt("meta.pagination.pages");
        json_idList = json.getList("data.id");
        json_name_List = json.getList("data.name");
        json_gender_List = json.getList("data.gender");
        //yukardaki objelere değer atadık


    }

    @Test //TC_0102 content-type assertion
    public void TC0102() {
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
    }

    @Test //all data count assertion
    public void TC103() {
        int total = json.getInt("meta.pagination.total");
        System.out.println(total);

        Assert.assertNotEquals(total, "2000");
        //assertion2
        // response.then().assertThat().body("meta.pagination.total", Matchers.equalTo(200));//not equals kullanamiyoruz

        //Matchers classinin importunu "import static org.hamcrest.Matchers.*;"
        // olarak duzenlersek her seferinde Matchers kullanmayabiliriz

    }

    @Test //all page assertion
    public void TC104() {
        int pageSize = json.getInt("meta.pagination.pages");
        System.out.println(pageSize);
        Assert.assertNotEquals(pageSize, 20);

        //response.then().assertThat().body("meta.pagination.pages",equalTo(20));//not equals kullanamiyoruz
    }

    @Test //id natural order assertion
    public void TC105() {

        List<Integer> idList = json.getList("data.id");
//      System.out.println(idList);
        boolean check = true;

        for (int i = 0; i < idList.size() - 1; i++) {
            System.out.println("i " + idList.get(i) + " j " + idList.get(i + 1));
            if (idList.get(i) > idList.get(i + 1)) {//kucukten buyuge dogru siralanmiyorsa ilk false oldugunda donguyu kirar
                check = false;
                break;
            }
        }
        Assert.assertFalse(check);


        /////2.yol Set ile

        List<Integer> idList2 = new ArrayList<>(idList);
        Collections.sort(idList2); //dogal siralama yapar
        Assert.assertNotEquals(idList, idList2);
    }

    @Test
    public void TC06() {
//id unique assertion


        List<Integer> idCheck = json.getList("data.id");

        for (int i = 0; i < idCheck.size(); i++) {
            for (int j = i + 1; j < idCheck.size(); j++) {
                Assert.assertNotEquals(idCheck.get(i), idCheck.get(j));
                System.out.println(i + ": " + idCheck.get(i) + " " + idCheck.get(j));
            }
        }
//ikinci yol
        Set<Integer> idSet = new HashSet<>(idCheck);
        Assert.assertEquals(idCheck.size(), idSet.size());
        System.out.println(idSet.size());
        System.out.println(idCheck.size());
    }

    @Test //NULL olan name var mı, varsa hangi page hangi id?
    public void Tc0107() {

        List<String> allNames = new ArrayList<>();  //once bos list olusturdum

        System.out.println("Total Page: " + json_allPages);

        for (int i = 1; i <= json_allPages; i++) {      //burada her sayfadaki 20 kisilik isimleri aldik.

            given().queryParam("page", i).
                    when().get();

            for (int j = 0; j < json_name_List.size(); j++) {        // bu listi olusturdugumuz bos listin icine koyduk.
                allNames.add(json_name_List.get(j));
                assertTrue(allNames.get(j) != null);
            }
        }

        System.out.println("allNames.size() : " + allNames.size());
    }

    @Test //number of males assertion
    public void TC108() {
        int count = 0;
        for (int i = 0; i < json_allPages; i++) {
            given().queryParam("page").when().get();
            for (String gender : json_gender_List) {
                if (gender.equals("male")) { //burda equals yerine contains desen famale i de sayar cunku "male" kelimesi female da da var
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Test //number of males assertion
    public void TC109() {
        int count = 0;
        for (int i = 0; i < json_allPages; i++) {
            given().queryParam("page").when().get();
            for (String gender : json_gender_List) {
                if (gender.equals("female")) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Test
    public void GetTC110() {

        List<String> notDupliNames = new ArrayList<>();
        List<String> dupliNames = new ArrayList<>();

        for (String name : json_name_List) {
            if (!notDupliNames.contains(name)) {
                notDupliNames.add(name);
            } else if (json_name_List.contains(name)) {
                dupliNames.add(name);

                System.out.println(name);
            }


//        Set<String> store = new HashSet<>();
//        for (String name : json_name_List) {
//            if (store.add(name) == false) {
//                System.out.println("found a duplicate element in array : " + json_name_List);
//            } else {
//                System.out.println("No duplicate names");
//            }

        }


    }

    @Test
    public void Get111() {
        int count = 0;
        for (int i = 0; i < json_allPages; i++) {
            given().queryParam("page").when().get();
            for (String gender : json_gender_List) {
                if (gender.equals("female")) {
                    count++;

                }
            }
            System.out.println(count);
        }


    }
}


