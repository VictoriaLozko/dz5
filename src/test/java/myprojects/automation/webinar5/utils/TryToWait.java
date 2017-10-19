package myprojects.automation.webinar5.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TryToWait {

    private TryToWait(){
    }

    public static boolean tryToWaitForPresenceOfElementLocated(WebDriverWait wait, By locator, String msg1, String msg2){
        boolean isPresent = true;
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch(TimeoutException te){
            isPresent = false;
            CustomReporter.log(msg1);
            System.out.println(msg2);
        }
        return isPresent;
    }

    public static boolean tryToWaitForElementToBeClickable(WebDriverWait wait, By locator, String msg1, String msg2) {
        boolean isClickable = true;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }catch (TimeoutException te){
            CustomReporter.log(msg1);
            System.out.println(msg2);
            isClickable = false;
        }
        return isClickable;
    }

    public static boolean tryToWaitForVisibilityOfElementLocated(WebDriverWait wait, By locator, String msg1, String msg2) {
        boolean isVisible = true;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (TimeoutException te){
            CustomReporter.log(msg1);
            System.out.println(msg2);
            isVisible = false;
        }
        return isVisible;
    }

}
