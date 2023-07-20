package org.example.web;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.junit.jupiter.api.*;


class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
        //("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }


    @Test
    void shouldOpenSite() {
        driver.get("http://localhost:9999");
    }

    @Test
    public void shouldCheckFirstLastNameInRussian() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79871235676");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldWritingADoubleSurnameWithHyphenInRussian() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина-Иванова Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79871235676");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void shouldWritingADoubleNameWithHyphenInRussian() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина Анна-Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79871235676");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldWritingADoubleNameByASpaceInRussian() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина Анна Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79871235676");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldWritingADoubleSurnameByASpaceInRussian() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина Иванова Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79871235676");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldWritingTheNameWithLetterEInRussian() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Артёмова Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79871235676");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldWritingTwoLettersInRussian() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Су Ли");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79871235676");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
}



