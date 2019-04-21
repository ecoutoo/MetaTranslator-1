package uk.uoa.cs.princSwEng.test;

import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import org.openqa.selenium.support.ui.Select;
import uk.uoa.cs.princSwEng.resource.Sentence;
import uk.uoa.cs.princSwEng.resource.Survey;
import uk.uoa.cs.princSwEng.database.SearchRandomSentenceDatabase;
import uk.uoa.cs.princSwEng.database.CreateSurveyDatabase;
import uk.uoa.cs.princSwEng.servlet.AbstractDatabaseServlet;

import javax.mail.SendFailedException;
import java.util.concurrent.TimeUnit;

//import static org.springframework.test.web.servlet.request.Mock
//import org.springframework.test.web.servlet.MockMvc;


public class JWebUnitTest {
    final Scanner pause = new Scanner(System.in);
    private static String statement;
    private Connection con;
    private static ResultSet rs;
    //private static final WebDriver[] drivers = {new ChromeDriver(), new FirefoxDriver(), new EdgeDriver()};
    private WebDriver driver;
    private WebElement loginLink;
    private WebElement passwordLink;
    private WebElement username;
    private WebElement name;
    private WebElement surname;
    private WebElement email;
    private WebElement password;
    private WebElement registrationSubmit;


    @Before
    @Test
    public void zerothTest() {
        String username = "-1";
        //Check that home page is working correctly...
        driver = new ChromeDriver();
        driver.close();
        driver.quit();
    }

    //Check that user creation is working.
    //Check that error given if email in incorrect format.
    //Query all columns of database to check.
    //Check that emails are being sent

    @Test
    public void createEmptyUserTest() {
        driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8080/Echo-Team-0.1/registration");
        assertEquals("Register", driver.getTitle(), "Title check failed!");
        assert(driver.getPageSource().contains("If you want to create an account, please register below."));

        username = driver.findElement(By.id("username"));
        name = driver.findElement(By.id("name"));
        surname = driver.findElement(By.id("surname"));
        email = driver.findElement(By.id("email"));
        password = driver.findElement(By.id("password"));
        registrationSubmit = driver.findElement(By.id("registrationSubmit"));

        username.sendKeys("foo");
        name.sendKeys("foo");
        surname.sendKeys("foo");
        email.sendKeys("foo");
        password.sendKeys("foo");

    }

    @Test
    public void createIncorrectEmailTest() {

    }

    @Test
    public void createUsers() {

    }
    /*
    @Test void incorrectKeyInputTest() {
        driver = new ChromeDriver();
        //Check that home page is working correctly...
        driver.navigate().to("http://localhost:8080/Echo-Team-0.1/login");
        assertEquals("Login", driver.getTitle(), "Title check failed!");
        loginLink = driver.findElement(By.name("rkey"));
        passwordLink = driver.findElement(By.name("password"));
        String researcherKey = "-1";
        loginLink.sendKeys(researcherKey);
        passwordLink.sendKeys("pass");
        WebElement submit = driver.findElement(By.id("loginButton"));
        assert(driver.getPageSource().contains("Your researcher key does not exist, please check and try again."));
        loginLink.sendKeys();
        submit.click();
        //TimeUnit.SECONDS.sleep(2);

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        }
        catch (InterruptedException e)
        {
            System.err.println(e);
        }


        driver.close();
        driver.quit();
    }
    */


    @Test
    public void surveyCreationTest() {
        String username = "-1";
        driver = new ChromeDriver();
        //Check that home page is working correctly...
        driver.navigate().to("http://localhost:8080/Echo-Team-0.1/");
        assertEquals("MetaTranslate", driver.getTitle(), "Title check failed!");
        //Check that redirects work while user isn't logged in.
        //Check that login page is accessible directly, and that user can login.
        driver.navigate().to("http://localhost:8080/Echo-Team-0.1/login");
        WebElement loginLink = driver.findElement(By.name("rkey"));
        WebElement passwordLink = driver.findElement(By.name("password"));
        String researcherKey = "3";
        loginLink.sendKeys(researcherKey);
        passwordLink.sendKeys("pass");
        WebElement submit = driver.findElement(By.id("loginButton"));
        submit.click();
        //TimeUnit.SECONDS.sleep(2);

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch (InterruptedException e)
        {
            System.err.println(e);
        }

        //User is successfully logged in
        //assertEquals("Create Survey", driver.getTitle(), "Title check failed!"); //Temporarily on this page?

        assertEquals("User Page", driver.getTitle(), "Title check failed!");
        try {
            statement = "SELECT * FROM USERS WHERE id = '" + researcherKey + "'";
            try {
                con = AbstractDatabaseServlet.getConnection();
            }

            catch (URISyntaxException e) {
                System.err.println("URISyntaxException");
                System.err.println(e);
            }
            PreparedStatement pstmt = con.prepareStatement(statement);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
            }

            else {
                assert(false);
            }
        }
        catch (SQLException e) {
            System.err.println("An SQL exception occured!");
            System.err.println(e);
        }

