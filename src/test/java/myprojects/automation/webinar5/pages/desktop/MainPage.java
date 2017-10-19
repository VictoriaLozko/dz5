package myprojects.automation.webinar5.pages.desktop;

import myprojects.automation.webinar5.utils.CustomReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        boolean isOpened = true;

        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(productsListOnMainPageLocator));
        }catch(TimeoutException te){
            isOpened = false;
            CustomReporter.log("TimeoutException in isMainPageOpened");
            System.out.println("TimeoutException in isMainPageOpened");
        }

        return isOpened;
    }

    public static void goToAllProducts(WebDriver driver, WebDriverWait wait){

        try {
            wait.until(ExpectedConditions.elementToBeClickable(allProductsButtonLocator));
        }catch (TimeoutException te){
            CustomReporter.log("Cant found all products button");
            System.out.println("TimeoutException in goToAllProducts");
            return;
        }

        WebElement allProductsButton = driver.findElement(allProductsButtonLocator);
        allProductsButton.click();
    }

    public static boolean isAllProductsPageOpened(WebDriverWait wait){
        boolean isOpened = true;

        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(productsListOnAllProductsPageLocator));
        }catch(TimeoutException te){
            isOpened = false;
            CustomReporter.log("TimeoutException in isAllProductsPageOpened");
            System.out.println("TimeoutException in isAllProductsPageOpened");
        }

        return isOpened;
    }
}
