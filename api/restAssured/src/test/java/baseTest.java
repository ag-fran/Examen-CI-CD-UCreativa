import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.TestNGUtils;
import org.testng.annotations.*;

public class baseTest {
    public RequestSpecification request;


    @BeforeClass
    @Parameters ("baseURL")
    public void SetupRestAssured(@Optional String baseUrl){
        if (baseUrl == null){  baseUrl = "https://api-coffee-testing.herokuapp.com"; }
        RestAssured.baseURI = baseUrl;

    }
    @BeforeMethod
    public void before(){
        request=RestAssured.given();

    }
}