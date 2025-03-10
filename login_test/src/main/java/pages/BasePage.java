package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

     public class BasePage {

         public WebDriver driver;

         public BasePage(WebDriver driver) {
             this.driver = driver;
         }

         public WebElement findElement(String locator) {
             return driver.findElement(By.xpath(locator));
         }

         int BASIC_TIME = 20;

         public WebElement waitElementToBeVisible(String locator) {
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BASIC_TIME));
             return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
         }

         public void openAdminHomePage() {
             driver.getCurrentUrl();
         }
     }
