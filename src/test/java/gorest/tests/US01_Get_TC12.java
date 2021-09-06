package gorest.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class US01_Get_TC12 {
    Response response;
    String endpoint="https://gorest.co.in/public-api/users/";
    JsonPath json;

    @Test
    public void TC01_012() {
        //NUMBER OF ACTIVE STATUS HOLDERS 421
        response= given().accept(ContentType.JSON)
                .when().get(endpoint);

        response.then().assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);
                json=response.jsonPath();

        int totalPage=json.getInt("meta.pagination.pages");
        //System.out.println(totalPage); //checked 90 pages


        List<String> allActives=new ArrayList<>();

        for (int i = 1; i <=totalPage; i++) {
            response=given().queryParam("page",i).get(endpoint);
            json=response.jsonPath();
            List <String> actives=json.getList("data.active");

            allActives.addAll(actives);

        }
        System.out.println(allActives.size());
    }
}
