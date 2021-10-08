package Lambda;

public class Lambda {
    public static void getPrint(Object str) {
        System.out.print(str + " : ");
    }

    public static boolean getEven(int t) {
        return t % 2 == 0;
    }

    public static boolean getOdd(int number) {
        return number % 2 != 0;
    }

    public static boolean getThree(int sayi){
        return  sayi % 3 ==0;
    }



    public static boolean filter_k(String s) {
        return s.contains("k");
    }
}


