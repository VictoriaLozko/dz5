package myprojects.automation.webinar5.pages.desktop;

import myprojects.automation.webinar5.utils.TryToWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllProductsPage {
    //locator of the first product in list
    private static By productLocator = By.xpath("//div[@class='products row']/article[1]//h1/a");
    private static By addToCartButtonLocator = By.xpath("//button[@class='btn btn-primary add-to-cart']");
    private static By goToOrderingButtonLocator = By.xpath("//a[@class='btn btn-primary']");

    private AllProductsPage(){
    }

    public static void openProduct(WebDriver driver){
        WebElement product = driver.findElement(productLocator);
        product.click();
    }

    public static boolean isProductPageOpened(WebDriverWait wait){

        boolean isOpened = TryToWait.tryToWaitForPresenceOfElementLocated(wait, addToCartButtonLocator,
                "Cant found addToCart Button", "TimeoutException in isProductPageOpened");

        return isOpened;
    }

    public static void addProductToTheCart(WebDriver driver, WebDriverWait wait){

        boolean isClickable = TryToWait.tryToWaitForElementToBeClickable(wait, addToCartButtonLocator,
                "AddToCart button is not clickable", "TimeoutException in goToAllProducts");

        if(!isClickable){
            return;
        }

        WebElement addToCartButton = driver.findElement(addToCartButtonLocator);
        addToCartButton.click();
    }

    public static boolean isProductAddToCartMessageIsShown(WebDriverWait wait){

        boolean isShown = TryToWait.tryToWaitForVisibilityOfElementLocated(wait, goToOrderingButtonLocator,
                "Cant found product added to the cart message", "TimeoutException in isProductPageOpened");

        return isShown;
    }

    public static void goToOrdering(WebDriver driver, WebDriverWait wait){

        boolean isClickable = TryToWait.tryToWaitForElementToBeClickable(wait, goToOrderingButtonLocator,
                "Ordering button is not clickable", "TimeoutException in goToOrdering");

        if(!isClickable){
            return;
        }

        WebElement orderingButton = driver.findElement(goToOrderingButtonLocator);
        orderingButton.click();
    }
}
