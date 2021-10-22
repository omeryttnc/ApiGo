package Lambda;

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
        list.stream().filter(t -> t !=null);
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
        personList.add(new Person("hatice", "abla", 30, Arrays.asList(324567, 4653)));

        /*
        homework
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

        System.out.println("////////");
     personList.stream().filter(t -> t.surname.equals("abla")).sorted((Comparator.comparingInt(person -> person.name.length()))).forEach(t-> System.out.println(t.name));



        //toMap isim ve yas olan map olustur
        //yas ve dogum tarihi olan map olustur

        //flatMap

        //Matchers
        //any
        //all
        //none

        //array lambda 3 ways

        int[] way_1 = {12, 9, 13, 5, 8};
        int[] way_2 = {12, 9, 13, 5, 8};
        int[] way_3 = {12, 9, 13, 5, 8};
        String[] way_3_string = {"mahmut", "okkes", "murtaza"};


        //takedrop whiledrop


        //way 1
        //Arrays.stream(way_1).sorted().forEach(Lambda::getPrint);
        //way 2
        List<int[]> way_2_list = Arrays.asList(way_2);
        //      way_2_list.stream().sorted().forEach(t -> System.out.println(Arrays.toString(t)));
        //way 3 stream kullanilmiyor cunku zaten kullandik
        IntStream way_3_stream = Arrays.stream(way_3);
//        way_3_stream.sorted().forEach(Lambda::getPrint);
        Stream stream = Arrays.stream(way_3_string);


        //  Arrays.stream(way_1).dropWhile(t -> t % 2 == 0).forEach(Lambda::getPrint);
        // Arrays.stream(way_1).takeWhile(t -> t % 2 == 0).forEach(Lambda::getPrint);


    }




}
