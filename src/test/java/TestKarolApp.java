import org.apache.commons.lang3.ObjectUtils;
import org.apache.xpath.operations.Equals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Karanjit_Singh on 16-02-2017.
 */
public class TestKarolApp {

    WebDriver driver;

    @BeforeMethod
    private void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/");
    }

    @Test
    public void multipleClicks() throws InterruptedException {

        //Given
        int hmt = 3;
        WebElement titleField = driver.findElement(By.xpath(".//*[@id='titleInput']"));
        titleField.sendKeys("Hello");


        //When
        WebElement Button = driver.findElement(By.id("submitButton"));
        for (int i = 0; i < hmt; i++) {
            Button.click();
            Thread.sleep(2000);
        }
        driver.navigate().refresh();
        Thread.sleep(2000);
        List<WebElement> returnTitleField = driver.findElements(By.xpath(".//*[@class='title']"));

        //Then
        Assert.assertEquals(returnTitleField.size(),1);

    }

    /*@Test
    public void fieldsRemainFilledInBrowserFirefox() throws InterruptedException {

        //Given

            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("http://localhost:8080/");
        WebElement inputTitle = driver.findElement(By.xpath(".//*[@id='titleInput']"));
        WebElement inputDescription = driver.findElement(By.xpath(".//*[@id='descriptionArea']"));
        WebElement inputRating = driver.findElement(By.xpath(".//*[@id='ratingInput']"));
        WebElement buttonSubmit = driver.findElement(By.xpath(".//*[@id='submitButton']"));
        inputTitle.sendKeys("Hello");
        String text = inputTitle.getText();
        inputDescription.sendKeys("I should be empty after refresh");
        inputRating.sendKeys("22");

        //When
        buttonSubmit.click();
        driver.navigate().refresh();
        Thread.sleep(2000);

        //Then
        Assert.assertFalse(text.isEmpty());

    }*/

    @Test
    public void titleTextNotFormatted() throws InterruptedException {

        //Given
        WebElement inputTitle = driver.findElement(By.xpath(".//*[@id='titleInput']"));
        WebElement buttonSubmit = driver.findElement(By.xpath(".//*[@id='submitButton']"));
        inputTitle.sendKeys("Hello This text might be very long but I have to type it because it needs to be tested");
        String formattedText = inputTitle.getText();

        //When
        buttonSubmit.click();
        driver.navigate().refresh();
        Thread.sleep(2000);
        WebElement elementTitle = driver.findElement(By.id("title0"));
        String unformatedText =  elementTitle.getText();

        //Then
        Assert.assertTrue(unformatedText.contains(formattedText));
    }

    @Test
    public void htmlInjection() throws InterruptedException {


        //Given
        WebElement inputTitle = driver.findElement(By.xpath(".//*[@id='titleInput']"));
        WebElement buttonSubmit = driver.findElement(By.xpath(".//*[@id='submitButton']"));
        inputTitle.sendKeys("<h1>title</h1>");


        //When
        buttonSubmit.click();
        Thread.sleep(2000);
        driver.navigate().refresh();

        String htmlTitle = inputTitle.getText();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        WebElement elementTitle = driver.findElement(By.xpath(".//*[@id='title0']"));

        String returnTitle = elementTitle.getText();

        //Then
        Assert.assertTrue(returnTitle.contentEquals(htmlTitle));
    }

    @AfterMethod
    private void tearDown() {
        List<WebElement> deleteButtons = driver.findElements(By.xpath(".//*[@class='deleteButton']"));
        for (WebElement element : deleteButtons) {
            element.click();
        }
        driver.navigate().refresh();
    }
}



