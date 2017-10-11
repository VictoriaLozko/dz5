package myprojects.automation.webinar5.tests;


import myprojects.automation.webinar5.BaseTest;
import myprojects.automation.webinar5.utils.CustomReporter;
import myprojects.automation.webinar5.utils.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider
    public Object[][] getLoginData() {
        return new String[][] {
                {"webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw"}
        };
    }

    @Test(dataProvider = "getLoginData")
    public void userLogin(String login, String password) throws InterruptedException {
        CustomReporter.logAction("User login");
        driver.navigate().to(Properties.getBaseAdminUrl());
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.name("submitLogin")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("main")));

        Thread.sleep(10000);
    }
}
