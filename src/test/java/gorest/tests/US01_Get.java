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

import java.util.*;

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
        json = response.jsonPath();

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

//        Set<Integer> idSet=new HashSet<>(idCheck);
//        Assert.assertEquals(idCheck.size(),idSet.size());
//        System.out.println(idSet.size());
//        System.out.println(idCheck.size());
}
    @Test
    public void TC07(){

//  names are not NULL assertion

        List<String> names=json.getList("data.name");
        int pages=json.getInt("meta.pagination.page");
        List<Integer> ids=json.getList("data.id");

        //System.out.println(pages);

        for (int i = 0, namesSize = names.size(); i < namesSize; i++) {
            String name = names.get(i);
            String s = "Sankey Makron";
            // System.out.println(name);
            // Assert.assertNotEquals(name, s);

            if (name.equals(s)) {
                System.out.println(name);
                System.out.println("Page: "+ pages+ " id: " + ids.get(i));
            }
        }

    }

    @Test
    public void TC08(){


    }

    @Test
    public void TC0112() {

//        statusu aktif olan kaç kişi var// 421

        List<String> active = json.getList("data.status");
        int count = 0;
        String s = "active";
        for (String value : active) {
            if (Objects.equals(value, s)) {
                count++;
                System.out.println(value);
            }
        }
        System.out.println(count);

    }

    @Test
    public void TC0113(){
        //4142 id.si var mı

        List<Integer> id=json.getList("data.id");
        int expected=4142;
        boolean check=false;
        for (Integer integer : id) {
            if (integer == expected) {
                check = true;

                System.out.println(integer);
                break;
            }
            Assert.assertTrue(check);
        }



    }




}
