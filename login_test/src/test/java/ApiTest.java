import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.fail;


public class ApiTest {

    private static String accessToken;

    public static void main(String[] args) {

        Response authResponse = given()
                .header("Content-Type", "application/json")
                .body("{\"email\": \"exampleAdmin@gmail.com\", \"password\": \"example1234admin\"}")
                .post("https://hopoasis.onrender.com/auth/login");

        accessToken = authResponse.jsonPath().getString("access_token");
        System.out.println("Access Token: " + accessToken);
    }

    @Test
    public void testAdminAccessDeniedForNonAdmin() {
        given()
                .header("Authorization", "Bearer " + "invalidToken")
                .when()
                .get("https://hopoasis.onrender.com/admin/users")
                .then()
                .statusCode(403); // Перевіряємо, що доступ заборонено
    }
}

