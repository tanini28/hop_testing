package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AdminAuthHomePage extends BasePage {

    public AdminAuthHomePage(WebDriver driver) {
        super(driver);
    }

    private final String baseUrl = System.getProperty("BASE_URL", "https://hop-admin-angular.onrender.com");
    private final String VALID_EMAIL = ("//*[@placeholder='Введіть email']");
    private final String VALID_PASSWORD = ("//*[@placeholder='Введіть пароль']");
    private final String INVALID_EMAIL = ("//*[@placeholder='Введіть email']");
    private final String INVALID_PASSWORD = ("//*[@placeholder='Введіть пароль']");
    private final String LOGIN_BUTTON = ("//button[@type='submit']");
    private final String ERROR_MESSAGE = ("//div[contains(text(), 'Логін або Пароль не вірні')]");
    private final String LOGOUT_BUTTON = ("//button//span[contains(text(), 'Log out')]");
    private final String DASHBOARD_HEADING = ("//h1[contains(text(), 'Dashboard')]");

    public void openAdminHomePage() {
        driver.get(baseUrl);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    public void enterValidEmail(String email) {
        waitElementToBeVisible(VALID_EMAIL).sendKeys(email);
    }

    public void enterValidPassword(String password) {
        waitElementToBeVisible(VALID_PASSWORD).sendKeys(password);
    }


    public void enterInValidEmail(String email) {
        waitElementToBeVisible(INVALID_EMAIL).sendKeys(email);
    }


    public void enterInValidPassword(String password) {
        waitElementToBeVisible(INVALID_PASSWORD).sendKeys(password);
    }


    public void clickLoginButton() {
        waitElementToBeVisible(LOGIN_BUTTON).click();
    }


    public String getErrorMessage() {
        return waitElementToBeVisible(ERROR_MESSAGE).getText();
    }


    public void clickLogoutButton() {
        waitElementToBeVisible(LOGOUT_BUTTON).click();
    }


    public boolean isLoginFormDisplayed() {
        try {
            return waitElementToBeVisible(VALID_EMAIL).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isDashboardDisplayed() {
        try {
            return waitElementToBeVisible(DASHBOARD_HEADING).isDisplayed();
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
