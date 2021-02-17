package com.sparta.greg.pom.pagesTest.trainer;

import com.sparta.greg.pom.pages.components.Login;
import com.sparta.greg.pom.pages.components.PropertyLoader;
import com.sparta.greg.pom.pages.trainer.TrainerConsultancySkills;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TrainerConsultancySkillsTests {

    private static WebDriver webDriver;
    private static TrainerConsultancySkills trainerConsultancySkills;

    @Before
    public void setup() {
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:8080/login");
        PropertyLoader.loadProperties();
        String trainerUsername = PropertyLoader.properties.getProperty("trainerUsername");
        String trainerPassword = PropertyLoader.properties.getProperty("trainerPassword");
        Login login = new Login(webDriver);
        login.logInAsTrainer(trainerUsername, trainerPassword);

        webDriver.get("http://localhost:8080/consultancy");
        trainerConsultancySkills = new TrainerConsultancySkills(webDriver);
    }

    @Test
    @DisplayName("Click what these skills make button")
    public void clickWhatTheseSkillsMakeButton() throws InterruptedException {
        trainerConsultancySkills.clickWhatTheseSkillsMakeButton();
        Thread.sleep(2000);
        Assertions.assertTrue(webDriver.findElement(By.cssSelector("h5[id='ModalLabel']")).isDisplayed());
    }
    @Test
    @DisplayName("Click back to dashBoard button on consultancy skills")
    public void clickBackToDashBoardButtonOnConsultancySkillsTrainerLogin() {
        trainerConsultancySkills.clickBackToDashBoardButtonOnConsultancySkillsTrainerLogin();
        Assertions.assertEquals("http://localhost:8080/trainer/home",webDriver.getCurrentUrl());
    }
    @Test
    @DisplayName("Click Studious Button On Consultancy Skills")
    public void clickStudiousButtonOnConsultancySkills() {
        trainerConsultancySkills.clickCollapsableButtonOnConsultancySkills(TrainerConsultancySkills.buttonsOnTheConsultancySkills.STUDIOUS);
    }
    @Test
    @DisplayName("Click All Skills Button On Consultancy Skills")
    public void clickAllButtonOnConsultancySkills() throws InterruptedException {
        trainerConsultancySkills.clickAllCollapsableButtonOnConsultancySkills();
    }
    @AfterEach
    void close(){webDriver.close();}
}

