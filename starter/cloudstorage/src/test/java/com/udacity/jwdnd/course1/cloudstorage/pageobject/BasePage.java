package com.udacity.jwdnd.course1.cloudstorage.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    protected String title;

    protected String url;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public WebElement findElementByCss(WebDriver driver, String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }
}
