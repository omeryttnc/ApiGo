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

public class US01_Get_TC13 {

    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;
    int totalPage;

    @Test
    public void TC01_013(){
        response=given().accept(ContentType.JSON)
                .get(endpoint);
                response.then().assertThat()
                        .statusCode(200)
                        .contentType(ContentType.JSON);
                json=response.jsonPath();

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

