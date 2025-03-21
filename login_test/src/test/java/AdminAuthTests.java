import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AdminAuthHomePage;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AdminAuthTests extends TestInit {

    public AdminAuthHomePage adminAuthHomePage;
    private String adminUsername;
    private String adminPassword;
    private String baseUrl;

    @Override
    @BeforeEach
    public void setupTest() {
        super.setupTest();
        adminAuthHomePage = new AdminAuthHomePage(driver);

        adminUsername = System.getProperty("ADMIN_USERNAME", "exampleAdmin@gmail.com");
        adminPassword = System.getProperty("ADMIN_PASSWORD", "example1234admin");
        baseUrl = System.getProperty("BASE_URL", "https://hop-admin-angular.onrender.com");
    }

    @Test
    public void testSuccessfulLogin() {
        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterValidEmail(adminUsername);
        adminAuthHomePage.enterValidPassword(adminPassword);
        adminAuthHomePage.clickLoginButton();

        boolean isDashboardDisplayed = adminAuthHomePage.isDashboardDisplayed();
        Assertions.assertTrue(isDashboardDisplayed, "Сторінка Dashboard не відображається після входу");
    }

    @Test
    public void testInvalidLogin() {
        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterInValidEmail("invalid@example.com");
        adminAuthHomePage.enterInValidPassword("wrongpassword");
        adminAuthHomePage.clickLoginButton();

        Assertions.assertTrue(adminAuthHomePage.getErrorMessage()
                        .contains("Логін або Пароль не вірні"),
                "Повідомлення про помилку не відображається");
    }

    @Test
    public void testSessionInvalidationAfterLogout() {
        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterValidEmail(adminUsername);
        adminAuthHomePage.enterValidPassword(adminPassword);
        adminAuthHomePage.clickLoginButton();
        adminAuthHomePage.clickLogoutButton();

        Assertions.assertTrue(adminAuthHomePage.isLoginFormDisplayed(), "Сесія не була інвалідована");
    }

    @Test
    public void testLogoutFunctionality() {
        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterValidEmail(adminUsername);
        adminAuthHomePage.enterValidPassword(adminPassword);
        adminAuthHomePage.clickLoginButton();

        boolean isDashboardDisplayed = adminAuthHomePage.isDashboardDisplayed();
        Assertions.assertTrue(isDashboardDisplayed, "Сторінка Dashboard не відображається після входу");

        adminAuthHomePage.clickLogoutButton();

        boolean isLoginFormDisplayed = adminAuthHomePage.isLoginFormDisplayed();
        Assertions.assertTrue(isLoginFormDisplayed, "Сторінка входу не відображається після виходу");
    }

    @Test
    public void testInvalidLoginErrorMessage() {
        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterInValidEmail("invalid@example.com");
        adminAuthHomePage.enterInValidPassword("wrongpassword");
        adminAuthHomePage.clickLoginButton();

        String errorMessage = adminAuthHomePage.getErrorMessage();
        Assertions.assertEquals("Логін або Пароль не вірні", errorMessage, "Повідомлення про помилку не відповідає очікуваному");
    }

    @Test
    public void testRedirectToDashboardAfterLogin() {
        adminAuthHomePage.openAdminHomePage();
        adminAuthHomePage.enterValidEmail(adminUsername);
        adminAuthHomePage.enterValidPassword(adminPassword);
        adminAuthHomePage.clickLoginButton();

        String expectedUrl = baseUrl + "/layout/dashboard";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (TimeoutException e) {
            String errorMessage = adminAuthHomePage.getErrorMessage();
            Assertions.fail("Перенаправлення на Dashboard не відбулося. Повідомлення про помилку: " + errorMessage);
        }
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, currentUrl, "Після входу не відбувся перехід на сторінку Dashboard");
    }
}

