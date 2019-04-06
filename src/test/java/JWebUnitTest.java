package uk.uoa.cs.princSwEng.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JWebUnitTest {

    @Test
    public void fooTest() {

    }
    @Test
    public void firstTest() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8080/Echo-Team-0.1/");
        assertEquals("MetaTranslate", driver.getTitle(), "Title check failed!");
        driver.close();
        driver.quit();
    }


    /*
    @Test
    public void testIndexPage() {
        beginAt("http://localhost:8080/Echo-Team/");
        assertTitleEquals("Metatranslate");
        assertLinkPresent("manager");
        clickLink("manager");
        assertTitleEquals("Create Survey");
        System.out.println("testIndexPage done!");
    }

    @Test
    public void testManagerPage() {
        beginAt("http://localhost:8080/Echo-Team/manager");
        assertTitleEquals("Create Survey");
        assertLinkPresent("index");
        clickLink("index");
        assertTitleEquals("Metatranslate");
    }


     */


}
