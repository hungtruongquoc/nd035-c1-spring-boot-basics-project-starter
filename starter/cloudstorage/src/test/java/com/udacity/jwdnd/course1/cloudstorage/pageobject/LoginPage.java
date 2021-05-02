package com.udacity.jwdnd.course1.cloudstorage.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(css = "button[type=submit]")
    private WebElement submitBtn;

    @FindBy(css = ".logout-alert")
    private WebElement logoutText;

    @FindBy(css = ".invalid-alert")
    private WebElement invalidText;

    @FindBy(css = ".signup-link")
    private WebElement signUpLink;

    public LoginPage(WebDriver driver) {
        super(driver);
        title = "Login";
        url = "login";
    }

    public LoginPage setUserInfo(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        return this;
    }

    public void login() {
        submitBtn.click();
    }

    public boolean isLogout() {
        return logoutText.isDisplayed();
    }

    public boolean isInvalidTextDisplayed() {
        return invalidText.isDisplayed();
    }

    public boolean isLogoutTextDisplayed() {
        return logoutText.isDisplayed();
    }

    public void goToSignUp() {
        signUpLink.click();
    }
}