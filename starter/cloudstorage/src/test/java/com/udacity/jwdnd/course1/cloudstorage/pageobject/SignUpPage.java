package com.udacity.jwdnd.course1.cloudstorage.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
        title = "Sign Up";
        url = "signup";
    }

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "inputFirstName")
    private WebElement firstName;

    @FindBy(id = "inputLastName")
    private WebElement lastName;

    @FindBy(css = ".form-submission[type=submit]")
    private WebElement submitBtn;

    @FindBy(css = ".login-navigator")
    private WebElement goToLoginBtn;

    @FindBy(css = ".signup-success-text")
    private WebElement signupText;

    public SignUpPage setUserInfo(String username, String password, String firstName, String lastName) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        return this;
    }

    public void signup() {
        submitBtn.click();
    }

    public boolean successSignUp() {
        return signupText.isDisplayed();
    }

    public void goToLoginPage() {
        goToLoginBtn.click();
    }
}
