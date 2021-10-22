package gorest.tests.delete;

import gorest.utilities.ConfigurationReader;
import gorest.utilities.ReusableMethods;
import gorest.utilities.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class TC_302 extends TestBase {
    Response response;
    JsonPath json;

    @Test
    public void delete_5_random() {
        System.out.println("silinmeden once : "+ReusableMethods.getId());
        List<Integer> id = ReusableMethods.getId();
        List<Integer> deleted_id = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int n = random.nextInt(id.size());
            int deletedID = id.get(n);
            deleted_id.add(deletedID);
            System.out.println(deletedID);
            response = given().spec(spec01).contentType(ContentType.JSON).auth().oauth2(ConfigurationReader.getProperty("token")).
                    when().delete("/" + deletedID);
        }
        System.out.println("Silindikten sonra : "+ReusableMethods.getId());
        System.out.println("Silinenler : " + deleted_id);
        for (int i = 0; i <deleted_id.size() ; i++) {
            int finalI = i;
            Assert.assertTrue(ReusableMethods.getId().stream().noneMatch(t-> t.equals(deleted_id.get(finalI))));
        }
    }
}
