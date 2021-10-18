package Lambda;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.pojos.ApiGo;
import gorest.pojos.Data;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class FreeStyle {
    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    ObjectMapper objectMapper = new ObjectMapper();
    ApiGo apiGoPojo;
    JsonPath json;
    String token = "3158b67b6b0e956ecb5a1f06fe311f94a45c5f6268f56db7272f51e75f050304";
    List<String> allMail = new ArrayList<>();
    List<Integer> allId = new ArrayList<>();
    List<String> allName = new ArrayList<>();
    @BeforeMethod
    public void setup() throws IOException {
        response = given().
                accept(ContentType.JSON).
                when().
                get(endpoint);
        response.
                then().
                assertThat().
                contentType(ContentType.JSON);

        apiGoPojo = objectMapper.readValue(response.asString(), ApiGo.class);
        // apiGoPojo = response.body().as(ApiGo.class);


        response = given()
                  .contentType(ContentType.JSON)
                  .auth().oauth2(token)
                  .when()
                  .get(endpoint);
        json = response.jsonPath();

        int totalPage = json.getInt("meta.pagination.pages");

        for (int i = 1; i <= 2; i++) {

            response = given().queryParam("page", i)
                    .when().get(endpoint);

            apiGoPojo = objectMapper.readValue(response.asString(), ApiGo.class);


            for (Data w : apiGoPojo.getData()) {

                allId.add(w.getId());
                allMail.add(w.getEmail());
                allName.add(w.getName());
            }

        }
    }

    //id natural order assertion
    //id unique assertion
    //names are not NULL assertion
    //female sayisi daha mo fazla
    //search for id(4142) assertion 2492
    //search for name("nuri duman") assertion
    //search for email("aliveli@gmail.com")assertion
    //count emails "@gmail.com" assertion
    //counts surname begins with A and D assertion
    //check duplicate names


}
