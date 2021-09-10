package gorest.tests;

import gorest.utilities.ReusableMethods;

import gorest.utilities.TestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class TC_06_10  {

    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    JsonPath json;

    @BeforeMethod
    public void setup() {
        ReusableMethods.getresponse(endpoint);
        json = response.jsonPath();
    }

    // id unique assertion
    @Test
    public void TC06() {

        List<Integer> idAll = json.getList("data.id");
        System.out.println(idAll.size() + " Tane List'ten gelen id var");

        // 1. yol
//                for (int i = 0; i < idAll.size(); i++) {
//                    for (int j = i + 1; j < idAll.size(); j++) {
//                        Assert.assertTrue(idAll.get(i) != idAll.get(j));
//                    }
//                }


        //2. yol
        Set<Integer> idSet = new HashSet<>(idAll);
        Assert.assertEquals(idAll.size(), idSet.size());
        System.out.println(idSet.size() + " Tane Set'ten gelen id var");
    }

    //names are not NULL assertion
    @Test
    public void TC07() {

        List<String> allNames = new ArrayList<>();

        int totalPage = response.path("meta.pagination.pages");

        System.out.println("totalPage = " + totalPage);

        for (int i = 1; i <= totalPage; i++) {

            given().queryParam("page", i)
                    .when().get();
            List<String> names = json.getList("data.name");

            for (int j = 0; j < names.size(); j++) {
                allNames.add(names.get(j));
                Assert.assertTrue(allNames.get(j) != null);
            }
        }

        System.out.println("allNames.size() : " + allNames.size());

    }

    //number of males assertion
    @Test
    public void TC08() {

//        List<String> genderList = json.getList("data.gender");
//        //1. yol
//        int count = 0;
//        for (int i = 0; i < genderList.size(); i++) {
//            if (genderList.get(i).equals("Male")) {
//                count++;
//
//            }
//        }
//        System.out.println(count + ": " + "Tane Male var");
//        //2. yol
//        int countMale = 0;
//        for (String male : genderList) {
//            if (male.equals("Male")) {
//                countMale++;
//            }
//        }
//        System.out.println(countMale + " Tane Male var");
//    }

        ///*********3.yol
    int numberOfMaleData = 0;
    ReusableMethods.getresponse(endpoint);
    json = response.jsonPath();

    int totalPages = json.getInt("meta.pagination.pages");
    int totalLimitPerPages = json.getInt("meta.pagination.limit");
        for(int i=1;i<=totalPages;i++){
        ReusableMethods.getresponse(endpoint+ "?page="+i);
        json = response.jsonPath();
        for(int j=0;j<totalLimitPerPages;j++){

                if(json.getString("data.gender["+j+"]").equals("Male")){
                    System.out.println(i+" page, "+ j + " index data is MALE");
                    numberOfMaleData++;
                }
            }


    }
        System.out.println(numberOfMaleData);
        //Assert.assertEquals(numberOfMaleData,0);


    }
    //number of females assertion
    @Test
    public void TC09() {

        List<String> genderList = json.getList("data.gender");

        int countFemale=0;
        for (String female : genderList){
            if (female.equals("Female")){
                countFemale++;
            }
        }
        System.out.println(countFemale  + " Tane Female var");

    }

    //check dublicate names
    @Test
    public void TC10() {
        List<String> nameList = json.getList("data.name");
        List<String> duplicateNameList = new ArrayList<>();

        for (String name : nameList) {
            if (!duplicateNameList.contains(name)) {
                duplicateNameList.add(name);
            }
        }
        System.out.println(duplicateNameList);
        Assert.assertEquals(nameList.size(), duplicateNameList.size());//size'lari ayniysa dublicate yok demek
        //2. yol
        Set<String> dublicateSet = new HashSet<>(nameList);

        System.out.println(dublicateSet.size());
        Assert.assertEquals(nameList.size(), dublicateSet.size());

    }

    //more females assertion//female sayisi daha mo fazla
    @Test
    public void TC11() {

        List<String> allNames = new ArrayList<>();

    }
}