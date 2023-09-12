package digitalbank;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class GetPersonTests {

    @BeforeTest
    public static String getToken() {


        RestAssured.baseURI = "http://localhost:8080/bank/";

        RequestSpecification request = RestAssured.given();

        String payload = "{\r\n" +
                "  \"userName\": \"admin@demo.io\",\r\n" +
                "  \"password\": \"Demo123!\"\r\n" +
                "}";

        request.header("Content-Type","application/json");

        Response responseFromGenerateToken = request.body(payload).post("/api/v1/auth");

        String jsonString = responseFromGenerateToken.getBody().asString();

        String tokenGenerated = JsonPath.from(jsonString).get("token");

        request.header("Authorization","Bearer "+tokenGenerated)
                .header("Content-Type","application/json");

        return tokenGenerated;


    }
    @Test
    public static void getUsers() {
        given()
                .header("Authorization", "Bearer " + getToken())
                .when()
                .get("/users")
                .then()
                .statusCode(200);
    }

    @Test
    public void getTest() {
        given()
                .headers(
                        "Authorization",
                        "Bearer " + getToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .get("http://localhost:8080/bank/api/v1/users")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();
    }


    @BeforeClass
    public void setupTest() throws IOException {
        basePath = "/api/v1/";
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        prop.load(input);
        baseURI = prop.getProperty("URL");
        authentication = basic(prop.getProperty("username"), prop.getProperty("password"));
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @Parameters("id")
    public void getCustomerName(@Optional("79") String id){
        given()
                .when()
                .get("/account/" + id)
                .then()
                .body(containsString("id"))
                .body("id", equalTo(id))
                .statusCode(200);
    }
}
