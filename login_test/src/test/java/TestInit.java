import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class TestInit {

    public WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
public void setupTest() {
    ChromeOptions options = new ChromeOptions();
    if (System.getenv("CI") != null) {
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
    }
    
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
}

    @AfterEach
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Error closing WebDriver: " + e.getMessage());
        }
    }
}