        assert(driver.getPageSource().contains("WELCOME, " + username));
        assert(driver.getPageSource().contains("<b>Researcher ID</b> " + researcherKey));
        WebElement createSurveyButton = driver.findElement(By.id("displayrkey_createSurvey"));
        createSurveyButton.click();

        try {
            TimeUnit.SECONDS.sleep(2);
        }
        catch (InterruptedException e)
        {
            System.err.println(e);
        }

        assertEquals("Create Survey", driver.getTitle(), "Title check failed!");

        //TRANSLATOR ENGINE
        assert(driver.getPageSource().contains("TRANSLATOR ENGINE"));
        assert(driver.getPageSource().contains("google"));
        assert(driver.getPageSource().contains("yandex"));
        assert(driver.getPageSource().contains("TARGET LANGUAGE"));
        assert(driver.getPageSource().contains("Chinese"));
        assert(driver.getPageSource().contains("Italian"));
        assert(driver.getPageSource().contains("French"));
        assert(driver.getPageSource().contains("German"));
        assert(driver.getPageSource().contains("NUMBER OF SENTENCES"));
        assert(driver.getPageSource().contains("5"));
        assert(driver.getPageSource().contains("10"));
        assert(driver.getPageSource().contains("15"));
        assert(driver.getPageSource().contains("CORPORA"));
        assert(driver.getPageSource().contains("VUA"));
        assert(driver.getPageSource().contains("MOH"));
        assert(driver.getPageSource().contains("FLA"));

        WebElement createSurveyManagerPage = driver.findElement(By.id("surveyCreationManager"));
        WebElement radioButton10 = driver.findElement(By.id("radio10"));
        radioButton10.click();
        //String i = pause.nextLine();

        String[] APIs = {"googleButton", "yandexButton"};
        String[] languages = {"Chinese", "Italian", "French", "German"};
        String[] corpora = {"buttonVUA", "buttonMOH", "buttonFLA"};
        String[] numbers = {"radio5", "radio10", "radio15"};

        WebElement api;
        WebElement languageWE;
        String language;
        WebElement corpus;
        WebElement number;

        for (int i = 0; i < APIs.length; i++) {
            api = driver.findElement(By.id(APIs[i]));
            api.click();
            for (int j = 0; j < languages.length; j++) {
                language =  languages[j];
                languageWE = driver.findElement(By.id("languagesButton"));
                new Select(languageWE).selectByVisibleText(language);
                languageWE.click();
                for (int k = 0; k < corpora.length; k++) {
                    corpus = driver.findElement(By.id(corpora[k]));
                    corpus.click();
                    for (int l = 0; l < numbers.length; l++) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(200);
                        }
                        catch (InterruptedException e) {
                            System.err.println("InterruptedException has occured");
                            System.err.println(e);
                        }

                        number = driver.findElement(By.id(numbers[l]));
                        number.click();
                        createSurveyManagerPage = driver.findElement(By.id("surveyCreationManager"));
                        createSurveyManagerPage.click();
                        assertEquals("Metatranslate", driver.getTitle(), "Title check failed!"); //Change this title
                        assert(driver.getPageSource().contains("RESEARCHER ID"));
                        assert(driver.getPageSource().contains("Thank you for creating a new survey"));
                        String keySurveyText = "The unique key of your survey is ";
                        assert(driver.getPageSource().contains(keySurveyText));
                        String uniquekeyvalue = driver.findElement(By.id("uniqueKeyValue")).getText().replace(keySurveyText, "");
                        System.out.println(uniquekeyvalue);

                        try {
                            statement = "SELECT * FROM SURVEYS WHERE id = '" + uniquekeyvalue + "'";
                            try {
                                con = AbstractDatabaseServlet.getConnection();
                            }

                            catch (URISyntaxException e) {
                                System.err.println("URISyntaxException");
                                System.err.println(e);
                            }
                            PreparedStatement pstmt = con.prepareStatement(statement);
                            rs = pstmt.executeQuery();
                            //assert(rs.next());
                            if (!rs.next()) {
                                assert(false);
                            }
                        }
                        catch (SQLException e) {
                            System.err.println("An SQL exception occured!");
                            System.err.println(e);
                        }

                        driver.navigate().to("http://localhost:8080/Echo-Team-0.1/manager");
                        System.out.println("Navigated to Manager");

                    }
                }
            }
        }


        //Give survey key an ID to check if it can be queried?

        driver.close();
        driver.quit();
    }



}