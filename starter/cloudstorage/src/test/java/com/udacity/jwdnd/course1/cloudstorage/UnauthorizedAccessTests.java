package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pageobject.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobject.SignUpPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UnauthorizedAccessTests extends CloudStorageApplicationTests {
    private LoginPage page;
    private SignUpPage signupPage;

    public UnauthorizedAccessTests() {
        page = new LoginPage(driver);
        signupPage = new SignUpPage(driver);
    }

    @Test
    public void testLoadLoginByDefault() {
        getPage("");
        Assertions.assertEquals(page.getTitle(), driver.getTitle());
        getPage("home");
        Assertions.assertEquals(page.getTitle(), driver.getTitle());
        getPage(signupPage.getUrl());
        Assertions.assertEquals(signupPage.getTitle(), driver.getTitle());
    }

    @Test
    public void testUnauthorizedLogin() {
        LoginPage page = new LoginPage(driver);
        getPage(page.getUrl());
        page.setUserInfo("test", "password");
        page.login();
        waitDriver.until(ExpectedConditions.titleIs(page.getTitle()));
        Assertions.assertEquals(page.getTitle(), driver.getTitle());
        Assertions.assertTrue(page.isInvalidTextDisplayed());
    }
}
