package Lambda;

import gorest.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Grup_Calismasi_Lambda {
    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(9);
        list.add(13);
        list.add(4);
        list.add(6);
        list.add(2);
        list.add(4);
        list.add(12);
        list.add(15);

        for (int w : list
        ) {
            System.out.print(w + " ");

        }
        System.out.println();
        System.out.println("**********");
        list.stream().forEach(t -> System.out.print(t + " "));
//        list.stream().forEach(System.out::println);
        System.out.println();
        list.stream().forEach(Lambda::getPrint);
//forEach her liste icindeki herbir elemana islem yaptiriyoruz
        // (Class :: Method)
        //sadece double olanlari yazdiralim
        System.out.println();
        list.stream().filter(t -> t % 2 == 0).forEach(t -> System.out.print(t + " , "));

        System.out.println();
        //functional programing
        list.stream().filter(Lambda::getEven).forEach(Lambda::getPrint);

        System.out.println();
        //structured programing
        for (int w : list
        ) {
            if (w % 2 == 0) {
                System.out.print(w + " ");
            }
        }

        //sadece odd olanlari yazdiralim

        for (int w : list
        ) {
            if (w % 2 != 0) {
                System.out.print(w + " ");
            }
        }

        list.stream().filter(t -> t % 2 != 0).forEach(t -> System.out.print(t + " ; "));
        list.stream().filter(Lambda::getOdd).forEach(Lambda::getPrint);

        //3 e bolunenleri yazdir
        System.out.println();
        list.stream().filter(Lambda::getThree).forEach(Lambda::getPrint);
        System.out.println();

        //Print the cubes(a*a*a) of all even elements by using "Functional Programming"
        list.stream().filter(Lambda::getEven).forEach(t -> System.out.print(t * t * t + " "));


    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<String>();
        list.add("Ali");  //3
        list.add("Mark");  //4 5
        list.add("Jackson"); //8
        list.add("Amanda");
        list.add("Chris");
        list.add("Tucker"); //7

        //ilk harfi A olan isimleri yazdir
        list.stream().filter(t -> t.charAt(0) == 'A').forEach(Lambda::getPrint);
        //filtrele ilk index A
        // herbirini yazdir
        System.out.println();
        list.stream().filter(t -> t.charAt(0) == 'A').forEach(System.out::println);
        System.out.println();
        for (String t : list
        ) {
            if (t.charAt(0) == 'A') {
                System.out.println(t);
            }
        }


        // karakter sayisi 3 den fazla olanlari yazdir
        System.out.println();
        list.stream().filter(t -> t.length() > 3).forEach(System.out::println);

        //Print the number of characters of every element on the console by using "Functional Programming"
        list.stream().map(t -> t.length()).forEach(t -> System.out.print(t + " @ "));
//Ali  -> 3


        // karakter sayisi 5 den fazla olan isimleri buyuk harf olarak yazdiralim
// Ali  ->  ALI
        System.out.println();
        list.stream().filter(t -> t.length() > 5).map(String::toUpperCase).forEach(Lambda::getPrint);
        System.out.println();
        list.stream().filter(t -> t.length() > 5).forEach(t -> System.out.println(t.toUpperCase() + " "));

        System.out.println();
        System.out.println(list);
        System.out.println();
        int sum = 0;
        //Print the total number of characters of all elements
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).length();
        }
        System.out.println(sum);

        int toplam = list.stream().map(String::length).reduce(0, Integer::sum);
        System.out.println();
        System.out.println(toplam);


    }

    @Test
    public void day2() {

        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(9);
        list.add(13);
        list.add(4);
        list.add(6);
        list.add(2);
        list.add(4);
        list.add(12);
        list.add(15);
        List<String> listString = new ArrayList<>();
        listString.add("Ali");
        listString.add("Mark");
        listString.add("Jackson");
        listString.add("Amanda");
        listString.add("Chris");
        listString.add("Tucker");
        //H.W
           /*
         1)Butun isimlerin sonuna '!' ekle
		 2)icerisinde 'k' olan isimlerin karakter sayilarini topla
         3)'A' ile baslayan isimlerin karakter sayilarinin carpimini bul
          */

        listString.stream().forEach(t -> System.out.println(t + "!"));
        listString.stream().map(t -> t + "!").forEach(Lambda::getPrint);
        Optional<Integer> toplam = listString.stream().filter(t -> t.contains("k")).map(String::length).reduce(Integer::sum);
        System.out.println();

        //listString.stream().filter(Lambda::filter_k).map(String::length).forEach(Lambda::getPrint);
        listString.stream().filter(t -> t.contains("k")).map(String::length).forEach(Lambda::getPrint);
        System.out.println();
        System.out.println(toplam);
        // butun isimleri yan yana yazdir
        System.out.println();
        listString.stream().forEach(t -> System.out.print(t));
//String birlestir = listString.stream().reduce((x,y)->x+y);
        String x = "ali";
        String y = "mark";
        String xy = x + y;
        System.out.println(xy); //alimark

        //ali
        //mark
        // Jasckson

//         Find the sum of squares of odd integer elements of the list by using Functional Programming.
        //tek karesinin toplamini
        // 2 -> 2*2
//        list.stream().filter(Lambda::getOdd).map(t->t*t).reduce(Integer::sum)
        //Optional<Integer> top=   list.stream().filter(t->t%2!=0).map(t->t*t).reduce((h, k)->h+k);

        //sorted()

        //Print all elements in natural order
        System.out.println();
        listString.stream().forEach(Lambda::getPrint);
        System.out.println();
        listString.stream().sorted(Comparator.naturalOrder()).forEach(Lambda::getPrint);
        //Print all elements in reverse order in a list
        System.out.println();
        list.stream().forEach(Lambda::getPrint);
        System.out.println();
        list.stream().sorted(Comparator.reverseOrder()).forEach(Lambda::getPrint);
        //Print all elements sorted according to the length
        System.out.println();
        listString.stream().sorted(Comparator.comparing(t -> t.length())).forEach(Lambda::getPrint);
        //Print all elements sorted according to the last character
        System.out.println();
        listString.stream().sorted(Comparator.comparing(t -> t.charAt(t.length() - 1))).forEach(Lambda::getPrint);


        //Collectors


        //Put in the List of elements whose lengths are less than 6 in alphabetical order

        List<String> a = listString.stream().filter(t -> t.length() < 6).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.println();
        System.out.println(a);
        //Print the minimum value of the lengths of the elements
        Optional<String> first = listString.stream().sorted(Comparator.comparing(t -> t.length())).findFirst();
        Optional<String> first2 = listString.stream().min(Comparator.comparing(String::length));
        System.out.println();
        System.out.println(first);
        //Print distinct elements in natural order on the console
        System.out.println();
        list.forEach(Lambda::getPrint);
        System.out.println();
        list.stream().distinct().forEach(Lambda::getPrint);
        //Print all elements whose lengths are less than 5, in natural order, in a Set
        Set<String> stringSet = listString.
                stream().
                filter(t -> t.length() < 5).
                //sorted(Comparator.naturalOrder()).
                        collect(Collectors.toCollection(TreeSet::new));
        System.out.println();
        System.out.println(stringSet);


        //skip()

        //natural order yap , ilk uc elemani atla System.out.println();
        listString.stream().sorted(Comparator.naturalOrder()).forEach(Lambda::getPrint);
        System.out.println();
        listString.stream().sorted(Comparator.naturalOrder()).skip(3).forEach(Lambda::getPrint);


        //limit()

        //natural order yap , ilk uc elemani yazdir
        System.out.println();
        listString.stream().sorted(Comparator.naturalOrder()).limit(3).forEach(Lambda::getPrint);


    }

    @Test
    public void day3() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("abdullah", "abi", 38, Arrays.asList(123456, 454545)));
        personList.add(new Person("erkan", "abi", 37, Arrays.asList(5457878, 4548787)));
        personList.add(new Person("hasan", "abi", 36, Arrays.asList(2131231, 21312312)));
        personList.add(new Person("omer", "abi", 35, Arrays.asList(2312312, 1231231)));
        personList.add(new Person("pinar", "abla", 30, Arrays.asList(2323213, 1231231)));
        personList.add(new Person("hatice", "abla", 31, Arrays.asList(324567, 4653)));

        /*        homework
         */
        //how many abla
        long abla = personList.stream().filter(t -> t.surname.equals("abla")).count();
        System.out.println(abla);
        System.out.println();
        //who are the abi elder than 36
        personList.stream().filter(t -> t.age > 36 && t.surname.equals("abi")).forEach(t -> System.out.println(t.name));
        System.out.println();
        System.out.println("***");
        System.out.println();
        //ismi en uzun olan abla
