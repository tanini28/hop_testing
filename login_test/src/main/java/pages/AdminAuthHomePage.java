package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AdminAuthHomePage extends BasePage {

    public AdminAuthHomePage(WebDriver driver) {
        super(driver);
    }

    // VALID_EMAIL = "exampleAdmin@gmail.com";
    // VALID_PASSWORD = "example1234admin";
    // invalidEmail = "invalid@example.com";
    // invalidPassword = "wrongpassword";


    private final String baseUrl = "https://hop-admin-angular.onrender.com";
    private final String VALID_EMAIL = ("//*[@placeholder='Введіть email']");
    private final String VALID_PASSWORD = ("//*[@placeholder='Введіть пароль']");
    private final String INVALID_EMAIL = ("//*[@placeholder='Введіть email']");
    private final String INVALID_PASSWORD = ("//*[@placeholder='Введіть пароль']");
    private final String LOGIN_BUTTON = ("//button[@type='submit']");
    private final String ERROR_MESSAGE = ("//div[contains(text(), 'Логін або Пароль не вірні')]");
    private final String LOGOUT_BUTTON = ("//button//span[contains(text(), 'Log out')]");

    public void openAdminHomePage() {
        driver.get(baseUrl);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    // Ввести коректний email
    public void enterValidEmail(String email) {
        waitElementToBeVisible(VALID_EMAIL).sendKeys(email);
    }

    // Ввести коректний пароль
    public void enterValidPassword(String password) {
        waitElementToBeVisible(VALID_PASSWORD).sendKeys(password);
    }

    // Ввести неправильний email
    public void enterInValidEmail(String email) {
        waitElementToBeVisible(INVALID_EMAIL).sendKeys(email);
    }

    // Ввести неправильний пароль
    public void enterInValidPassword(String password) {
        waitElementToBeVisible(INVALID_PASSWORD).sendKeys(password);
    }

    // Натиснути кнопку входу
    public void clickLoginButton() {
        waitElementToBeVisible(LOGIN_BUTTON).click();
    }

    // Отримати повідомлення про помилку
    public String getErrorMessage() {
        return waitElementToBeVisible(ERROR_MESSAGE).getText();
    }

    // Натиснути кнопку виходу
    public void clickLogoutButton() {
        waitElementToBeVisible(LOGOUT_BUTTON).click();
    }

    // Перевірити, що сторінка входу відображається
    public boolean isLoginFormDisplayed() {
        try {
            return waitElementToBeVisible(VALID_EMAIL).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Перевірити, що сторінка Dashboard відображається
    public boolean isDashboardDisplayed() {
        try {
            return waitElementToBeVisible("//h1[contains(text(), 'Dashboard')]").isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Отримати токен з localStorage
    public String getToken() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return window.localStorage.getItem('token');");
    }


    // Видалити токен з localStorage
    public void clearToken() {
        ((JavascriptExecutor) driver).executeScript("window.localStorage.removeItem('token');");
    }
}
