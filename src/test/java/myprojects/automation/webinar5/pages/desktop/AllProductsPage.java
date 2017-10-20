package myprojects.automation.webinar5.pages.desktop;

import myprojects.automation.webinar5.utils.TryToWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllProductsPage {
    private static By productsListOnAllProductsPageLocator = By.xpath("//div[@class='products row']");

    //locator of the first product in list
    private static By productLocator = By.xpath("//div[@class='products row']/article[1]//h1/a");

    private AllProductsPage(){
    }

    public static boolean isAllProductsPageOpened(WebDriverWait wait){

        boolean isOpened = TryToWait.tryToWaitForPresenceOfElementLocated(wait, productsListOnAllProductsPageLocator,
                "Cant found product list on all products page", "TimeoutException in isAllProductsPageOpened");

        return isOpened;
    }

    public static void openProduct(WebDriver driver){
        WebElement product = driver.findElement(productLocator);
        product.click();
    }

}