//persons.stream().min((p1, p2) -> p1.getBrithdate().compareTo(p2.getBrithdate()))
//        personList.stream().((p1,p2)->p1.name.length())
        System.out.println("////////");
        personList.stream().filter(t -> t.surname.equals("abla")).sorted((Comparator.comparingInt(person -> person.name.length()))).forEach(t -> System.out.println(t.name));
        System.out.println();
        personList.stream().map(t -> t.phoneNumber).forEach(Lambda::getPrint);
        System.out.println();
        personList.stream().flatMap(t -> t.phoneNumber.stream()).forEach(Lambda::getPrint);
        //toMap isim ve yas olan map olustur
        Map<String, Integer> collect = personList.stream().collect(Collectors.toMap(x -> x.name, y -> y.age));
        System.out.println();
        System.out.println(collect);
        System.out.println();
        //yas ve surname olan map olustur
        Map<Integer, String> collect1 = personList.stream().collect(Collectors.toMap(k -> k.age, v -> v.surname));
        System.out.println();
        System.out.println(collect1);
        System.out.println();
        //yas ve dogum yili olan map olustur

        Map<Integer, Integer> collect2 = personList.stream().collect(Collectors.toMap(k -> k.age, v -> 2021 - v.age));

        System.out.println();
        System.out.println(collect2);
        System.out.println();
        //butun isimleri listeye ekle
        List<String> collect3 = personList.stream().map(t -> t.name).collect(Collectors.toList());
        System.out.println();
        System.out.println(collect3);
        System.out.println();
        //flatMap
        List<List<Integer>> collect4 = personList.stream().map(t -> t.phoneNumber).collect(Collectors.toList());
        System.out.println();
        System.out.println(collect4);
        System.out.println();
        List<Integer> collect5 = personList.stream().flatMap(t -> t.phoneNumber.stream()).collect(Collectors.toList());
        System.out.println();
        System.out.println(collect5);
        //Matchers
        //any
        //all
        //none

        //array lambda 3 ways

        int[] way_1 = {25, 13, 19, 18, 5, 87};
        int[] way_2 = {252, 12, 9, 13, 5, 8};
        int[] way_3 = {25, 12, 9, 13, 5, 8};
        String[] way_3_string = {"mahmut", "okkes", "murtaza"};
        System.out.println("**************");
        System.out.println();
        //1 way
        Arrays.stream(way_1).forEach(Lambda::getPrint);
        //2 way
        IntStream way_2_stream = Arrays.stream(way_2);
        System.out.println();
        way_2_stream.forEach(Lambda::getPrint);
        System.out.println();
        //3 way
        List<int[]> way_3_list = Arrays.asList(way_2);
        System.out.println();
        way_3_list.stream().forEach(t -> System.out.println(Arrays.toString(t)));
        System.out.println();
        //takedrop whiledrop
        System.out.println();
        Arrays.stream(way_2).dropWhile(t -> t % 2 == 0).forEach(Lambda::getPrint);
        System.out.println();
        Arrays.stream(way_2).takeWhile(t -> t % 2 == 0).forEach(Lambda::getPrint);


        //way 1
        //Arrays.stream(way_1).sorted().forEach(Lambda::getPrint);
        //way 2
        List<int[]> way_2_list = Arrays.asList(way_2);
        //      way_2_list.stream().sorted().forEach(t -> System.out.println(Arrays.toString(t)));
        //way 3 stream kullanilmiyor cunku zaten kullandik
        IntStream way_3_stream = Arrays.stream(way_3);
