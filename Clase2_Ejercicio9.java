import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Clase2_Ejercicio9  {

    private WebDriver getDriver(String URL) {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
        return driver;
    }

    @Test
    public void completeRegistration() throws  InterruptedException{
        WebDriver driver = getDriver("https://www.facebook.com");
        driver.findElement(By.linkText("Crear cuenta nueva")).click();
        Thread.sleep(1000);


        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.name("lastname")).sendKeys("Smith");
        driver.findElement(By.name("reg_email__")).sendKeys("5555555");
        driver.findElement(By.id("password_step_input")).sendKeys("123456789");

        //Fecha de nac 26/6/1980
        setBirthdate( driver, "26", "jun", "1980");

        //Selecciono hombre
        driver.findElement(By.id("u_1_5")).click();
    }

    public void setBirthdate(WebDriver driver, String day, String month, String year){
        WebElement dias = driver.findElement(By.name("birthday_day"));
        Select comboDias = new Select(dias);
        comboDias.selectByValue(day);


        WebElement meses = driver.findElement(By.name("birthday_month"));
        Select comboMeses = new Select(meses);
        comboMeses.selectByVisibleText(month);

        WebElement año = driver.findElement(By.name("birthday_year"));
        Select comboYear = new Select(año);
        comboYear.selectByVisibleText(year);
    }
}
