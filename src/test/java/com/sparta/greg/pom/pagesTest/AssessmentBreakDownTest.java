package com.sparta.greg.pom.pagesTest;

import com.sparta.greg.pom.pages.AssessmentBreakdown;
import com.sparta.greg.pom.pages.Assessments;
import com.sparta.greg.pom.pages.Login;
import io.cucumber.java.bs.A;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class
AssessmentBreakDownTest {
    private static WebDriver webDriver;
    private static Properties properties = new Properties();
    private static String usernameTrainer;
    private static String passwordTrainer;
    private static AssessmentBreakdown assessmentBreakdownPage;

    @BeforeEach
    public void setup() {
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/login");
        try {
            properties.load(new FileReader("src/test/resources/login.properties"));
            usernameTrainer = properties.getProperty("trainerUsername");
            passwordTrainer = properties.getProperty("trainerPassword");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Login login = new Login(webDriver);
        login.logInAsTrainer(usernameTrainer, passwordTrainer);
        webDriver.get("http://localhost:8080/trainer/assessments/41");
        assessmentBreakdownPage = new AssessmentBreakdown(webDriver);
    }

    @Test
    @DisplayName("Test the first click plus icon")
    void clickFirstPlusIcon() {
        assessmentBreakdownPage.clickPlusIconFirstModule();
        Assertions.assertEquals(webDriver.findElement(By.cssSelector("tr[href*='collapseSQL']")).getText(),"SQL 30.9% FAIL 11/22/2019");
    }

    @Test
    @DisplayName("Test the second click plus icon")
    void clickSecondPlusIcon() {
        assessmentBreakdownPage.clickPlusIconSecondModule();
        Assertions.assertEquals(webDriver.findElement(By.cssSelector("tr[href*='collapseNotSQL']")).getText(),"NotSQL 78.0% B- 11/22/2019");

    }
/*
    @AfterEach
	void close(){
		webDriver.close();
	}*/
}
