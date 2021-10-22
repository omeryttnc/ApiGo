package gorest.tests.delete;

import gorest.pojos.ApiGo;
import gorest.pojos.Data;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class Delete_TC_304 {

    private static JsonPath json;
    private static Response response;
    private static String token = "127e45713e989102f2953435d4eb235fdf2eb8cbd8253ee59066e964e3c27bcd";
    private static String endpoint = "https://gorest.co.in/public-api/users/";


    public static List<Object> deleteMethod(Integer pageNumber, String key, String value) {
        response = given().contentType(ContentType.JSON).when().get(endpoint);
        response.then().contentType(ContentType.JSON);
        json = response.jsonPath();
        List<Object> deletedIDs = new ArrayList<>();
        List<Map<String, Object>> mapOfObjects = json.getList("data");//
        List<Map<String, String>> mapOfEmails = json.getList("data");//

        response = given().queryParam("page", pageNumber)
                .when().get(endpoint);

         json = response.jsonPath();

        for (int i = 0; i < mapOfEmails.size(); i++) {
            if (mapOfEmails.get(i).get(key).contains(value)) {
               // System.out.println(mapOfObjects.get(i).get("id"));

                deletedIDs.add(mapOfObjects.get(i).get("id"));

                response = given().auth().oauth2(token)
                        .when().delete(endpoint + mapOfObjects.get(i).get("id"));

            }

        }

        return deletedIDs;

    }



        @Test
        public  void TC_304(){
            System.out.println(deleteMethod(1, "email", "1634904686692ce.com"));
            Assert.assertEquals(deleteMethod(1, "email", "1634904686692ce.com").size(), 0);
        }

    }





