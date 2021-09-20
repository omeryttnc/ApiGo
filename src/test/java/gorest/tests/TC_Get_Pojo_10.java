package gorest.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.pojos.ApiGo;
import gorest.pojos.Data;
import gorest.utilities.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class TC_Get_Pojo_10 extends TestBase {
    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    ObjectMapper objectMapper = new ObjectMapper();
    ApiGo apiGoPojo;


    @BeforeMethod
    public void setup() throws JsonProcessingException {
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

    }

    @Test
    public void tcGet110a() throws JsonProcessingException {
        int allPages = apiGoPojo.getMeta().getPagination().getPages();
        List<String> allNames = new ArrayList<>();
        List<Integer> allId = new ArrayList<>();
        List<Integer> allPage = new ArrayList<>();


        for (int i = 1; i <= 3; i++) {      //burada her sayfadaki 20 er sayfa sayisini i ye atadik
            spec01.queryParam("page", i); // i 1,2 ... artarak sayfalari cekiyor
            response = given(). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    spec(spec01).
                    when().get();
            apiGoPojo=objectMapper.readValue(response.asString(), ApiGo.class);



            for (Data w : apiGoPojo.getData()) {
                allNames.add(w.getName());
                allId.add(w.getId());
                allPage.add(apiGoPojo.getMeta().getPagination().getPage());

            }
        }

        Collections.sort(allNames);
        //System.out.println(allNames);
        for (int j = 0; j < allNames.size() - 1; j++) {
            if (allNames.get(j).equals(allNames.get(j + 1))) {
                System.out.println("Page : " + allPage.get(j) + " first name : " + allNames.get(j) + " id : " + allId.get(j) + " Page : " + allPage.get(j) + " second name : " + allNames.get(j + 1) + " id : " + allId.get(j+1));
            }
        }


    }


    @Test
    public void tcGet110b() throws JsonProcessingException {
//        int allPages = apiGoPojo.getMeta().getPagination().getPages();
//        List<String> allNames = new ArrayList<>();
//        List<Integer> allId = new ArrayList<>();
//        List<Integer> allPage = new ArrayList<>();
        Map<Integer, String> Map_Id_Name = new HashMap<>();

        for (int i = 1; i <= 3; i++) {      //burada her sayfadaki 20 er sayfa sayisini i ye atadik
            spec01.queryParam("page", i); // i 1,2 ... artarak sayfalari cekiyor
            response = given(). //given yeniden request yaptik, i kac tane page var saydi ve toplam page i verecek
                    spec(spec01).
                    when().get();
            apiGoPojo = objectMapper.readValue(response.asString(), ApiGo.class);

            for (Data w : apiGoPojo.getData()) {
                Map_Id_Name.put(w.getId(), w.getName());
            }

        }
        Map<Integer, String> Map_sorted = Map_Id_Name.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        List<Integer> idList = new ArrayList<>(Map_sorted.keySet());

        for (int i = 0; i < Map_sorted.size() - 1; i++) {

            System.out.println("name : " + Map_sorted.get(idList.get(i)) + " id : " + idList.get(i));
            if (Map_sorted.get(idList.get(i)).equals(Map_sorted.get(idList.get(i + 1)))) {
                System.out.println(

                        "name : " + Map_sorted.get(idList.get(i)) +
                                " id : " + idList.get(i) +
                                " name : " + Map_sorted.get(idList.get(i + 1)) +
                                " id : " + idList.get(i + 1)
                );
            }
        }
    }
}
