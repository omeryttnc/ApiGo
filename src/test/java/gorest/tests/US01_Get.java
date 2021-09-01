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
//ikinci yol
        Set<Integer> idSet=new HashSet<>(idCheck);
        Assert.assertEquals(idCheck.size(),idSet.size());
        System.out.println(idSet.size());
        System.out.println(idCheck.size());
    }

    @Test
    public void TC01012() {
        //NUMBER OF ACTIVE STATUS HOLDERS 421

        totalPage=json.getInt("meta.pagination.pages");
        //System.out.println(totalPage); //checked 90 pages


        List<String> allActives=new ArrayList<>();

        for (int i = 1; i <=totalPage; i++) {
            response=given().queryParam("page",i).get(endpoint);
            JsonPath json=response.jsonPath();
            List <String> actives=json.getList("data.active");

            allActives.addAll(actives);

        }
        System.out.println(allActives.size());
    }

    @Test
    public void TC01013(){

        //ASSERT that A CERTAIN ID NUMBER EXISTS /AND RETRIEVE THE NAME AND STATUS OF THAT ID
        int expected=1685;

        totalPage=json.getInt("meta.pagination.pages");
        //System.out.println(totalPage); total number of pages is 74

        List<Integer> allIDs=new ArrayList<>();
        List<String> allNames=new ArrayList<>();
        List<String> allEmails=new ArrayList<>();
        List<String> allGenders=new ArrayList<>();
        List<String> allStatus=new ArrayList<>();

        for (int i = 1; i <=totalPage; i++) {
            response = given().queryParam("page", i)
                    .when().get(endpoint);
            json = response.jsonPath();

            List<Integer> IDs = json.getList("data.id");
            List<String> names = json.getList("data.name");
            List<String> emails = json.getList("data.email");
            List<String> genders = json.getList("data.gender");
            List<String> status = json.getList("data.status");
            allIDs.addAll(IDs);
            allNames.addAll(names);
            allEmails.addAll(emails);
            allGenders.addAll(genders);
            allStatus.addAll(status);
        }

            for (int j = 0; j < allIDs.size(); j++) {
                if(allIDs.get(j)==expected){
                    System.out.println("ID= "+allIDs.get(j)+
                            "; NAME= " + allNames.get(j)+
                            "; EMAIL= " + allEmails.get(j)+
                            "; GENDER= " + allGenders.get(j)+
                            "; STATUS= " + allStatus.get(j));
                    break;
                }

            }

            Assert.assertTrue(allIDs.contains(expected));
        System.out.println(allIDs);
        System.out.println(allNames);
        System.out.println(allEmails);
        System.out.println(allGenders);
        System.out.println(allStatus);

    }


}

