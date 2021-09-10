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

public class US01Get extends TestBase {

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

    @Test //TC_0102 content-type assertion
    public void tc102() {
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
    }

    @Test //all data count assertion
    public void tc103() {
        int total = json.getInt("meta.pagination.total");
        System.out.println(total);

        Assert.assertNotEquals(total, "2000");
        //assertion2
        // response.then().assertThat().body("meta.pagination.total", Matchers.equalTo(200));//not equals kullanamiyoruz

        //Matchers classinin importunu "import static org.hamcrest.Matchers.*;"
        // olarak duzenlersek her seferinde Matchers kullanmayabiliriz

    }

    @Test //all page assertion
    public void tc104() {
        int pageSize = json.getInt("meta.pagination.pages");
        System.out.println(pageSize);
        Assert.assertNotEquals(pageSize, 20);

        //response.then().assertThat().body("meta.pagination.pages",equalTo(20));//not equals kullanamiyoruz
    }

    @Test //id natural order assertion
    public void tc105() {

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

    @Test //id unique assertion
    public void tc106() {

//        for (int i = 0; i < json_idList.size(); i++) {
//            for (int j = i + 1; j < json_idList.size(); j++) {
//                Assert.assertNotEquals(json_idList.get(i), json_idList.get(j));
//                System.out.println(i + ": " + json_idList.get(i) + " " + json_idList.get(j));
//            }
//        }
        //ikinci yol
        Set<Integer> idSet = new HashSet<>(json_idList);
        Assert.assertEquals(json_idList.size(), idSet.size());
        System.out.println(idSet.size());
        System.out.println(json_idList.size());
    }

    @Test //NULL (isimsiz) olan name var mı, varsa hangi page hangi id?
    public void tc107() {

        List<String> allNames = new ArrayList<>();  //once bos list olusturdum

        System.out.println("Total Page: " + json_allPages); // sayfanin basindaki all pagesdan aliyor

        for (int i = 1; i <= json_allPages; i++) {      //burada her sayfadaki 20 er sayfa sayisini i ye atadik

            given().queryParam("page", i). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    when().get(endPoint);
            System.out.println(i); //100

            for (int j = 0; j < json_name_List.size(); j++) {        // bu listi olusturdugumuz bos listin icine koyduk.
                allNames.add(json_name_List.get(j));
                System.out.println(j);
                assertTrue(allNames.get(j) != null);
            }
        }

        System.out.println("allNames.size() : " + allNames.size());
    }

    @Test //number of males assertion
    public void tc108() {
        int count = 0;
        for (int i = 0; i < json_allPages; i++) {
            given().queryParam("page", i). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    when().get(endPoint);
            for (String gender : json_gender_List) {
                if (gender.equals("male")) { //burda equals yerine contains desen famale i de sayar cunku "male" kelimesi female da da var
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    @Test //number of males assertion
    public void tc109() {
        int count = 0;
        for (int i = 0; i < json_allPages; i++) {
            given().queryParam("page", i). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    when().get(endPoint);
            for (String gender : json_gender_List) {
                if (gender.equals("female")) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

//    public static void main(String[] args) {
//        String endPoint = "https://gorest.co.in/public-api/users?page=2";
//        Response response = given(). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
//                when().get(endPoint);
//        response.prettyPrint();
////        System.out.println(i);
//    }

    @Test //give duplicate names with their ids
    public void getTc110() {

//        Set<String> store = new HashSet<>();
//
//            for (String name : json_name_List) {
//                if (store.add(name) == false) {
//                    System.out.println("found a duplicate element in array : " + name);
//
//                } else {
//                    System.out.println("No duplicate names");
//                }
//
//            }
//        }

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
    @Test
    public void get111() {

        int countFemale = 0;
        int countMale = 0;
        for (int i = 0; i < json_allPages; i++) {
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
        //Assert.assertTrue(countFemale>countMale);
        Assert.assertEquals(countFemale, countMale);
    }

}


