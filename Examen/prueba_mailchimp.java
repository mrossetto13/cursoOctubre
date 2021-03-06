package Examen;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import practico14.DataGenerator;

import java.util.concurrent.TimeUnit;

public class prueba_mailchimp {
    WebDriver driver;
    Faker faker= new Faker();
    public static final String URLSIGNUP = "https://login.mailchimp.com/signup/";
    public static final String URL= "https://login.mailchimp.com/";

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "driver/nueva/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
    }

    @Test (priority = 4)
    public void validarTituloTest(){
        String titulo= driver.getTitle();
        //System.out.println(titulo);
        Assert.assertEquals(titulo, "Login | Mailchimp");
    }

    @Test (priority = 3)
    public void iniciarSesionPageTest(){
        WebElement logIn= driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        System.out.println(logIn.getText());
        Assert.assertEquals(logIn.getText(), "Log In");
        WebElement needMail= driver.findElement(By.xpath("//*[contains(text(),'Need a Mailchimp account?')]"));
        System.out.println(needMail.getText());
        Assert.assertEquals(needMail.getText(), "Need a Mailchimp account?");
    }

    @Test (priority = 2)
    public void loginErrorTest(){
        String mail= "XXXXX@gmail.com";


        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys(mail);
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        WebElement msgError= driver.findElement(By.xpath("//*[contains(text(),'Looks like you forgot your password there')]"));
        //System.out.println(msgError.getText());
        Assert.assertEquals(msgError.getText(), "Looks like you forgot your password there, " + mail + ".");

        WebElement checkBox= driver.findElement(By.xpath("//input[@type=\"checkbox\"]"));
        //System.out.println(checkBox.isSelected());
        Assert.assertFalse(checkBox.isSelected());

    }

    @Test (priority = 1)
    public void fakeEmailTest(){
        driver.navigate().to(URLSIGNUP);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), URLSIGNUP);
        driver.findElement(By.xpath("//input[@type=\"email\"]")).sendKeys(faker.internet().emailAddress());

    }

    @Test (dataProvider = "logIn", dataProviderClass = DataProvider.class, priority = 0)
    public void dataProviderEmailTest(String anMail, String anPassword) throws InterruptedException{
        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys(anMail);
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(anPassword);
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(2000);
        WebElement needMail= driver.findElement(By.xpath("//*[contains(text(),'Can we help you recover your username?')]"));
        System.out.println(needMail.getText());
        Assert.assertEquals(needMail.getText(), "Sorry, we couldn't find an account with that username. Can we help you recover your username?");
    }

    @AfterMethod
    public void cerrar(){
        driver.close();

    }

}
