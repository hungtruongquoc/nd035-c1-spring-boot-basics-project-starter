package com.udacity.jwdnd.course1.cloudstorage.pageobject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.UUID;

public class CredentialPage extends HomePage{

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "credential-add")
    private WebElement addButton;

    @FindBy(id = "credentialModalLabel")
    private WebElement dialogTitle;

    @FindBy(id = "credential-url")
    private WebElement urlInput;

    @FindBy(id = "credential-username")
    private WebElement usernameInput;

    @FindBy(id = "credential-password")
    private WebElement passwordInput;

    public WebElement getSaveButton() {
        return saveCredentialButton;
    }

    @FindBy(id = "credential-submit")
    private WebElement saveCredentialButton;

    public WebElement getAddDialog() {
        return addDialog;
    }

    @FindBy(id = "credentialModal")
    private WebElement addDialog;

    public CredentialPage(WebDriver driver) {
        super(driver);
    }

    public CredentialPage formReset() {
        usernameInput.clear();
        passwordInput.clear();
        urlInput.clear();
        return this;
    }

    public CredentialPage setCredentialInputs(String url, String username, String password) {
        urlInput.sendKeys(url);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        return this;
    }
    
    public CredentialPage activateCredentialTab() {
        credentialTab.click();
        return this;
    }

    public WebElement getAddButton() {
        return addButton;
    }

    public Boolean isCredentialTabDisplayed() {
        return addButton.isDisplayed();
    }

    public CredentialPage showAddDialog() {
        addButton.click();
        return this;
    }

    public Boolean isCredentialModalDisplayed() {
        return dialogTitle.isDisplayed();
    }

    public void saveCredential() {
        getSaveButton().click();
    }
}
