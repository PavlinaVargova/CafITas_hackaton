package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlaseniNaKurz {

    // Nejdrive konstanty
    private static final String URL_APLIKACE = "http://czechitas-datestovani-hackathon.cz/en/";

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void prihlaseniKUctu() {
        prohlizec.navigate().to(URL_APLIKACE);
        WebElement odkazPrihlasit = prohlizec.findElement(By.xpath("/html/body/div[1]/div[1]/header/div[3]/div/div/div[7]/ul/li/a/span"));
        odkazPrihlasit.click();

        WebElement polickoEmail = prohlizec.findElement(By.id("email"));
        polickoEmail.sendKeys("hjh@gmail.com");
        WebElement polickoHeslo = prohlizec.findElement(By.id("passwd"));
        polickoHeslo.sendKeys("12345");
        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div[2]/form/div/p[2]/button/span"));
        tlacitkoPrihlasit.click();

        Assertions.assertTrue(prohlizec.getCurrentUrl().endsWith("/my-account"));
    }

    @Test
    public void uzivatelPoPrihlaseniKeSvemuUctuMusiBytSchopenZkontrolovatSveUdaje() {
        prohlizec.navigate().to(URL_APLIKACE);
        WebElement odkazPrihlasit = prohlizec.findElement(By.xpath("/html/body/div[1]/div[1]/header/div[3]/div/div/div[7]/ul/li/a/span"));
        odkazPrihlasit.click();
        WebElement polickoEmail = prohlizec.findElement(By.id("email"));
        polickoEmail.sendKeys("hjh@gmail.com");
        WebElement polickoHeslo = prohlizec.findElement(By.id("passwd"));
        polickoHeslo.sendKeys("12345");
        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div[2]/form/div/p[2]/button/span"));
        tlacitkoPrihlasit.click();
        Assertions.assertTrue(prohlizec.getCurrentUrl().endsWith("/my-account"));

        WebElement tlacitkoMojeAdresa = prohlizec.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div/div/ul/li[3]/a/span"));
        tlacitkoMojeAdresa.click();
        WebElement jmeno = prohlizec.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/div/div/ul/li[2]/span[1]"));
        WebElement prijmeni = prohlizec.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/div/div/ul/li[2]/span[2]"));
        WebElement ulice = prohlizec.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/div/div/ul/li[5]/span"));
        WebElement smerovaciCislo = prohlizec.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/div/div/ul/li[7]/span[1]"));
        WebElement mesto = prohlizec.findElement(By.className("address_city"));
        WebElement stat = prohlizec.findElement(By.xpath("/html/body/div/div[2]/div/div[2]/div/div[1]/div/div/ul/li[8]/span"));
        WebElement telefoniCislo = prohlizec.findElement(By.className("address_phone"));

        Assertions.assertEquals("Hjh", jmeno.getText());
        Assertions.assertEquals("Ho", prijmeni.getText());
        Assertions.assertEquals("kjhjkhkkhkhk 14", ulice.getText());
        Assertions.assertEquals("61500", smerovaciCislo.getText());
        Assertions.assertEquals("brno", mesto.getText());
        Assertions.assertEquals("Czech Republic", stat.getText());
        Assertions.assertEquals("123456789", telefoniCislo.getText());
    }

    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}
