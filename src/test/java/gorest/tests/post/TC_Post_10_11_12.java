package gorest.tests.post;

import com.github.javafaker.Faker;
import gorest.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TC_Post_10_11_12 {
    Response response;
    //String endPoint= ConfigurationReader.getProperty("endPoint");
    String endPoint= "https://gorest.co.in/public-api/users";
    //String token=ConfigurationReader.getProperty("token");
    String token="127e45713e989102f2953435d4eb235fdf2eb8cbd8253ee59066e964e3c27bcd";
    JsonPath json;
    Map<String,Object> postMap= new HashMap<>();
    Faker faker=new Faker();
    List<Object> nameList=new ArrayList<>();
    List<String> emailList=new ArrayList<>();
    List<String> genderList=new ArrayList<>();


  @Test
  public void TC_10() {
      // new data creation with (name,email,gender,status) wrong type of name
      // farkli turleri  kabul ettigini dogrula

      nameList.add(100);
      nameList.add("200");
      nameList.add(515.251);
      nameList.add('Z');

      for (int i = 0; i <4 ; i++) {
          postMap.put("name",nameList.get(i));
          postMap.put("email",faker.internet().emailAddress());
          postMap.put("gender", "male");
          postMap.put("status","active");
          response= given().
                  contentType(ContentType.JSON).
                  body(postMap).
                  auth().oauth2(token).
                  when().post(endPoint);
          json=response.jsonPath();
          String code=json.getString("code");
          Assert.assertEquals(code,"201");


          response.prettyPrint();
      }

  }

  @Test
    public void TC_11(){
    //new data creation with (name,email,gender,status) with wrong type of email
      //@ isareti olmadan,,,@'den once ve  sonra en az kac harf girilmeli

      emailList.add("xytz.gmail.com");
      emailList.add("@gmail.com");
      emailList.add("kjln@");
      emailList.add("klm@.com");
      //emailList.add("lkjjmsd@gmail.com");


      for (int i = 0; i <4 ; i++) {
          postMap.put("name",faker.name().fullName());
          postMap.put("email",emailList.get(i));
          postMap.put("gender", "male");
          postMap.put("status","active");
          response= given().
                  contentType(ContentType.JSON).
                  body(postMap).
                  auth().oauth2(token).
                  when().post(endPoint);
          json=response.jsonPath();
          String code=json.getString("code");
          String fieldMessage=json.getString("data.field").concat(json.getString("data.message") );
          System.out.println("fieldMessage = " + fieldMessage);
          System.out.println("code = " + code);
          Assert.assertTrue((code.equals("422")&&(fieldMessage.equals("[email][is invalid]"))));


          response.prettyPrint();
      }

  }

  @Test
    public void TC_12(){

      //new data creation with (name,email,gender,status) wrong type of gender
      //can't be blank message should be received

      genderList.add("mle");
      genderList.add("fmale");
      genderList.add("unknown");

      for (int i = 0; i < 3; i++) {
          postMap.put("name",faker.name().fullName());
          postMap.put("email",faker.internet().emailAddress());
          postMap.put("gender",genderList.get(i));
          postMap.put("status","inactive");

          response=given().contentType(ContentType.JSON)
                  .auth().oauth2(token)
                  .body(postMap)
                  .when().post(endPoint);

          json=response.jsonPath();
          response.prettyPrint();
          Assert.assertEquals(json.getString("code"),"422");

         Assert.assertEquals(json.getList("data").toString(), "[{field=gender, message=can't be blank}]");
      }

  }





}
