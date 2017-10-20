package myprojects.automation.webinar5.pages.desktop;

import myprojects.automation.webinar5.utils.RandomString;
import myprojects.automation.webinar5.utils.TryToWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private static By continueButtonLocator = By.name("continue");//div[@class='js-address-form']//button
    private static By continueAddressButtonLocator = By.name("confirm-addresses");
    private static By continueDeliveryButtonLocator = By.name("confirmDeliveryOption");
    private static By orderButtonLocator = By.xpath("//div[@id='payment-confirmation']//button");

    private static By nameFieldLocator = By.name("firstname");
    private static By surnameFieldLocator = By.name("lastname");
    private static By emailFieldLocator = By.name("email");

    private static By addressFieldLocator = By.name("address1");
    private static By postcodeFieldLocator = By.name("postcode");
    private static By cityFieldLocator = By.name("city");

    private static By radioButtonPayByBillLocator = By.xpath("//input[@id='payment-option-1']");
    private static By checkBoxAgreementLocator = By.id("conditions_to_approve[terms-and-conditions]");

    private OrderPage(){
    }

    public static boolean isOrderPageOpened(WebDriverWait wait){

        boolean isOpened = TryToWait.tryToWaitForPresenceOfElementLocated(wait, continueButtonLocator,
                "Cant found continue button", "isOrderPageOpened");

        return isOpened;
    }

    public static boolean makeAnOrder(WebDriver driver, WebDriverWait wait){

        fillPersonalData(driver);
        boolean orderIsMade = TryToWait.tryToWaitForElementToBeClickable(wait, continueButtonLocator,
                "Confirm personal data button is not clickable", "makeAnOrder");
        if(!orderIsMade){
            return orderIsMade;
        }
        WebElement continueButton = driver.findElement(continueButtonLocator);
        continueButton.click();

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        orderIsMade = TryToWait.tryToWaitForPresenceOfElementLocated(wait, continueAddressButtonLocator,
                "Confirm address data button is not present", "makeAnOrder");
        if(!orderIsMade){
            return orderIsMade;
        }

        fillAddressData(driver);

        WebElement continueAddressButton = driver.findElement(continueAddressButtonLocator);
        continueAddressButton.click();

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        orderIsMade = TryToWait.tryToWaitForElementToBeClickable(wait, continueDeliveryButtonLocator,
                "Confirm delivery button is not clickable", "makeAnOrder");
        if(!orderIsMade){
            return orderIsMade;
        }
        WebElement continueDeliveryButton = driver.findElement(continueDeliveryButtonLocator);
        continueDeliveryButton.click();

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*orderIsMade = TryToWait.tryToWaitForVisibilityOfElementLocated(wait, radioButtonPayByBillLocator,
                "Radio button PayByBill is not visible", "makeAnOrder");
        if(!orderIsMade){
            System.out.println("Radio button PayByBill is not visible");
            return orderIsMade;
        }*/

        WebElement radioButtonPayByBill = driver.findElement(radioButtonPayByBillLocator);
        radioButtonPayByBill.click();
        WebElement checkBoxAgreement = driver.findElement(checkBoxAgreementLocator);
        checkBoxAgreement.click();

        orderIsMade = TryToWait.tryToWaitForElementToBeClickable(wait, orderButtonLocator,
                "Order button is not clickable", "makeAnOrder");
        if(!orderIsMade){
            return orderIsMade;
        }

        WebElement orderButton = driver.findElement(orderButtonLocator);
        orderButton.click();

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return orderIsMade;
    }

    private static void fillTheField(WebDriver driver, By locator, String value){
        WebElement element = driver.findElement(locator);
        element.sendKeys(value);
    }

    private static void fillPersonalData(WebDriver driver){
        String name = RandomString.getRandomString(12);
        String surname = RandomString.getRandomString(20);
        String email = RandomString.getRandomString(9) + '@' + RandomString.getRandomString(3) + '.'
                + RandomString.getRandomString(3);

        fillTheField(driver, nameFieldLocator, name);
        fillTheField(driver, surnameFieldLocator, surname);
        fillTheField(driver, emailFieldLocator, email);
    }

    private static void fillAddressData(WebDriver driver){
        String address = RandomString.getRandomString(20);
        String postcode = "12345";
        String city = RandomString.getRandomString(15);

        fillTheField(driver, addressFieldLocator, address);
        fillTheField(driver, postcodeFieldLocator, postcode);
        fillTheField(driver, cityFieldLocator, city);
    }
}
