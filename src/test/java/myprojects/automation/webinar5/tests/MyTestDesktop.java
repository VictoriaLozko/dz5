package myprojects.automation.webinar5.tests;

import myprojects.automation.webinar5.BaseTest;
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

    }
}
