package com.sparta.greg.pom.pagesTest.components;

import com.sparta.greg.pom.pages.components.Login;
import com.sparta.greg.pom.pages.trainer.HomeTrainer;
import com.sparta.greg.pom.pages.trainer.TrainerConsultancySkills;
import com.sparta.greg.pom.pages.trainer.TrainerGuide;
import com.sparta.greg.pom.pages.trainer.WeeklyAttendance;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

class SideBarTrainerTest {

    private static final Properties properties = new Properties();
    private static WebDriver webDriver;
    private static WeeklyAttendance weeklyAttendance;
    private static String trainerUsername;
    private static String trainerPassword;
    private static HomeTrainer homeTrainer;

    @BeforeAll
    static void setup() {
        webDriver = new ChromeDriver();
        Login login = new Login(webDriver);
        try {
            properties.load(new FileReader("src/test/resources/login.properties"));
            trainerUsername = properties.getProperty("trainerUsername");
            trainerPassword = properties.getProperty("trainerPassword");
        } catch (IOException e) {
            e.printStackTrace();
        }

        login.logInAsTrainer(trainerUsername, trainerPassword);
        webDriver.get("http://localhost:8080/trainer/weekly-attendance");
        weeklyAttendance = new WeeklyAttendance(webDriver);
    }

    @AfterEach
    void refresh() {
        webDriver.navigate().refresh();
    }

    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

    @Test
    @DisplayName("goToConsultancySkillsTest")
    void goToConsultancySkillsTest() {
        weeklyAttendance.getSideBarTrainer().selectView();
        Assertions.assertEquals(TrainerConsultancySkills.class, weeklyAttendance.getSideBarTrainer().goToConsultancySkills().getClass());
    }

    @Test
    @DisplayName("go to guide page")
    void goToGuidePage() {
        weeklyAttendance.getSideBarTrainer().selectView();
        Assertions.assertEquals(TrainerGuide.class, weeklyAttendance.getSideBarTrainer().goToTraineeGuide().getClass());
    }

    @Test
    @DisplayName("isTrainerOptionsExpanded returns false when not expanded")
    void isTrainerOptionsExpandedReturnsFalseWhenNotExpanded() {
        Assertions.assertFalse(weeklyAttendance.getSideBarTrainer().isTrainerOptionsExpanded());
    }

    @Test
    @DisplayName("isTrainerOptionsExpanded returns true when expanded")
    void isTrainerOptionsExpandedReturnsTrueWhenExpanded() {
        weeklyAttendance.getSideBarTrainer().clickTrainerOptions();
        Assertions.assertTrue(weeklyAttendance.getSideBarTrainer().isTrainerOptionsExpanded());
    }
}