//        way_3_stream.sorted().forEach(Lambda::getPrint);
        Stream stream = Arrays.stream(way_3_string);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Arrays.stream(way_2).dropWhile(t -> t % 2 == 0).forEach(Lambda::getPrint);
        System.out.println();
        Arrays.stream(way_2).takeWhile(t -> t % 2 == 0).forEach(Lambda::getPrint);
//persons.removeIf(p -> p.getGender().equals(Gender.WOMAN));

    }

    @Test
    public void testName() {
        List<String> almak_istenenler = Arrays.asList("tomatoes", "pepper", "milk", "banana");
        List<String> alinanlar = Arrays.asList("pepper", "banana");
        List<String> alinmayacaklar = Arrays.asList("tea", "sugar");

        Map<String, Product> map_in_the_cart = new HashMap<>();
        map_in_the_cart.put("uzum", new Product("$2.5", "3", "$7.5"));
        map_in_the_cart.put("limon", new Product("£3", "6", "£18"));
        map_in_the_cart.put("elma", new Product("£5", "2", "£10"));
        map_in_the_cart.put("karpuz", new Product("£2", "9", "£18"));
        map_in_the_cart.put("kereviz", new Product("£3", "6", "£18"));

        double total_Price = 71.5;

        Map<String, Product> map_on_the_invoice = new HashMap<>();
        map_on_the_invoice.put("uzum", new Product("$2.5", "3", "$7.5"));
        map_on_the_invoice.put("limon", new Product("£3", "6", "£18"));
        map_on_the_invoice.put("elma", new Product("£5", "2", "£10"));
        map_on_the_invoice.put("karpuz", new Product("£2", "9", "£18"));
        map_on_the_invoice.put("kereviz", new Product("£3", "6", "£18"));


//        map_on_the_invoice.put(1,new Product("uzum","$2.5","3","$7.5"));
//        map_on_the_invoice.put(2,new Product("limon","£3","6","£18"));
//        map_on_the_invoice.put(3,new Product("elma","£5","2","£10"));
//        map_on_the_invoice.put(4,new Product("karpuz","£2","9","£18"));
//        map_on_the_invoice.put(5,new Product("kereviz","£3","6","£18"));
//


        //her bir product in total price i price * quantity mi
        //total price toplam price a esit mi
        //invoice deki urunlerle cart deki urunler ayni mi

    }
