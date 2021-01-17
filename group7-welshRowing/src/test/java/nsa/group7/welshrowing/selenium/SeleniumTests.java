package nsa.group7.welshrowing.selenium;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class SeleniumTests {

    private WebDriver webDriver;

    @Test
    public void shouldCreateAnAccount() throws Exception {

        if (System.getProperty("os.name").equals("Mac OS X")){
            System.setProperty("webdriver.chrome.driver", "chromedrivermac");
        } else {
            System.setProperty("webdriver.chrome.driver", "chromedriverwindows");
        }

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        webDriver = new ChromeDriver(options);

        webDriver.get("https://localhost:8080/");
        assertEquals("Welsh Rowing", this.webDriver.getTitle());
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //waits 2 seconds before moving to next page.
        webDriver.findElement(By.id("signup")).click();

        //section for filling in new login credentials.
        assertEquals("New Applicant", this.webDriver.getTitle());

        WebElement fullName = webDriver.findElement(By.id("fullName")); // the name field.
        fullName.sendKeys("Zac Farro"); //enter a name
        WebElement username = webDriver.findElement(By.id("username")); // the username field.
        username.sendKeys("zfarro"); //enter a username
        WebElement password = webDriver.findElement(By.id("password")); // the password field.
        password.sendKeys("@zFarro1"); //enter a password
        WebElement confirmPassword = webDriver.findElement(By.id("confirmPassword")); // the confirm password field.
        confirmPassword.sendKeys("@zFarro1"); //enter a password

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits 2 seconds before pressing submit button.
        webDriver.findElement(By.id("submit")).click();

        //section for filling in user information.
        assertEquals("Update your Profile", this.webDriver.getTitle());

        WebElement email = webDriver.findElement(By.id("email")); // the email field.
        email.sendKeys("nbaitup1@gmail.com"); //enter an email

        WebElement gender = webDriver.findElement(By.cssSelector("input[value='male']")); // the heard about field.
        gender.click();

        WebElement dob = webDriver.findElement(By.id("dob")); // the dob field.
        dob.sendKeys("01012001"); //enter a dob

        WebElement mobileNum = webDriver.findElement(By.id("mobileNum")); // the mobile num field.
        mobileNum.sendKeys("07543215678"); //enter a mobile num

        WebElement telephoneNum = webDriver.findElement(By.id("telephoneNum")); // the telephone num field.
        telephoneNum.sendKeys("01656734213"); //enter a telephone num

        WebElement address = webDriver.findElement(By.id("address")); // the address field.
        address.sendKeys("1 Test Lane"); // enters an address

        WebElement postcode = webDriver.findElement(By.id("postcode")); // the postcode field.
        postcode.sendKeys("CF349BD"); //enters a postcode

        WebElement school = webDriver.findElement(By.id("school")); // the school / uni field.
        school.sendKeys("Cardiff University"); //enter a school/uni

        WebElement findBy = webDriver.findElement(By.cssSelector("input[value='facebook']")); // the heard about field.
        findBy.click();

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits 2 seconds before pressing submit button.
        webDriver.findElement(By.id("submit")).click(); // submits the new user information, directing to the user dashboard.

        // redirects to the athlete dashboard, waits five seconds then logs you out and logs back in.
        assertEquals("Athlete Dashboard", this.webDriver.getTitle());

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // waits 5 seconds before pressing submit button.
        webDriver.findElement(By.id("signout")).click();
        webDriver.findElement(By.id("home")).click();
        assertEquals("Welsh Rowing", this.webDriver.getTitle());

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits 2 seconds before pressing submit button.

        webDriver.findElement(By.id("login")).click();
        assertEquals("Login page", this.webDriver.getTitle());

        WebElement usernameLogin = webDriver.findElement(By.id("username")); // the username field.
        usernameLogin.sendKeys("zfarro"); //enter a username
        WebElement passwordLogin = webDriver.findElement(By.id("password")); // the password field.
        passwordLogin.sendKeys("@zFarro1"); //enter a password

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits 2 seconds before pressing submit button.
        webDriver.findElement(By.id("submit")).click();
        assertEquals("Athlete Dashboard", this.webDriver.getTitle());


    }

}


