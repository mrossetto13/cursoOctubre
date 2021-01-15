package Clase11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EjercicioGit {

    private WebDriver getDriver(String URL) {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }

    @Test
    public void spotifyByPlaceHolder(){
        WebDriver driver = getDriver("https://www.spotify.com/ar/signup/");

        driver.findElement(By.cssSelector("input[placeholder=\"Introduce tu correo electrónico.\"]")).sendKeys("test@test.com");
        driver.findElement(By.cssSelector("input[placeholder=\"Vuelve a introducir tu correo electrónico.\"]")).sendKeys("test@test.com");
        driver.findElement(By.cssSelector("input[placeholder=\"Crea una contraseña.\"]")).sendKeys("test123!");
        driver.findElement(By.cssSelector("input[placeholder=\"Introduce un nombre de perfil.\"]")).sendKeys("pepito");

        driver.findElement(By.cssSelector("input[placeholder=\"DD\"]")).sendKeys("13");
        Select mes= new Select(driver.findElement(By.cssSelector("*[name=\"month\"]")));
        mes.selectByIndex(05);
        driver.findElement(By.cssSelector("input[placeholder=\"AAAA\"]")).sendKeys("1992");

        WebElement man= driver.findElement(By.xpath("//*[contains(text(), 'Hombre')]"));
        man.click();

        WebElement term = driver.findElement(By.xpath("//*[contains(text(), 'Compartir mis datos de registro con los proveedores de contenido de Spotify para fines de marketing.')]"));
        term.click();

        driver.findElement(By.cssSelector("*[type='submit']")).click();
    }
}