@Test
    public void shopping() {
        //soru 1


        List<String> almak_istenenler = Arrays.asList("tomatoes","pepper","milk","banana");
        List<String> alinanlar = Arrays.asList("pepper","banana");
        List<String> alinmayacaklar = Arrays.asList("tea","sugar");

        //market listemiz var alinanlarin hepsi almak istenenlerin icinde olacak ama alinmayacaklarin
        // hicbiri almak istenenlerin icinde olmayacak
       // List<String> marketList=almak_istenenler.stream().takeWhile(alinanlar::contains).dropWhile(alinmayacaklar::contains).collect(Collectors.toList());
        List<String> marketList=almak_istenenler.stream().filter(t->alinanlar.contains(t)&&!alinmayacaklar.contains(t)).collect(Collectors.toList());
        System.out.println("marketList = " + marketList);
    }
    @Test
    public void VAT() {

        // Applying 12% VAT on each purchase
        // Old way:
//         List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//         double total = 0;
//         for (Integer cost : costBeforeTax) {
//             double price = cost + .12*cost; total = total + price; }
//         System.out.println("Total : " + total);
        // New way:
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        Optional<Double> bill=costBeforeTax.stream().map(t->t*1.12).reduce(Double::sum);
        //double bill=costBeforeTax.stream().map(t->t*1.12).reduce(Double::sum).get();

//        double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);
        //Output Total : 1680.0 Total : 1680.0

    }
    @Test
        public void Joining(){
            // Convert String to Uppercase and join them using coma
            List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
            String G7Countries = G7.stream() .map(x -> x.toUpperCase()) .collect(Collectors.joining(", "));
            System.out.println(G7Countries);
            //Output : USA, JAPAN, FRANCE, GERMANY, ITALY, U.K., CANADA

            //Read more: https://javarevisited.blogspot.com/2014/02/10-example-of-lambda-expressions-in-java8.html#ixzz79pg6fYRx

        }
        @Test
        public void distinctNumber(){
            //Soru: Create List of square of all distinct numbers
            List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);

            //List<Integer> newList=numbers.stream().distinct().map(t->t*t).collect(Collectors.toList());
            Set<Integer> newList=numbers.stream().map(t->t*t).collect(Collectors.toSet());


//            List<Integer> newList=numbers.stream().map(t->t*t).distinct().collect(Collectors.toList());
            System.out.println("newList = " + newList);
        }
        @Test
        public void numbers(){
            List<Integer> liste = new ArrayList<>();
            liste.add(12);
            liste.add(9);
            liste.add(-4);
            liste.add(13);
            liste.add(4);
            liste.add(-12);
            liste.add(9);
            liste.add(2);
            liste.add(4);
            liste.add(12);
            liste.add(15);
            liste.add(500);


            //1-en buyuk sayiyi bulalim
            int max= liste.stream().sorted(Comparator.reverseOrder()).findFirst().get();
            System.out.println("max = " + max);


            //2- Listedeki tek elemanların karelerini sıralı olarak ve tekrarsız bir şekilde
            List<Integer> squareOfDistinctodds=liste.stream().filter(t->t%2!=0).map(t->t*t).sorted(Comparator.naturalOrder()).distinct().collect(Collectors.toList());
            System.out.println("squareOfDistinctodds = " + squareOfDistinctodds);
            //  listeye kaydeden metodu yazınız.


            // negatif sayilarin kupunu bularak bir listeye ekleyelim
            List<Integer> negativeNumbers=liste.stream().filter(t->t<0).map(t->t*t*t).collect(Collectors.toList());
            System.out.println("negativeNumbers = " + negativeNumbers);
        }
        @Test
        public void namesx (){
            List<String> list = new ArrayList<>();
            list.add("Ali");
            list.add("Can");
            list.add("Veli");
            list.add("Kan");
            list.add("Ayse");
            list.add("Emine");
            list.add("Ali");
            list.add("Emel");
            list.add("Veli");


            /// a/A ile baslayan ve uzunlugu 5 ten kucuk olan isimleri bulalim

            List <String> Anames=list.stream().filter(t->t.substring(0,1).toUpperCase().equals("A")&&t.length()<5).collect(Collectors.toList());
            System.out.println("Anames = " + Anames);
            ///i ile biten farkli isimleri uygun bir collectiona koyalim
            List<String> iNames=list.stream().filter(t->t.charAt(t.length()-1)=='i').collect(Collectors.toList());
            System.out.println("iNames = " + iNames);

    }







}





