package Lambda;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class Ornek_Cozumu {



    // Soru: Create List of square of all distinct numbers
    @Test
    public void Ornek01() {
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        numbers.stream().forEach(t->System.out.println(t*t*t));
    }
}
