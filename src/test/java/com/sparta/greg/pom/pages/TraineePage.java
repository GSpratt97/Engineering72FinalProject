package com.sparta.greg.pom.pages;

import org.openqa.selenium.By;

public abstract class TraineePage extends Page {

    By traineeOptionsButton = new By.ByCssSelector("a[data-target*='#collapseUtilities']");
    By feedbackFormLink = new By.ByCssSelector("a[href='/traineeRecentReport']");
    By reportsHistoryLink = new By.ByCssSelector("a[href='/trainee/report']");
    By attendanceHistoryLink = new By.ByCssSelector("a[href='/trainee/trainee-attendance']");

    public void clickTraineeOptions() {
        webDriver.findElement(traineeOptionsButton).click();
    }

    public FeedbackForm goToFeedbackForm() {
        webDriver.findElement(feedbackFormLink).click();
        return new FeedbackForm(webDriver);
    }

    public ReportTrainee goToReportsHistory() {
        webDriver.findElement(reportsHistoryLink).click();
        return new ReportTrainee(webDriver);
    }

    public WeeklyAttendance goToAttendanceHistory() {
        webDriver.findElement(attendanceHistoryLink).click();
        return new WeeklyAttendance(webDriver);
    }
}