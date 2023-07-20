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


class FailedPathTest {
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
    public void shouldCheckFirstLastNameInEnglish() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Oskina Margarita");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79871235676");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldEmptyNameField() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79871235675");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckTheSymbolInName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Маргарита№ Оськина");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckShotPhoneNumber() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+754446878");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckLongerPhoneNumber() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7997678123456");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckEmptyPhoneField() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckPhoneFieldWithoutPlus() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("79998887755");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckboxEmptyField() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887755");
        driver.findElement(By.className("button")).click();

        Boolean expected = true;
        Boolean actual = driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid")).isDisplayed();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckRussianLettersInPhoneField() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("арими");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCheckEnglishLettersInPhoneField() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Оськина Маргарита");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("kfif");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();

        Assertions.assertEquals(expected, actual);
    }
}