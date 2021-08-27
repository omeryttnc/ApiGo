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

public class US01Get {

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


        //response.prettyPrint();
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
    public void GetTC10() {

        List<String> dupliNames = json.getList("data.name");


//        List<String> notDupliNames = new ArrayList<>();
//        List<String> dupliNames = new ArrayList<>();

//        for (String name : nameList) {
//            if (!notDupliNames.contains(name)) {
//                notDupliNames.add(name);
//            } else if (nameList.contains(name)) {
//                dupliNames.add(name);
//                List<Integer> dupliId = json.getList("data.id");
//                System.out.println(dupliNames);
//            }


        Set<String> store = new HashSet<>();
        for (String name : dupliNames) {
            if (store.add(name) == false) {
                System.out.println("found a duplicate element in array : " + name);
            } else {
                System.out.println("No duplicate names");
            }
        }


        }
    }
//    @Test
//    public void Get11(){
//        List<String> gender = json.getList("data.gender");
//        int count=0;
//        for(String gender : gender){
//            if (user[i].gender == "Male")
//                total+= 1;
//        }
//        System.out.println("Total: \nMale: " + (total) + "\nTotal Female: " + (count - total));
//
//     }


}