package myprojects.automation.webinar5.pages.desktop;

import myprojects.automation.webinar5.utils.TryToWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;

public class ProductPage {

    private static By addToCartButtonLocator = By.xpath("//button[@class='btn btn-primary add-to-cart']");
    private static By goToOrderingButtonLocator = By.xpath("//a[@class='btn btn-primary']");
    private static By nameLocator = By.xpath("//div[@class='col-md-6']/h1");
    private static By priceLocator = By.xpath("//div[@class='current-price']/span");
    private static By moreAboutProductLocator = By.xpath("//ul[@class='nav nav-tabs']/li[2]/a");
    private static By productCountLocator = By.xpath("//div[@class='product-quantities']/span");

    private static String name;
    private static String price;
    private static Integer productCount;

    private ProductPage(){
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

    public static void saveProductProperties(WebDriver driver){
        name = driver.findElement(nameLocator).getText().toLowerCase();
        String price_string = driver.findElement(priceLocator).getText();
        price = price_string.substring(0, (price_string.indexOf(",") + 3));
    }

    public static void saveProductCountInGeneral(Integer saveTo, WebDriver driver, WebDriverWait wait){

        goToMoreAboutProduct(driver, wait);

        boolean isOpened = TryToWait.tryToWaitForPresenceOfElementLocated(wait,productCountLocator,
                "More about product section was not opened","TimeoutException in saveProductCountInGeneral");

        if(!isOpened){
            return;
        }
        if(saveTo == null){
            String productCountStr = driver.findElement(productCountLocator).getText();
            productCountStr = productCountStr.substring(0,productCountStr.indexOf(" "));
            productCount = new Integer(productCountStr);
        }else{
            String productCountStr = driver.findElement(productCountLocator).getText();
            productCountStr = productCountStr.substring(0,productCountStr.indexOf(" "));
            saveTo = new Integer(productCountStr);
        }
    }

    public static boolean isProductCountLessOn(WebDriver driver, WebDriverWait wait, int lessOn){
        boolean isLess = false;
        Integer newProductCount = new Integer(0);
        saveProductCountInGeneral(newProductCount,driver,wait);
        newProductCount = Integer.valueOf(newProductCount.intValue() + lessOn);

        if(newProductCount.intValue() == productCount.intValue()){
            isLess = true;
        }

        return isLess;
    }

    private static void goToMoreAboutProduct(WebDriver driver, WebDriverWait wait){
        boolean isClickable = TryToWait.tryToWaitForElementToBeClickable(wait, moreAboutProductLocator,
                "More about product link is not clickable", "TimeoutException in goToMoreAboutProduct");

        if(!isClickable){
            return;
        }

        WebElement moreAboutProduct = driver.findElement(moreAboutProductLocator);
        moreAboutProduct.click();
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

    public static String getProductName(){
        return name;
    }

    public static String getProductPrice(){
        return price;
    }


}
