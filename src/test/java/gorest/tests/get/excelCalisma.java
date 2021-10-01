package gorest.tests.get;

import gorest.utilities.ExcelUtil;
import gorest.utilities.ReusableMethods;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class excelCalisma {


    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";

    JsonPath json;  //de-serialization
    List<String> json_gender_List;
    int totalPage;
   // String path = "IdeaProjects\\ApiGo\\exceldata.xlsx";
   // String alldata = "alldata";
    //ExcelUtil excel = new ExcelUtil(path, alldata);
    int row =1;
    @Test
    public void testName() {
        String excelPath = "src/test/resources/idListNew.xlsx";
        String sheetName = "alldata";
        int row =1;
        ExcelUtil excel = new ExcelUtil(excelPath,sheetName);
        ReusableMethods.getresponse(endpoint);
      //  int totalPage = response.path("meta.pagination.pages");

        for (int i = 1; i <= 20; i++) {

            response = given().queryParam("page", i)
                    .when().get(endpoint);

           json = response.jsonPath();

            List<Integer> idList = json.getList("data.id");


            for (int j = 2; j < idList.size(); j++) {
                excel.setCellDataWithColumnName(json.getString("data.id[" + j + "]"), "id", row++);

            }
        }
    }
}
