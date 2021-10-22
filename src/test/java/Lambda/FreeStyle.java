package Lambda;

import com.fasterxml.jackson.databind.ObjectMapper;
//import gorest.pojos.ApiGo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class FreeStyle {
    Response response;
    String endpoint = "https://gorest.co.in/public-api/users/";
    ObjectMapper objectMapper = new ObjectMapper();
   // ApiGo apiGoPojo;
    JsonPath json;
    String token = "3158b67b6b0e956ecb5a1f06fe311f94a45c5f6268f56db7272f51e75f050304";
    data_lambda data_lambda = new data_lambda();
    List<Integer> id = new ArrayList<>(Arrays.asList(data_lambda.class_allid));
    List<String> email = new ArrayList<>(Arrays.asList(data_lambda.class_allEmail));
    List<String> name = new ArrayList<>(Arrays.asList(data_lambda.class_allName));
    List<String> gender = new ArrayList<>(Arrays.asList(data_lambda.class_allGender));
    List<Integer> l = new ArrayList<>(Arrays.asList(2, 121, 211, 2, 7));
    Set<Integer> t = new HashSet<>(l);

    @Test
    public void testName() {

        //id natural order assertion
        List<Integer> id_natural_order = id.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        Assert.assertNotEquals(id, id_natural_order);
        //id unique assertion
        Set<Integer> id_set = new HashSet<>(id);
//        Set<Integer> id_set = id.stream().collect(Collectors.toSet());
        List<Integer> id_set2 = id.stream().distinct().collect(Collectors.toList());
        Assert.assertEquals(id.size(), id_set.size());

        //names are not NULL assertion
        List<String> null_name = name.stream().filter(t -> t != null).collect(Collectors.toList());
        // List<String> null_name = name.stream().filter(Objects::nonNull).collect(Collectors.toList());
//        Assert.assertTrue(name.stream().noneMatch(t->t.equals(null)));
        Assert.assertTrue(name.stream().noneMatch(Objects::isNull));
//        Assert.assertFalse(name.containsAll(null));
        // System.out.println(null_name2);

        //female sayisi daha mi fazla

        //search for id(2492) assertion 2492
        int count = (int) id.stream().filter(t -> t.equals(22)).count();
        Assert.assertNotNull(count);

        Assert.assertTrue(id.stream().anyMatch(t -> t.equals(22)));
        //search for name("Bhaswar Varrier") assertion
        Assert.assertTrue(name.stream().anyMatch(t -> t.equals("Bhaswar Varrier")));
        //search for email("bodhan_ganaka@paucek.name")assertion
        Assert.assertTrue(email.stream().anyMatch(t -> t.equals("bodhan_ganaka@paucek.name")));
        Assert.assertTrue(email.stream().anyMatch(t -> t.contains("bodhan_ganaka@paucek.name")));
        //count emails "@gmail.com" assertion
        Assert.assertTrue(email.stream().anyMatch(t -> t.contains("@gmail.com")));
        //counts surname begins with A and D assertion
        int count1 = (int) name.stream().filter(t -> t.charAt(0) == 'A' || t.charAt(0) == 'D').count();
        Assert.assertTrue(count1 >= 1);

        Assert.assertTrue(name.stream().anyMatch(t -> t.charAt(0) == 'A' || t.charAt(0) == 'D'));
        //check duplicate names
        Set<String> collect = name.stream().collect(Collectors.toSet());
        Set<String> collect2 = new HashSet<>(name);
        Assert.assertEquals(name.size(), collect.size());




        // Applying 12% VAT on each purchase
        // Old way:
//         List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//         double total = 0;
//         for (Integer cost : costBeforeTax) {
//             double price = cost + .12*cost; total = total + price; }
//         System.out.println("Total : " + total);
         // New way:
        List <Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream() .map((cost) -> cost + .12*cost) .reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
        //Output Total : 1680.0 Total : 1680.0

    }


}
