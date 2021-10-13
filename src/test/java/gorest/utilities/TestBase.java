package gorest.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected RequestSpecification spec01;
    protected RequestSpecification getSpec01;

    @BeforeMethod
    public void setUp01(){
        spec01 = new RequestSpecBuilder().
                setBaseUri("https://gorest.co.in/public-api/users").
                build();

        getSpec01 = new RequestSpecBuilder().setBaseUri("https://gorest.co.in/public-api").build();
    }
}
