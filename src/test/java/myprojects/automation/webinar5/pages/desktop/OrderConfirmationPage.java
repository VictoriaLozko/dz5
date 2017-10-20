package myprojects.automation.webinar5.pages.desktop;

import myprojects.automation.webinar5.utils.TryToWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmationPage {
    //div[@class='card-block']//h3
    private static By orderConfirmationTitleLocator = By.id("content-hook_order_confirmation");

    private static By nameLocator = By.xpath("//div[@class='col-sm-4 col-xs-9 details']/span");
    private static By priceLocator = By.xpath("//div[@class='col-xs-5 text-sm-right text-xs-left']");
    //private static By countLocator = By.xpath("//div[@class='col-xs-2']");
    private static By countLocator = By.className("col-xs-2");

    private static String name;
    private static String price;
    private static Integer count;

    private OrderConfirmationPage(){
    }

    public static boolean isOrderConfirmationPageOpened(WebDriverWait wait){
        boolean isOpened = TryToWait.tryToWaitForPresenceOfElementLocated(wait, orderConfirmationTitleLocator,
                "Cant found order confirmation title", "isOrderConfirmationPageOpened");

        return isOpened;
    }

    public static void getProductProperties(WebDriver driver){
        name = driver.findElement(nameLocator).getText().toLowerCase();
        name = name.substring(0,(name.indexOf("size") - 3));

        String price_string = driver.findElement(priceLocator).getText();
        price = price_string.substring(0, (price_string.indexOf(",") + 3));

        String cnt_str = driver.findElement(countLocator).getText();
        count = new Integer(cnt_str);
    }

    public static boolean compareProductName(WebDriver driver){
        boolean isTheSame = false;
        if(name == null){
            getProductProperties(driver);
        }
        if(name.equals(ProductPage.getProductName())){
            isTheSame = true;
        }
        return isTheSame;
    }

    public static boolean compareProductPrice(WebDriver driver){
        boolean isTheSame = false;
        if(price == null){
            getProductProperties(driver);
        }
        if(price.equals(ProductPage.getProductPrice())){
            isTheSame = true;
        }
        return isTheSame;
    }

    public static boolean compareProductCount(WebDriver driver, int cnt){
        boolean isTheSame = false;
        if(count == null){
            getProductProperties(driver);
        }
        if(count.intValue() == cnt){
            isTheSame = true;
        }
        return isTheSame;
    }


}
