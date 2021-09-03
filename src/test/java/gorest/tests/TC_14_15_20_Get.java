package gorest.tests;

import gorest.utilities.ConfigurationReader;
import gorest.utilities.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.AssertionSupport;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class TC_14_15_20_Get {


    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;

//   @BeforeMethod
//   public void setup(){
//       ReusableMethods.getresponse(endpoint);
//       json=response.jsonPath();
//   }
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

    json = response.jsonPath();

}

    @Test  //null data verification
    public void TC14() {

        List<String> allNames = new ArrayList<>();  //once bos list olusturulur

        int totalPage = response.path("meta.pagination.pages");  //toplam kac sayfa var onu aldik.

        for (int i = 1; i <= totalPage; i++) {      //burada her sayfadaki 20 kisilik isimleri aldik.

            response = given().queryParam("page", i)
                    .when().get(endpoint);
             json = response.jsonPath();

            List<Map<String, Object>> map = json.getList("data");
            for (Map<String, Object> w : map) {
                for (String p : w.keySet()) {
                    System.out.println(w.keySet());
                    System.out.println(w.values());
                    Assert.assertFalse(w.get(p) == null);
                }
            }
        }
}

    @Test  //search for name("nuri duman") assertion
    public void TC15() {

        List<String> allNames = new ArrayList<>();
        int totalPage = response.path("meta.pagination.pages");  //toplam kac sayfa var onu aldik.

        for (int i = 1; i <= totalPage; i++) {

            response = given().queryParam("page", i)
                    .when().get(endpoint);

            JsonPath json = response.jsonPath();

            List<String> names = json.getList("data.name");

            for (int j = 0; j < names.size(); j++) {
                allNames.add(names.get(j));           // bu listi olusturdugumuz bos listin icine koyduk.

            }
        }
        System.out.println(allNames);
        System.out.println(allNames.size()); //1686
        Assert.assertFalse(allNames.contains("nuri duman"));

    }
    @Test  //updated data count assertion ==  1696(guncel sayi surekli degisiyor)
    public void TC20() {

        List<String> allCreatedDates = new ArrayList<>();
        List<String> allUpdateDates = new ArrayList<>();
        int totalPage = response.path("meta.pagination.pages");

        for (int i = 1; i <= totalPage; i++) {

            response = given().queryParam("page", i)
                    .when().get(endpoint);

            JsonPath json = response.jsonPath();

            List<String> createdDate = json.getList("data.created_at");
            List<String> updatedDate=json.getList("data.updated_at");


            for (int j = 0; j < createdDate.size(); j++) {
                allCreatedDates.add(createdDate.get(j));
            }

            for (int j = 0; j < updatedDate.size(); j++) {
                allUpdateDates.add(updatedDate.get(j));
            }
        }
        System.out.println(allCreatedDates.size());
        System.out.println(allUpdateDates.size());
        Assert.assertEquals(allCreatedDates.size(),allUpdateDates.size());  // --all date uptadated
       // Assert.assertEquals(allUpdateDates.size(),"1696");  //guncel sayi surekli degisiyor.
    }


}



