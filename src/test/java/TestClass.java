import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

/**
 * Created by Karanjit_Singh on 15-02-2017.
 */
public class TestClass {
    /*@Test
    public void testMethod() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Karanjit_Singh\\qa_auto_training\\SeleniumDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.id("gb_70"));

        assertNotNull(By.id("gb_70"));

        driver.close();
    }*/

    @Test
    public void GoogleTest() {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Karanjit_Singh\\qa_auto_training\\SeleniumDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.google.com");

        WebElement SearchField = driver.findElement(By.id("lst-ib"));
        SearchField.sendKeys("Selenium");
        SearchField.submit();

        List<WebElement> SearchResults = driver.findElements(By.cssSelector(".r"));
        for (WebElement searchResult : SearchResults) {
            if (!searchResult.getText().toLowerCase().contains("selenium")) {
                fail("Not all elements contain search phrase");
            }

        }

        driver.close();
    }
}

