import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Karanjit_Singh on 15-02-2017.
 */
public class Locators {
@Test
    public void locatorsTest(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.google.com");
        WebElement searchField = driver.findElement(By.id("lst-ib"));
        searchField.sendKeys("First Java Program");
        WebElement searchButton = driver.findElement(By.id("_fZl"));
        searchButton.click();

        //WebElement firstSearchResult = driver.findElement(By.className("r"));
        //WebElement searchTagName = firstSearchResult.findElement(By.tagName("a"));
        WebElement searchTextName = driver.findElement(By.partialLinkText("Java Basic"));
        searchTextName.click();


    //List<WebElement> searchResults = driver.findElements(By.className("r"));
    //System.out.println(searchResults.size());






    }
}
