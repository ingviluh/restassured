package digitalbank;

import org.junit.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class getPerson {

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
