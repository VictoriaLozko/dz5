package myprojects.automation.webinar5.pages.desktop;

import myprojects.automation.webinar5.utils.TryToWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private static By orderButtonLocator = By.xpath("//div[@class='text-xs-center']/a");
    private static By nameLocator = By.xpath("//div[@class='product-line-info']/a");
    private static By priceLocator = By.xpath("//div[@class='product-line-info']/span");
    private static By countLocator = By.xpath("//div[@class='cart-summary-line']/span[@class='label js-subtotal']");
    private static By cartCountLocator = By.xpath("//span[@class='cart-products-count']");
    private static String name;
    private static String price;

    private CartPage(){
    }

    public static boolean isCartPageOpened(WebDriverWait wait){

        boolean isOpened = TryToWait.tryToWaitForPresenceOfElementLocated(wait, orderButtonLocator,
                "Cant found order button", "isCartPageOpened");

        return isOpened;
    }

    public static void goToOrder(WebDriver driver, WebDriverWait wait){

        boolean isClickable = TryToWait.tryToWaitForElementToBeClickable(wait, orderButtonLocator,
                "AddToCart button is not clickable", "TimeoutException in goToAllProducts");

        if(!isClickable){
            return;
        }

        WebElement orderButton = driver.findElement(orderButtonLocator);
        orderButton.click();
    }

    public static boolean productIsOneInCart(WebDriver driver){
        boolean isOne;
        //String cnt_str = driver.findElement(countLocator).getText().toString();
        //cnt_str = cnt_str.substring(0,cnt_str.indexOf(" "));
        //Integer count = new Integer(Integer.getInteger(cnt_str));
        String cnt_str = driver.findElement(cartCountLocator).getText();
        cnt_str = cnt_str.substring(1);
        cnt_str = cnt_str.substring(0,cnt_str.indexOf(')'));
        Integer count = new Integer(cnt_str);
        int cnt = count.intValue();
        if(cnt == 1){
            isOne=true;

        }else{
            isOne=false;
        }
        return isOne;
    }

    public static void getProductProperties(WebDriver driver){
        name = driver.findElement(nameLocator).getText().toLowerCase();
        String price_string = driver.findElement(priceLocator).getText();
        price = price_string.substring(0, (price_string.indexOf(",") + 3));
    }

    public static boolean compareProductName(WebDriver driver){
        boolean isTheSame = false;
        if (name == null){
            getProductProperties(driver);
        }
        if(name.equals(ProductPage.getProductName())){
            isTheSame = true;
        }
        return isTheSame;
    }

    public static boolean compareProductPrice(WebDriver driver){
        boolean isTheSame = false;
        if (price==null){
            getProductProperties(driver);
        }
        if(price.equals(ProductPage.getProductPrice())){
            isTheSame = true;
        }
        return isTheSame;
    }
}
