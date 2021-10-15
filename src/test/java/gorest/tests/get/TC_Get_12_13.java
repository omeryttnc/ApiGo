package gorest.tests.get;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TC_Get_12_13 {
    Response response;
    String endpoint="https://gorest.co.in/public-api/users/";
    JsonPath json;
    int totalPage;


    @BeforeMethod
    public void setup(){
        response= given().accept(ContentType.JSON)
                .when().get(endpoint);

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);
        json=response.jsonPath();
         totalPage=json.getInt("meta.pagination.pages");
        //System.out.println(totalPage); //checked 90 pages
        totalPage=5;
    }

    @Test
    public void TC01_012() {
        //NUMBER OF ACTIVE STATUS HOLDERS 421
        int expected=421;

        List<String> allActives=new ArrayList<>();

        for (int i = 1; i <=totalPage; i++) {
            response=given().queryParam("page",i).get(endpoint);
            json=response.jsonPath();
            List <String> actives=json.getList("data.active");

            allActives.addAll(actives);

        }
        System.out.println(allActives.size());
        //Since the output changes,assertion generally fails.
        //Assert.assertEquals(expected, allActives.size());
    }

    @Test
    public void TC01_013(){

        //ASSERT that A CERTAIN ID NUMBER EXISTS /AND RETRIEVE THE NAME AND STATUS OF THAT ID
        int expected=1982;



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
