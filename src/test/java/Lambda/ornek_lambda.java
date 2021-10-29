package Lambda;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ornek_lambda {
    static void getPrint(Object t) {
        System.out.println(t + " ");
    }

    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<>(Arrays.asList(-5, -8, 2, -12, 0, 1, 12, 5, 6, 9, 15, 8));

        //12  ->  7-12 skip(my.length-5

        // S1:listi aralarinda bosluk birakarak yazdiriniz //
        myList.stream().forEach(ornek_lambda::getPrint);

        //S2: sadece negatif olanlari yazdir
        myList.stream().filter(t -> t < 0).forEach(System.out::println);

        //S3: pozitif olanlardan yeni bir liste olustur
        List<Integer> collect = myList.stream().filter(t -> t > 0).collect(Collectors.toList());

        // S4: list in elemanlarin karelerinden yeni bir list olusturalim
        List<Integer> kareler = myList.stream().map(x -> x * x).collect(Collectors.toList());

        //S5 : list in elemanlarin karelerinden tekrarsiz yeni bir list olusturalim
        List<Integer> tekrarsizKare = myList.stream().map(t -> t * t).distinct().collect(Collectors.toList());
        Set<Integer> tekrarsizKareset = myList.stream().map(t -> t * t).collect(Collectors.toSet());

        //S6: listin elemanlarini kucukten buyuge siralayalim
        Object collect1 = myList.stream().sorted().collect(Collectors.toList());
        //ctrl + alt + v otomatik data type olusturuyor

        //S7: listin elemanlarini buyukten kucuge siralayalim
        List<Integer> collect2 = myList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        // S8: list pozitif elemanlari icn kuplerini bulup birler basamagi 5 olanlardan yeni bir list olusturalim
        myList.stream().filter(x -> x > 0).filter(y -> (y * y * y) % 10 == 5).collect(Collectors.toList());
        myList.stream().filter(x -> x > 0).map(m -> m * m * m).filter(y -> y % 10 == 5).collect(Collectors.toList());

        //S9: list pozitif elemanlari icn kuplerini bulup birler basamagi 5 olmayanlardan yeni bir list olusturalim
        myList.stream().filter(x -> x > 0).filter(y -> (y * y * y) % 10 != 5).collect(Collectors.toList());

        // S10 :list elemanlarini toplamini bulalim
        int reduce = myList.stream().reduce((x, y) -> x + y).get();
        int reduce2 = myList.stream().reduce(Integer::sum).get();

        // S11 : peek ornegi cozelim negatiplerin karelerinden list olusturalim
        //look but do not touch

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        myList
                .stream()
                .filter(x -> x < 0)
                .peek(e -> System.out.print(e + " : "))
                .map(y -> y * y)
                //.peek(e-> System.out.println(e+" ; "))
                .forEach(System.out::println);

        // S12 : listeden 5 den buyuk  sayi var mi
        boolean b = myList.stream().anyMatch(x -> x > 5);

        // S13 : listin tum elemanlari sifirdan kucukmu
        Assert.assertFalse(myList.stream().allMatch(x -> x < 0));

        // S14: listin 100 e esit elemani yok
        Assert.assertTrue(myList.stream().noneMatch(x -> x.equals(100)));

        // S15: listin sifira esit elemani yok
        //  Assert.assertTrue(myList.stream().noneMatch(x -> x.equals(0)));

        // S16 : limit listenin ilk 5 elemanini topla
        int reduce1 = myList.stream().limit(5).reduce((x, y) -> x + y).get();

        //S17: skip dizinin son bes elemaninin  listele
        List<Integer> sonbes = myList.stream().skip(myList.size() - 5).collect(Collectors.toList());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("***********");
        System.out.println();
        System.out.println();
        IntStream.iterate(myList.size() - 5, x -> x + 1)
                .peek(e -> System.out.print(e + " : "))
                .limit(5)
                .map(t -> myList.get(t))
                .forEach(System.out::println);
        //                  12-5=7                  7+1
//        IntStream.range(0, 3).forEach(System.out::println);
//        DoubleStream.iterate(1, x -> x + 2).limit(10).skip(3).forEach(System.out::println);
//        IntStream.iterate(0, x -> x + 2).limit(10).skip(3).forEach(System.out::println);
//        for (int i = myList.size()-5; i <myList.size() ; i++) {
//            System.out.println(myList.get(i));
//        }

    }

    @Test
    public void testName() {
        //abdullah abi hasan abi hatice abla erkan abi

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
        System.out.println(liste.stream().mapToInt(t -> t).max().getAsInt());
        int first = liste.stream().sorted(Comparator.reverseOrder()).findFirst().get();
        System.out.println();
        System.out.println(first);
        //1-en buyuk sayiyi bulalim
              /* Integer max = listOfIntegers
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
*/

        //2- Listedeki tek elemanların karelerini sıralı olarak ve tekrarsız bir şekilde listeye kaydeden metodu yazınız

        List<Integer> collect = liste.stream().filter(t -> t % 2 != 0).map(y -> y * y).sorted().distinct().collect(Collectors.toList());

        //  .

        // negatif sayilarin kupunu bularak bir listeye ekleyelim
        List<Integer> collect1 = liste.stream().filter(x -> x < 0).map(x -> x * x * x).collect(Collectors.toList());

    }


}






























     /*   myList.forEach(t -> System.out.print(t + " "));
        myList.stream().filter(t -> t < 0).forEach(System.out::println);
        List<Integer> positive = myList.stream().filter(t -> t > 0).collect(Collectors.toList());
        List<Integer> kareleri = myList.stream().map(t -> t * t).collect(Collectors.toList());
        List<Integer> kareleri_tekrarsiz = myList.stream().map(t -> t * t).distinct().collect(Collectors.toList());
        System.out.println();
        myList.stream().sorted().forEach(System.out::println);
        myList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        List<Integer> son5 = myList.stream().filter(t -> t > 0).map(t -> t * t * t).filter(t -> t % 10 == 5).collect(Collectors.toList());
        List<Integer> son5degil = myList.stream().filter(t -> t > 0).map(t -> t * t * t).filter(t -> t % 10 == 5).collect(Collectors.toList());
        int integer = myList.stream().reduce(Integer::sum).get();
        System.out.println();
        System.out.println(integer);
        boolean b = myList.stream().anyMatch(t -> t > 5);
        boolean b1 = myList.stream().allMatch(t -> t < 0);
        boolean b2 = myList.stream().noneMatch(t -> t.equals(100));
        boolean b3 = myList.stream().noneMatch(t -> t.equals(0));
        Integer integer1 = myList.stream().limit(5).reduce(Integer::sum).get();
        Integer integer2 = myList.stream().limit(5).reduce(Integer::sum).get();
*/