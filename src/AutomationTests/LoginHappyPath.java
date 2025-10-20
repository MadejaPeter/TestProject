package AutomationTests;

import Data.Data;
import Util.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/*
    Created By            : Peter Madeja
    Test Case             : Login NegativePath
    Test Case Description :
         Open web page https://www.azet.sk
         Log in with correct username and password
         Check if user is logged in
         Log out from account
         Verify that user is logged out
*/

public class LoginHappyPath {

    static WebDriver driver = new ChromeDriver();

    @Test
    public static void main(String[] args) {

        String password = Data.PASSWORD;
        System.out.println("Test STARtED");

        System.out.println("Test STARtED");
        System.out.println("Test STARtED");

        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.azet.sk/");

        WebElement iframe = driver.findElement(By.id("sp_message_iframe_1322724"));
        driver.switchTo().frame(iframe);

        Utils.privatePolicy(driver);
        Utils.loginInAzet(driver, password);
        Utils.verifyLoggedAndlogOutAzet(driver);

        System.out.println("TEST PASSED");
        driver.quit();
    }
}
