package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;


public class AdminAuthHomePage extends BasePage {

    public AdminAuthHomePage(WebDriver driver) {
        super(driver);
    }

    // VALID_EMAIL = "exampleAdmin@gmail.com";
    // VALID_PASSWORD = "example1234admin";
    // invalidEmail = "invalid@example.com";
    // invalidPassword = "wrongpassword";


    private final String baseUrl = System.getProperty("BASE_URL", "https://hop-admin-angular.onrender.com");
    private final String FIELD_PASSWORD = ("//*[@placeholder='Введіть пароль']");
    private final String FIELD_EMAIL = ("//*[@placeholder='Введіть email']");
    private final String LOGIN_BUTTON = ("//button[@type='submit']");
    private final String ERROR_MESSAGE = ("//div[@class='login-error']");
    private final String LOGOUT_BUTTON = ("//button//span[contains(text(), 'Log out')]");

    public void openAdminHomePage() {
        driver.get(baseUrl);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                webDriver -> Objects.equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState"), "complete")
        );
    }

    public void enterValidEmail(String email) {
        waitElementToBeVisible(FIELD_EMAIL).sendKeys(email);
    }


    public void enterValidPassword(String password) {
        waitElementToBeVisible(FIELD_PASSWORD).sendKeys(password);
    }


    public void enterInValidEmail(String email) {
        waitElementToBeVisible(FIELD_EMAIL).sendKeys(email);
    }


    public void enterInValidPassword(String password) {
        waitElementToBeVisible(FIELD_PASSWORD).sendKeys(password);
    }


    public void clickLoginButton() {
        waitElementToBeVisible(LOGIN_BUTTON).click();
    }


    public String getErrorMessage() {
        try {
            return waitElementToBeVisible(ERROR_MESSAGE).getText();
        } catch (Exception e) {
            System.out.println("Error finding message: " + e.getMessage());
            return "";
        }
    }

    public void clickLogoutButton() {
        waitElementToBeVisible(LOGOUT_BUTTON).click();
    }


    public boolean isLoginFormDisplayed() {
        try {
            return waitElementToBeVisible(FIELD_EMAIL).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDashboardDisplayed() {
        try {
            return waitElementToBeVisible("//h1[contains(text(), 'Dashboard')]").isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public String getToken() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return window.localStorage.getItem('token');");
    }

    public void clearToken() {
        ((JavascriptExecutor) driver).executeScript("window.localStorage.removeItem('token');");
    }
}