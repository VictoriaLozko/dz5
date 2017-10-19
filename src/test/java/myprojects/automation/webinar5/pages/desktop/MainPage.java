package myprojects.automation.webinar5.pages.desktop;

import myprojects.automation.webinar5.utils.TryToWait;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private static By allProductsButtonLocator = By.xpath("//section[@class = 'featured-products clearfix']/a");
    private static By productsListOnMainPageLocator = By.xpath("//div[@class = 'products']");
    private static By productsListOnAllProductsPageLocator = By.xpath("//div[@class='products row']");

    private MainPage(){
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
        allProductsButton.click();
    }

    public static boolean isAllProductsPageOpened(WebDriverWait wait){

        boolean isOpened = TryToWait.tryToWaitForPresenceOfElementLocated(wait, productsListOnAllProductsPageLocator,
                "Cant found product list on all products page", "TimeoutException in isAllProductsPageOpened");

        return isOpened;
    }
}
