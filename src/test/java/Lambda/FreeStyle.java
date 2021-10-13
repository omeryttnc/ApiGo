package Lambda;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.pojos.ApiGo;
import gorest.pojos.Data;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class FreeStyle {
    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    ObjectMapper objectMapper = new ObjectMapper();
    ApiGo apiGoPojo;
    JsonPath json;
    String token = "3158b67b6b0e956ecb5a1f06fe311f94a45c5f6268f56db7272f51e75f050304";
    data_lambda data_lambda = new data_lambda();
    List<Integer> id = new ArrayList<>(Arrays.asList(data_lambda.class_allid));
    List<String> email = new ArrayList<>(Arrays.asList(data_lambda.class_allEmail));
    List<String> name = new ArrayList<>(Arrays.asList(data_lambda.class_allName));


    @Test
    public void testName() {


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
