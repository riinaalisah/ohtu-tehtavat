package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        Random r = new Random();
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        System.out.println(driver.getPageSource());

        // LOGIN TO APPLICATION
        /*
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();

        System.out.println(driver.getPageSource());
*/

        // ---------------------------

        /* UNSUCCESSFUL LOGIN
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrongpwd");
        element = driver.findElement(By.name("login"));
        element.submit();

        System.out.println(driver.getPageSource());
        */

        // ----------------------------

        // CREATE NEW USER

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        System.out.println(driver.getPageSource());

        element = driver.findElement(By.name("username"));
        element.sendKeys("newuser" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("newpassword");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("newpassword");
        element = driver.findElement(By.name("signup"));
        element.submit();

        System.out.println(driver.getPageSource());

        // AND LOGOUT
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        System.out.println(driver.getPageSource());

        element = driver.findElement(By.linkText("logout"));
        element.click();

        System.out.println(driver.getPageSource());

        // ---------------------------

        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
