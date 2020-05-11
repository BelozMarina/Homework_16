import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PriceTest {

    public static String browser = "firefox";
    public static WebDriver driver;


    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        if(browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("opera")) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://price.ua/");

        // Product search
        WebElement element = driver.findElement(By.id("SearchForm_searchPhrase"));
        element.sendKeys("кавомашини");
        element.findElement(By.xpath("//*[@id='search-block-submit']")).submit();


        WebDriverWait wait = (new WebDriverWait(driver, 15));

        //set min and max price
        WebElement priceMin = wait.until(ExpectedConditions.elementToBeClickable(By.id("price_min_")));
        priceMin.sendKeys("8000");
        WebElement priceMax = wait.until(ExpectedConditions.elementToBeClickable(By.id("price_max_")));
        priceMax.sendKeys("12000");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // click OK button
        driver.findElement(By.xpath("//a[@class='btn btn-purple ga_cat_filter btn-filters-submit btn-ok']")).click();


    }
}
