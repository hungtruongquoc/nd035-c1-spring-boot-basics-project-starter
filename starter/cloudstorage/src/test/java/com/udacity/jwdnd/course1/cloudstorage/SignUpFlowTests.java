package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pageobject.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pageobject.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobject.SignUpPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.UUID;

public class SignUpFlowTests extends CloudStorageApplicationTests{
    private SignUpPage page;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        page = new SignUpPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        getPage(page.getUrl());
    }

    @Test
    public void signUpPageDisplayed() {
        Assertions.assertEquals(page.getTitle(), driver.getTitle());
    }

    @Test
    public void signUpWithUserInfo() {
        String username = "jzProg" + UUID.randomUUID(), firstName = "John", lastName = "Zaga", password = "1234";
        page.setUserInfo(username, password, firstName, lastName).signup();
        waitDriver.until(ExpectedConditions.titleIs(page.getTitle()));
        Assertions.assertTrue(page.successSignUp());
        page.goToLoginPage();
        waitDriver.until(ExpectedConditions.titleIs(loginPage.getTitle()));
        Assertions.assertEquals(loginPage.getTitle(), driver.getTitle());
        loginPage.setUserInfo(username, password).login();
        waitDriver.until(ExpectedConditions.titleIs(homePage.getTitle()));
        Assertions.assertEquals(homePage.getTitle(), driver.getTitle());
        homePage.logout();
        waitDriver.until(ExpectedConditions.titleIs(loginPage.getTitle()));
        Assertions.assertTrue(loginPage.isLogoutTextDisplayed());
        getPage(homePage.getUrl());
        waitDriver.until(ExpectedConditions.titleIs(loginPage.getTitle()));
        Assertions.assertEquals(loginPage.getTitle(), driver.getTitle());
    }
}
