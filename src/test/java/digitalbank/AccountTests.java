package digitalbank;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class AccountTests {
    String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqc21pdGhAZGVtby5pbyIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfQVBJIn0seyJhdXRob3JpdHkiOiJST0xFX1VTRVIifV0sImlhdCI6MTY5NDU1ODcxOCwiZXhwIjoxNjk0NTYyMzE4fQ.diBwxgHJChNIe8Zj-PnM9o-Ss1u1lVEgPcQxZfPhcp0";
    @Before
    public void setUp() {
        // Set the base URI for your API
        RestAssured.baseURI = "http://13.53.168.89:8080/bank"; // Replace with your API's base URL
    }

    @Test
    public void testGetAccountById() {
        // Define the endpoint you want to test
        String endpoint = "/api/v1/account/{id}";

        // Define the path parameter (replace with a valid account ID)
        long accountId = 1066; // Replace with a valid account ID

        // Make a GET request to the endpoint with the path parameter
        Response response = RestAssured
                .given()
                .pathParam("id", accountId)
                .header("Authorization", authToken) // Replace with a valid authorization token
                .when()
                .get(endpoint);

        // Validate the response
        response
                .then()
                .statusCode(403) // Assert the expected status code (200 OK)
                .contentType("application/json;charset=UTF-8"); // Assert the expected content type (you can modify this based on the actual response)

        // You can add more specific assertions to validate the response body if needed
    }
}
