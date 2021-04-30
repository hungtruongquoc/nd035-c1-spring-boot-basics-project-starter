package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pageobject.CredentialPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobject.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobject.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.pageobject.SignUpPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.UUID;

public class CredentialTests extends CloudStorageApplicationTests{
    private CredentialPage page;
    private LoginPage loginPage;
    private SignUpPage signUpPage;

    @BeforeEach
    public void beforeEach() {
        super.beforeEach();

        page = new CredentialPage(driver);
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);

        getPage(signUpPage.getUrl());

        String username = "noteUser" + UUID.randomUUID(), firstName = "John", lastName = "Zaga", password = "1234";
        signUpPage.setUserInfo(username, password, firstName, lastName).signup();
        waitDriver.until(ExpectedConditions.titleIs(signUpPage.getTitle()));
        signUpPage.goToLoginPage();
        waitDriver.until(ExpectedConditions.titleIs(loginPage.getTitle()));
        loginPage.setUserInfo(username, password).login();
        waitDriver.until(ExpectedConditions.titleIs(page.getTitle()));
        page.activateCredentialTab();
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getAddButton()));
    }

    @Test
    public void credentialTabDisplayed() {
        Assertions.assertTrue(page.isCredentialTabDisplayed());
    }

    @Test
    public void createACredential() {
        page.showAddDialog();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getAddDialog()));
        Assertions.assertTrue(page.isCredentialModalDisplayed());
        page.setCredentialInputs("https://abc.com", "abc", "testpass");
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getSaveButton()));
        page.saveCredential();
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getLogoutButton()));
    }

}
