package Clase12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EjercicioGit {

    String URL= "https://login.salesforce.com/?locale=eu";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @Test (priority = 3)
    public void LoginFailureTest() throws InterruptedException{
        WebElement logo= driver.findElement(By.id("logo"));
        Assert.assertEquals(logo.getAttribute("alt"), "Salesforce" );

        WebElement email= driver.findElement(By.xpath("//input[@type=\"email\"]"));
        email.sendKeys("test@test.com");

        WebElement password= driver.findElement(By.xpath("//input[@type=\"password\"]"));
        password.sendKeys("123466");

        WebElement logIn= driver.findElement(By.xpath("//input[@type=\"submit\"]"));
        logIn.click();

        Thread.sleep(2000);

        WebElement errorMsg= driver.findElement(By.xpath("//div[@id=\"error\"]"));

        System.out.println("El mensaje de error que sale es el siguiente: " + errorMsg.getText());

        String error="Your access to salesforce.com has been disabled by your system administrator. Please contact your Administrator for more information.";

        Assert.assertEquals(errorMsg.getText(), error);
    }
/*
    @AfterMethod
    public void close(){
        driver.close();
    }*/
}
