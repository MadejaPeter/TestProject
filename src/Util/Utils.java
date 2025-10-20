package Util;

import Data.Data;
import Data.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Random;

import static java.awt.SystemColor.window;

public final class Utils {


    public static void loginToMailbox(WebDriver driver) {
        // Login to Emails
        driver.findElement(By.name(Locators.USERNAME_NICKNAME_EMAIL)).sendKeys(Data.USERNAME_NICKNAME);
        driver.findElement(By.name(Locators.USERNAME_NICKNAME_PASSWORD)).sendKeys(Data.PASSWORD);
        driver.findElement(By.cssSelector(Locators.LOGIN_BUTTON_MAIL)).click();
    }


    public static void waitForNewEmail(WebDriver driver) {
        driver.findElement(By.cssSelector(".inbox__ > .priecinok")).click();
        driver.findElement(By.xpath("//*[@id=\"mailbox\"]/tbody/tr[2]/td[4]/a/b")).isDisplayed();
        String test = driver.findElement(By.xpath("//*[@id=\"mailbox\"]/tbody/tr[2]/td[4]/a/b")).getText();
        System.out.println(test);
    }

    public static void logoutFromMailbox(WebDriver driver) {
        driver.findElement(By.id("ab_nick")).click();
        driver.findElement(By.id("ab_logout")).click();
        driver.findElement(By.cssSelector(Locators.LOG_IN_BUTTON_AFTER_LOGOUT)).isDisplayed();
    }

    public static void addEmailAttachment(WebDriver driver) {
        String filePath = "C:\\Users\\hotovo\\IdeaProjects\\TestProject\\src\\Image\\TestImage.jpg";
        driver.findElement(By.id("file_input")).sendKeys(filePath);
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".done .upload-done")));
        }
    }

    public static void createEmailHead(WebDriver driver) {
        //Generate random subject
        Random random = new Random();
        int number = random.nextInt(1000);
        String SUBJECT = "Test" + number;

        // Create new email
        driver.findElement(By.linkText(Locators.CREATE_EMAIL)).isEnabled();
        driver.findElement(By.linkText(Locators.CREATE_EMAIL)).click();

        driver.findElement(By.id(Locators.EMAIL_CONTACTS)).click();
        driver.findElement(By.linkText(Locators.CONTACT_PETER)).click();
        driver.findElement(By.cssSelector(Locators.EMAIL_CONTACTS_CLOSE)).click();

        driver.findElement(By.id(Locators.EMAIL_SUBJECT)).click();
        driver.findElement(By.id(Locators.EMAIL_SUBJECT)).sendKeys(SUBJECT);
    }

    public static void emailBody(WebDriver driver) {
        //Email body
        driver.switchTo().frame(1);
        driver.findElement(By.cssSelector(Locators.EMAIL_BODY)).click();
        driver.findElement(By.cssSelector(Locators.EMAIL_BODY)).sendKeys(Data.EMAIL_TEXT);
        driver.switchTo().defaultContent();
    }

    //Send email
    public static void sendEmail(WebDriver driver) {
        driver.findElement(By.cssSelector(".coSoSpravou:nth-child(1) .odoslat-container span")).click();
    }

    public static void loginInAzet(WebDriver driver, String password) {


        driver.findElement(By.id(Locators.HOME)).isDisplayed();
        driver.findElement(By.xpath(Locators.LOG_IN_BUTTON_HOME)).isDisplayed();
        driver.findElement(By.xpath(Locators.LOG_IN_BUTTON_HOME)).isEnabled();


        driver.findElement(By.xpath(Locators.LOG_IN_BUTTON_HOME)).click();

        // Switch to new tab
        driver.getWindowHandles().forEach(tab->driver.switchTo().window(tab));
        driver.findElement(By.id(Locators.TOP_BAR)).isDisplayed();

        //Enter Username
        driver.findElement(By.id(Locators.USERNAME)).isDisplayed();
        driver.findElement(By.id(Locators.USERNAME)).isEnabled();
        driver.findElement(By.id(Locators.USERNAME)).sendKeys(Data.USERNAME_NICKNAME);
        String windowHandle1 = driver.getWindowHandle();
        System.out.println(windowHandle1);

        //Enter Password
        driver.findElement(By.id(Locators.PASSWORD)).isDisplayed();
        driver.findElement(By.id(Locators.PASSWORD)).isEnabled();
        driver.findElement(By.id(Locators.PASSWORD)).sendKeys(password);

        //Click to Login - Prihlasit sa
        driver.findElement(By.id(Locators.LOGIN_BUTTON)).isDisplayed();
        driver.findElement(By.id(Locators.LOGIN_BUTTON)).isEnabled();
        driver.findElement(By.id(Locators.LOGIN_BUTTON)).click();

        //Verify that user is correctly Logged

        ((JavascriptExecutor) driver).executeScript("window.focus();");
    }

    //Verify user not able to login with incorrect password
    public static void verifyIncorrectPasswordLogIn(WebDriver driver){
        driver.findElement(By.id(Locators.LOG_IN_INCORRECT)).isDisplayed();
//      Assert.assertEquals(driver.findElement(By.cssSelector(Locators.LOG_IN_INCORRECT)),"Zadané údaje nie sú správne");


    }

    //Verify user is Signed in correctly and logout
    //Verify user is logged out correctly
    public static void verifyLoggedAndlogOutAzet(WebDriver driver) {
        //Verify that user is Logged in

//        String window = driver.getWindowHandle();
//        ((JavascriptExecutor) driver).executeScript("alert('Test')");
//        driver.switchTo().alert().accept();
//        driver.switchTo().window(window);
//        driver.findElement(By.cssSelector(Locators.PROFILE_ICON_AFTER_LOGGED)).isEnabled();
        driver.findElement(By.cssSelector(Locators.PROFILE_ICON_AFTER_LOGGED)).click();

        String userMail = driver.findElement(By.cssSelector(Locators.USER_EMAIL_CSS)).getText();
        System.out.println(userMail);
        Assert.assertEquals(userMail, Data.USER_EMAIL);

        driver.findElement(By.cssSelector(".dvHDVY")).isDisplayed();
        driver.findElement(By.cssSelector(".dvHDVY")).click();
        driver.findElement(By.cssSelector(Locators.LOG_IN_BUTTON_AFTER_LOGOUT)).isDisplayed();
    }


    public static void privatePolicy(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"notice\"]/div[5]/div[2]/button")).isDisplayed();
        driver.findElement(By.xpath("//*[@id=\"notice\"]/div[5]/div[2]/button")).click();
        driver.switchTo().defaultContent();
    }


}
