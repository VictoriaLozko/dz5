package myprojects.automation.webinar5.tests;

import myprojects.automation.webinar5.BaseTest;
import myprojects.automation.webinar5.pages.desktop.*;
import myprojects.automation.webinar5.utils.CustomReporter;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
        Assert.assertTrue(AllProductsPage.isAllProductsPageOpened(wait),"All Products page was not opened");
    }

    @Test (dependsOnMethods = "testGoToAllProducts")
    public void testOpenProduct(){
        CustomReporter.logAction("Try to open some product");
        AllProductsPage.openProduct(driver);
        Assert.assertTrue(ProductPage.isProductPageOpened(wait),"Product page was not opened");
    }

    @Test (dependsOnMethods = "testOpenProduct")
    public void testAddToCart(){
        ProductPage.saveProductProperties(driver);
        ProductPage.saveProductCountInGeneral(null,driver,wait);
        CustomReporter.logAction("Try to add some product to the cart");
        ProductPage.addProductToTheCart(driver,wait);
        Assert.assertTrue(ProductPage.isProductAddToCartMessageIsShown(wait),
                "Product add to cart message was not shown");
    }

    @Test (dependsOnMethods = "testAddToCart")
    public void testGoToCart(){
        CustomReporter.logAction("Try to go to the cart");
        ProductPage.goToOrdering(driver,wait);

        Assert.assertTrue(CartPage.isCartPageOpened(wait), "Cart page was not opened");
        CartPage.getProductProperties(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(CartPage.productIsOneInCart(driver),"Product count is not one in cart");
        softAssert.assertTrue(CartPage.compareProductName(driver), "Product name in cart is not the same");
        softAssert.assertTrue(CartPage.compareProductPrice(driver), "Product price in cart is not the same");

        softAssert.assertAll();
    }

    @Test (dependsOnMethods = "testGoToCart")
    public void testOrder(){
        CustomReporter.logAction("Try to go to the order page");
        CartPage.goToOrder(driver,wait);
        Assert.assertTrue(OrderPage.isOrderPageOpened(wait), "Order page was not opened");

        CustomReporter.logAction("Try to make an order");
        Assert.assertTrue(OrderPage.makeAnOrder(driver,wait),"Order was not completed");

        Assert.assertTrue(OrderConfirmationPage.isOrderConfirmationPageOpened(wait),
                "Order confirmation page was not opened");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(OrderConfirmationPage.compareProductName(driver),
                "Product name on order confirmation page is not the same");
        softAssert.assertTrue(OrderConfirmationPage.compareProductPrice(driver),
                "Product price on order confirmation page is not the same");
        softAssert.assertTrue(OrderConfirmationPage.compareProductCount(driver,1),
                "Product count on order confirmation page is not the same");

        softAssert.assertAll();
    }

    @Parameters ({"mainPageUrl"})
    @Test (dependsOnMethods = "testOrder")
    public void testProductCountIsChanged (String url){
        testGoToMainPage(url);
        testGoToAllProducts();
        testOpenProduct();
        Assert.assertTrue(ProductPage.isProductCountLessOn(driver,wait,1),
                "The product quantity is not updated on product page after making an order");
    }
}
