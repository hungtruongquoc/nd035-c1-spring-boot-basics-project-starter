package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pageobject.CredentialPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobject.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobject.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.pageobject.SignUpPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.UUID;

public class CredentialTests extends CloudStorageApplicationTests {
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
        waitDriver.until(ExpectedConditions.textToBePresentInElement(page.getSuccessAlert(),
                "New credential created"));
        Assertions.assertTrue(page.getSuccessAlert().isDisplayed());
        Assertions.assertTrue(page.getItemRow(driver, 1).isDisplayed());
        Assertions.assertNotEquals(page.getTableItemPassword(driver, 1).getText(), "testpass");
    }

    @Test
    public void deleteACredential() {
        page.showAddDialog();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getAddDialog()));
        Assertions.assertTrue(page.isCredentialModalDisplayed());
        page.setCredentialInputs("https://delete.com", "abc", "deletepass");
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getSaveButton()));
        page.saveCredential();
        WebElement itemDelete = page.getTableItemDeleteButton(driver, 1);
        waitDriver.until(ExpectedConditions.elementToBeClickable(itemDelete));
        Assertions.assertTrue(page.getItemRow(driver, 1).isDisplayed());
        Assertions.assertEquals(page.getTableItemUrl(driver, 1).getText(), "https://delete.com");
        itemDelete.click();
        WebElement confirmDelete = page.getDeleteConfirmButton(driver);
        waitDriver.until(ExpectedConditions.elementToBeClickable(confirmDelete));
        confirmDelete.click();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getSuccessAlert()));
        Assertions.assertEquals(page.getSuccessAlert().getText(), "The provided credential was deleted successfully.");

    }

    @AfterEach
    public void afterEach() {
        page.logout();
        super.afterEach();
    }
}
