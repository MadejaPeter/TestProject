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
    Test Case             : Login HappyPath
    Test Case Description :
         Open web page https://www.azet.sk
         Log in with correct username and wrong password
         Verify if user is not able to log in
*/

public class LoginNegativePath {

    static WebDriver driver = new ChromeDriver();

    @Test
    public static void main(String[] args) {

        System.out.println("Test STARtED");
        String wrongPassword = Data.WRONG_PASSWORD;
        System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.azet.sk/");

        WebElement iframe = driver.findElement(By.id("sp_message_iframe_717181"));
        driver.switchTo().frame(iframe);

        Utils.privatePolicy(driver);
        Utils.loginInAzet(driver, wrongPassword);
        Utils.verifyIncorrectPasswordLogIn(driver);

        System.out.println("TEST PASSED");
        driver.quit();
    }



//    }

}
