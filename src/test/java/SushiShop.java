import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Karanjit_Singh on 23-02-2017.
 */
public class SushiShop {

    WebDriver driver;

    @BeforeMethod
    private void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://demos.telerik.com/kendo-ui/websushi#/");
    }

    @Test
    public void itemAddedToCart(){
        //Given
        // Website opened successfully

        //When
        WebElement clickOnItem = driver.findElement(By.className("view-details"));
        clickOnItem.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement addToCart = driver.findElement(By.className("buy"));
        addToCart.click();
        WebElement shoppingCart = driver.findElement(By.xpath(".//*[@id='cart-info']/span"));
        String newText = shoppingCart.getText();

        //Then
        System.out.println(newText);
        Assert.assertEquals(newText,"1 ITEMS","Cart should contain only 1 item");
    }

    @Test
    public void addedItemCanBeDeleted() {
        //Given
        // website can be opened


        //When

        WebElement clickOnItem = driver.findElement(By.className("view-details"));
        clickOnItem.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement addToCart = driver.findElement(By.className("buy"));
        addToCart.click();
        WebElement shoppingCart = driver.findElement(By.xpath(".//*[@id='cart-info']/span"));
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement emptyCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='empty-cart']")));
        emptyCart.click();
        String finalCart = shoppingCart.getText();

        System.out.println(finalCart);

        //Then
        Assert.assertEquals(finalCart,"0 ITEMS", "Cart still contains few items");
    }
}
