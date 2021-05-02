package com.udacity.jwdnd.course1.cloudstorage.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
        title = "Home";
        url = "home";
    }

    @FindBy(css = "button[type=submit].logout")
    private WebElement logoutButton;

    public void logout() {
        this.logoutButton.click();
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }
}
