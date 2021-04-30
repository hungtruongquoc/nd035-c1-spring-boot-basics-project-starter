package com.udacity.jwdnd.course1.cloudstorage;

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

public class NoteTests extends CloudStorageApplicationTests {
    private NotePage page;
    private LoginPage loginPage;
    private SignUpPage signUpPage;

    @BeforeEach
    public void beforeEach() {
        super.beforeEach();

        page = new NotePage(driver);
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
        page.activateNoteTab();
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getAddButton()));
    }

    @Test
    public void noteTabDisplayed() {
        Assertions.assertTrue(page.isNoteTabDisplayed());
    }

    @Test
    public void createANote() {
        page.showAddDialog();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getAddDialog()));
        Assertions.assertTrue(page.isNoteModalDisplayed());
        page.setNoteContent("Title", "Test Note");
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getSaveNoteButton()));
        page.saveNote();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getSuccessAlert()));
        Assertions.assertTrue(page.isSuccessAlertDisplayed());
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getLogoutButton()));
    }

    @Test
    public void deleteANote() {
        page.showAddDialog();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getAddDialog()));
        Assertions.assertTrue(page.isNoteModalDisplayed());
        page.setNoteContent("Title", "Test Note");
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getSaveNoteButton()));
        page.saveNote();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getSuccessAlert()));
        Assertions.assertTrue(page.isSuccessAlertDisplayed());
        WebElement deleteButton = page.findItemDeleteButton(driver,1);
        Assertions.assertTrue(deleteButton.isDisplayed());
        deleteButton.click();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getSuccessAlert()));
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getConfirmDeleteNoteButton()));
        page.clickConfirmDeleteButton();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getSuccessAlert()));
        Assertions.assertTrue(page.isSuccessAlertDisplayed());
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getLogoutButton()));
    }

    @Test
    public void editANote() {
        page.showAddDialog();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getAddDialog()));
        Assertions.assertTrue(page.isNoteModalDisplayed());
        page.setNoteContent("Title", "Test Note");
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getSaveNoteButton()));
        page.saveNote();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getSuccessAlert()));
        WebElement editButton = page.findItemEditButton(driver, 1);
        waitDriver.until(ExpectedConditions.elementToBeClickable(editButton));
        editButton.click();
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(page.getTitleTextBox(), page.getDescriptionTextBox()));
        Assertions.assertTrue(page.getTitleTextBox().isDisplayed());
        Assertions.assertTrue(page.getDescriptionTextBox().isDisplayed());
        page.setNoteContent("New Title", "Title Description");
        page.saveNote();
        WebElement itemRow = page.getItemRow(driver, 1);
        waitDriver.until(ExpectedConditions.visibilityOfAllElements(itemRow));
        Assertions.assertEquals("New Title", page.getItemTitle(driver, 1).getText());
        Assertions.assertEquals("Title Description", page.getItemDescription(driver, 1).getText());
        waitDriver.until(ExpectedConditions.elementToBeClickable(page.getLogoutButton()));
    }

    @AfterEach
    public void afterEach() {
        page.logout();
        super.afterEach();
    }
}
