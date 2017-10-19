package myprojects.automation.webinar5.tests;

import myprojects.automation.webinar5.BaseTest;
import myprojects.automation.webinar5.pages.desktop.AllProductsPage;
import myprojects.automation.webinar5.pages.desktop.CartPage;
import myprojects.automation.webinar5.pages.desktop.MainPage;
import myprojects.automation.webinar5.utils.CustomReporter;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyTestDesktop extends BaseTest{

    @Parameters ({"mainPageUrl"})
    @Test
    public void testGoToMainPage(String url){
        CustomReporter.logAction("Go to main page");
        MainPage.goToMainPage(driver, url);
        Assert.assertTrue(MainPage.isMainPageOpened(wait), "Main page was not opened");
    }

    @Test (dependsOnMethods = "testGoToMainPage")
    public void testGoToAllProducts(){
        CustomReporter.logAction("Go to AllProductsPage");
        MainPage.goToAllProducts(driver,wait);
        Assert.assertTrue(MainPage.isAllProductsPageOpened(wait),"All Products page was not opened");
    }

    @Test (dependsOnMethods = "testGoToAllProducts")
    public void testOpenProduct(){
        CustomReporter.logAction("Try to open some product");
        AllProductsPage.openProduct(driver);
        Assert.assertTrue(AllProductsPage.isProductPageOpened(wait),"Product page was not opened");
    }

    @Test (dependsOnMethods = "testOpenProduct")
    public void testAddToCart(){
        CustomReporter.logAction("Try to add some product to the cart");
        AllProductsPage.addProductToTheCart(driver,wait);
        Assert.assertTrue(AllProductsPage.isProductAddToCartMessageIsShown(wait),
                "Product add to cart message was not shown");
    }

    @Test (dependsOnMethods = "testAddToCart")
    public void testGoToCart(){
        CustomReporter.logAction("Try to go to the cart");
        AllProductsPage.goToOrdering(driver,wait);
        Assert.assertTrue(CartPage.isCartPageOpened(wait), "Cart page was not opened");
        Assert.assertTrue(CartPage.productIsOneInCart(driver),"Product count is not one in cart");
        CartPage.saveProductProperties(driver);
    }
}
