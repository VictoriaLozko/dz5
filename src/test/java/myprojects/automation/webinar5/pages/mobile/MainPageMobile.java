package myprojects.automation.webinar5.pages.mobile;

import myprojects.automation.webinar5.utils.CustomReporter;
import myprojects.automation.webinar5.utils.TryToWait;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageMobile {

    private static By allProductsButtonLocator = By.xpath("//section[@class = 'featured-products clearfix']/a");
    private static By productsListOnMainPageLocator = By.xpath("//div[@class = 'products']");

    private MainPageMobile(){
    }

    public static void goToMainPage(WebDriver driver, String url){
        driver.navigate().to(url);
    }

    public static boolean isMainPageOpened(WebDriverWait wait){

        boolean isOpened = TryToWait.tryToWaitForPresenceOfElementLocated(wait, productsListOnMainPageLocator,
                "Main page was not opened", "TimeoutException in isMainPageOpened");

        return isOpened;
    }

    public static void goToAllProducts(WebDriver driver, WebDriverWait wait){

        boolean isClickable = TryToWait.tryToWaitForElementToBeClickable(wait, allProductsButtonLocator,
                "All products button is not clickable", "TimeoutException in goToAllProducts");

        if (!isClickable){
            return;
        }

        WebElement allProductsButton = driver.findElement(allProductsButtonLocator);
        scrollPageDown(driver);
        allProductsButton.click();
    }

    private static void scrollPageDown(WebDriver driver){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("window.scrollBy(0,2500)");
    }
}
