package AutomationTests;

import Util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/*
    Created By            : Peter Madeja
    Test Case             : Send email
    Test Case Description :
         Open web page https://www.azet.sk
         Log in with correct username and correct password
         Verify if user is logged in
         Start creating new email
         Set Subject
         Choose email contact from email address book
         Set Body
         Send email
         Log out from account
         Verify that user is logged out
*/


public class SendEmail {

    static WebDriver driver = new ChromeDriver();
    @Test
    public static void main(String[] args) {

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://mail.azet.sk/");
        WebElement iframe = driver.findElement(By.id("sp_message_iframe_717193"));
        driver.switchTo().frame(iframe);


        Utils.privatePolicy(driver);
        Utils.loginToMailbox(driver);
        Utils.createEmailHead(driver);
        Utils.emailBody(driver);
        Utils.sendEmail(driver);
        Utils.logoutFromMailbox(driver);

        System.out.println("TEST PASSED");
        driver.quit();
    }



}
