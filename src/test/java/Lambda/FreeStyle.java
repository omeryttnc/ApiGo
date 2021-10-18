package Lambda;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.pojos.ApiGo;
import gorest.pojos.Data;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    List<String> genderList = new ArrayList<>();

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


        response = given().contentType(ContentType.JSON).auth().oauth2(token).when().get(endpoint);
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
                genderList.add((w.getGender()));

            }

        }
        System.out.println(genderList);
    }

    @Test
    public void odev() {
//        //1-id natural order assertion
//        allId.stream().sorted(Comparator.naturalOrder()).forEach(Lambda::getPrint);
//
//        //2-id unique assertion
//       allId.stream().distinct().forEach(Lambda::getPrint);
////        System.out.println("aa");
//        Set idSet=allId.stream().collect(Collectors.toSet());
//        System.out.println(idSet.size());
//        Assert.assertEquals(allId.size(),idSet.size());
//
//        //3-names are not NULL assertion
//        allName.stream().filter(t->t!=null).forEach(Lambda :: getPrint);
//        Assert.assertTrue(allName.containsAll(null));
//        //4-female sayisi daha mo fazla
//
        List femaleList=genderList.stream().filter(t->t.equals("female")).collect(Collectors.toList());
        List maleList=genderList.stream().filter(t->t.equals("male")).collect(Collectors.toList());
        System.out.println(femaleList.size() + " " +maleList.size());  //19//21
        Assert.assertFalse(femaleList.size()>maleList.size());
        //5-search for id(4142) assertion 2492
       Stream<Integer> a=allId.stream().filter(t->t.equals(2492));
//        System.out.println(a);
//
//        //6-search for name("nuri duman") assertion
       allName.stream().filter((t -> t.equals("nuri duman")));
//        //search for email("aliveli@gmail.com")assertion
//
        //7-count emails "@gmail.com" assertion
        allName.stream().filter((t -> t.contains("@gmail.com")));
        //8-counts surname begins with A and D assertion
       int c= (int) allName.stream().filter((t -> t.charAt(0)=='A' || t.charAt(0)=='D')).count();
////        //9-check duplicate names
        Set setName=allName.stream().collect(Collectors.toSet());
        System.out.println(setName.size());
        System.out.println(allName.size());
        //2.yol
        List<String> dublicateName=new ArrayList<>();
//       allName.stream().filter(t->dublicateName.contains(allName)).
//                forEach(dublicateName.add(allName));
    }

 ///ctrl+alt+v ile otomatik konteynir atanabilir sag taraf varsa
}
