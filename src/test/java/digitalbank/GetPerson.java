package digitalbank;

import io.restassured.http.ContentType;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
public class GetPerson {
    ClassLoader cl = getClass().getClassLoader();
String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc21pdGhAZGVtby5pbyIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfQVBJIn0seyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sImlhdCI6MTY5NDU1NjAzMiwiZXhwIjoxNjk0NTU5NjMyfQ.TqvGzR7Si8rGrbT_zYCZPmg3ZqXtCkDyZ1JLRScoZZU\"";
String URL = "http://13.53.168.89:8080";
//String URL = "http://localhost:8080";

/* 4 Is admin account, so if you get token from there use that. 79 or 71 is from Jhsmith */
    @Test
    @Parameters("id")
    public void getCustomerName(@Optional("88") int id){
        given().auth().preemptive().oauth2(accessToken)
                .when()
                .get(URL + "/bank/api/v1/user/" + id)
                .then()
                .body(containsString("id"))
                .body("id", equalTo(id))
                .statusCode(200);
    }

    @Test
    @Parameters("id")
    public void getSavingsAccount(@Optional("88") int id){
        given().auth().preemptive().oauth2(accessToken)
                .when()
                .get(URL + "/bank/api/v1/account/" + id)
                .then()
                .body(containsString("id"))
                .body("name", equalTo("Indiviudal Savings"))
                .body("accountType.id", equalTo(11))
                .statusCode(200);
    }

    @Test
    @Parameters("id")
    public void postDeposit(@Optional("88") int id) {
        String jsonString = "{\"amount\" : \"150\",\"description\" : \"Online Deposit\",\"transactionTypeCode\" : \"DPT\"}";
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonString)
                .when()
                .post(URL + "/bank/api/v1/account/" + id)
                .then()
                .body(containsString("id"))
                .statusCode(200);
    }

    @Test
    public void postDepositFile(){
        File requestBody = new File(cl.getResource("deposit.json").getFile());
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/sjekkmedlem")
                .then()
                .body(containsString("messageId"))
                .statusCode(200);
    }
}
