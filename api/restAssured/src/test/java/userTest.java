import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class userTest extends baseTest  {

    private String name;

    private  String PathURL = "/v1/examen/randomName";

    @Test
    public void putNameWithOutBody() {

        request
                .put(String.format("https://api-coffee-testing.herokuapp.com/v1/examen/updateName"))
                .then()
                .statusCode(406)
                .body("message",equalTo("Name was not provided"));
    }

    @Test
    public void getRandomNameWithoutLoginInfo() {

        request
                .get(String.format("%s", PathURL))
                .then()
                .statusCode(401)
                .body("message",equalTo("Please login first"));

    }

    @Test
    public void getRandomNameWithLoginData() {

        name =
                given()
                        .auth()
                        .basic("testuser","testpass")
                        .get(String.format("%s", PathURL))
                        .then()
                        .extract()
                        .path("name");

        System.out.println(name);
    }

    @Test
    public void sameName() {

        request
                .body("{\"name\":\""+name+"\"}")
                .post(String.format("https://api-coffee-testing.herokuapp.com/v1/examen/sameName"))
                .then()
                .statusCode(200)
                .body("name",equalTo(name));

        System.out.println(name);
    }

}
