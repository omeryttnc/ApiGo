package gorest.tests;

import gorest.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TC_11_15_Get {


    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigurationReader.get("goRest.uri");

    @Test //null data verification
    public void TC0114() {
        List<String> allNames=new ArrayList<>();  //once bos list olusturdum
        Response response= given().accept(ContentType.JSON)
                .when().get();

        JsonPath json=response.jsonPath();
        int totalPage= response.path("meta.pagination.pages");  //toplam kac sayfa var onu aldik.

        System.out.println("totalPage = " + totalPage);

        for (int i = 1; i <=totalPage ; i++) {      //burada her sayfadaki 20 kisilik isimleri aldik.
            given().queryParam("page", i)
                    .when().get();
            List<String> names = json.getList("data.name");
            for (int j = 0; j <names.size() ; j++) {        // bu listi olusturdugumuz bos listin icine koyduk.
                allNames.add(names.get(j));
                Assert.assertTrue(allNames.get(j)!=null);
            }
        }

        System.out.println("allNames.size() : " +allNames.size());

        HashMap<String ,String> map=response.as(HashMap.class);

        Assert.assertFalse(map.containsValue(null));
        Assert.assertFalse(map.containsKey(null));

        System.out.println(map.values());
        System.out.println(map.keySet());


    }

    @Test  //search for name("nuri duman") assertion
    public void TC0115() {

        List<String> nameList=json.getList("data.name");
        System.out.println(nameList);
        Assert.assertFalse(nameList.contains("nuri duman"));

    }

}
