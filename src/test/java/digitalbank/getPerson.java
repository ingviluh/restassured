package digitalbank;

import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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




}
