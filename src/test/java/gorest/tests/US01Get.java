package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class US01Get {

    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    //endpoint'in configuration properties ya da testbase de olmasi daha guzeldir
    JsonPath json;

    @BeforeMethod  //TC_0101 status code assertion  ,//TC_0102 content-type assertion
    public void TC01_02() {
        response = given().  //anahtar kelime-1
                accept(ContentType.JSON). // accept("application/json). ---olarak a karsimiza cikabilir
                ///get methodunda accept type kullaniriz
                        when().//anahtar kelime-2
                        get(endpoint);
        response.
                then().//anahtar kelime-3
                assertThat().
                statusCode(200). // statusCode(HttpStatus.SC_OK).--statusCode(HTTP/1.1 200 OK)--olarak da karsimiza cikabilir
                contentType(ContentType.JSON);  ///burasi her turlu method icin(get-post-put..) content type olarak yazilir

        // System.out.println(response.statusCode());  //sadece status codu yazdirmak icin
        //  response.prettyPrint();  //sadece body i gosterir
        // response.prettyPeek();   //hem body hem headeri gosterir
        json = response.jsonPath();  //json,gelen body icinde rahatca islem yapmamizi saglar

    }

    @Test
    public void deneme() {
        int code = json.getInt("code");
        System.out.println(code);
        int total = json.getInt("meta.pagination.total");
        System.out.println(total);
        Assert.assertNotEquals(total, "2000");

        //List kullanimi : birden fazla value icin
        List<Integer> idList = json.getList("data.id");
        System.out.println(idList);  //index kullanilarak istenilen id'ye ulasilabilir

        //List Map Kullanimi : birden fazla key value barindiran datalar icin
        List<Map<String, Object>> data = json.getList("data");
        System.out.println(data.get(1).get("gender")); //index ve key ismi kullniarak istenilen dataya ulasilabilir


        //****Asssertion Farki

        //TestNG ile assertion
        Assert.assertEquals(data.get(1).get("gender"), "female");

        //response classindan gelen assertThat ile assertion

/*        response.then().assertThat().body("meta.pagination.limit", equalTo(20),
                "meta.pagination.page", equalTo(1),
                "data.id", hasItem(2662), "data.id", hasItems(2662, 2654));
 */

        //kullanimi yaygin degildir
        // hard assertion'dir.
        // Biri yanlis olsa bile code durur hata raporu verir
        // Karisikliga yol acar,okuma zorlugu olusturur,
        //Dinamik degildir
    }

    @Test
    public void TC03() {
        int total = json.getInt("meta.pagination.total");//totale ulasmak icin 3 adim var//parent child iliskisi
        System.out.println(total);
        Assert.assertNotEquals(total, 1375);
    }

    @Test
    public void TC04() {
        int pageSize = json.getInt("meta.pagination.pages");
        System.out.println(pageSize);
        Assert.assertNotEquals(pageSize, 20);
    }

    @Test
    public  void TC05(){

    }

    @Test
    public void TC06() {
//id unique assertion
       List<Integer> idList= json.getList("data.id");
        String endpoint = "https://gorest.co.in/public-api/users/";
        for(int i=1;i<=idList.size();i++) {
            setup(endpoint + "?page=" + i);

            List<Integer> idCheck = json.getList("data.id");

            for (int i = 0; i < idCheck.size(); i++) {
                for (int j = i + 1; j < idCheck.size(); j++) {
                    Assert.assertNotEquals(idCheck.get(i), idCheck.get(j));
                    System.out.println(i + ": " + idCheck.get(i) + " " + idCheck.get(j));

                }

            }
        }
//        Set<Integer> idSet=new HashSet<>(idCheck);
//        Assert.assertEquals(idCheck.size(),idSet.size());
//        System.out.println(idSet.size());
//        System.out.println(idCheck.size());
   }
    @Test
    public void TC07(){

//  names are not NULL assertion
        
        List<String> names=json.getList("data.name");
        int pages=json.getInt("meta.pagination.page");
        List<Integer> ids=json.getList("data.id");

        //System.out.println(pages);

        for (int i = 0, namesSize = names.size(); i < namesSize; i++) {
            String name = names.get(i);
            String s = "Sankey Makron";
           // System.out.println(name);
           // Assert.assertNotEquals(name, s);

            if (name.equals(s)) {
                System.out.println(name);
                System.out.println("Page: "+ pages+ " id: " + ids.get(i));
            }
        }

    }

    @Test
    public void TC08(){


    }

    @Test
    public void TC0112() {

//        statusu aktif olan kaç kişi var// 421

        List<String> active = json.getList("data.status");
        int count = 0;
        String s = "active";
        for (String value : active) {
            if (Objects.equals(value, s)) {
                count++;
                System.out.println(value);
            }
        }
        System.out.println(count);

    }

    @Test
    public void TC0113(){
        //4142 id.si var mı

        List<Integer> id=json.getList("data.id");
        int expected=4142;
        boolean check=false;
        for (Integer integer : id) {
            if (integer == expected) {
                check = true;

                System.out.println(integer);
                break;
            }
            Assert.assertTrue(check);
        }



    }

}