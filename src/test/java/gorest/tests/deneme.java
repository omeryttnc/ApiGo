package gorest.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class deneme {

    @Test
    public void testName() {
        System.out.println("aa");
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("aa");
    }
}
