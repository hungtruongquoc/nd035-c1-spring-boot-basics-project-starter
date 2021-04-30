package com.udacity.jwdnd.course1.cloudstorage.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NotePage extends HomePage {

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(css = "button[type=button].add-note")
    private WebElement addNoteButton;

    @FindBy(id = "noteModal")
    private WebElement addDialog;

    @FindBy(id = "noteModalLabel")
    private WebElement dialogTitle;

    @FindBy(css = "#noteModal .modal-close")
    private WebElement closeModalButton;

    @FindBy(css = "#noteModal .save-note")
    private WebElement saveNoteButton;

    @FindBy(id = "note-title")
    private WebElement titleTextBox;

    @FindBy(id = "note-description")
    private WebElement descriptionTextBox;

    @FindBy(css = "#nav-notes div.alert-success")
    private WebElement successAlert;

    @FindBy(css = "#deleteNoteModal")
    private WebElement noteDeleteDialog;

    @FindBy(css = "#deleteNoteModal button.note-delete")
    private WebElement confirmDeleteNoteButton;

    public NotePage(WebDriver driver) {
        super(driver);
    }

    public NotePage activateNoteTab() {
        noteTab.click();
        return this;
    }

    public Boolean isNoteTabDisplayed() {
        return addNoteButton.isDisplayed();
    }

    public WebElement getAddButton() {
        return addNoteButton;
    }

    public WebElement getSuccessAlert() {
        return successAlert;
    }

    public NotePage showAddDialog() {
        addNoteButton.click();
        return this;
    }

    public WebElement getAddDialog() {
        return addDialog;
    }

    public WebElement getSaveNoteButton() {
        return saveNoteButton;
    }

    public WebElement getNoteDeleteDialog() {
        return noteDeleteDialog;
    }

    public WebElement getConfirmDeleteNoteButton() {
        return confirmDeleteNoteButton;
    }

    public Boolean isNoteModalDisplayed() {
        return dialogTitle.isDisplayed();
    }

    public Boolean isSuccessAlertDisplayed() {
        return successAlert.isDisplayed();
    }

    public NotePage closeNoteModalDialg() {
        closeModalButton.click();
        return this;
    }

    public NotePage setNoteContent(String title, String description) {
        descriptionTextBox.clear();
        titleTextBox.clear();
        descriptionTextBox.sendKeys(description);
        titleTextBox.sendKeys(title);
        return this;
    }

    public NotePage clickConfirmDeleteButton() {
        confirmDeleteNoteButton.click();
        return this;
    }

    public void saveNote() {
        saveNoteButton.click();
    }

    public WebElement findItemDeleteButton(WebDriver driver, Integer itemId) {
        return findElementByCss(driver, "a.note-delete[data-id='" + itemId.toString() + "']");
    }

    public WebElement findItemEditButton(WebDriver driver, Integer itemId) {
        return findElementByCss(driver, "a.note-edit[data-id='" + itemId.toString() + "']");
    }

    public WebElement getItemRow(WebDriver driver, Integer itemId) {
        return findElementByCss(driver, "tr.note-item-row[data-id='" + itemId.toString() + "']");
    }

    public WebElement getItemTitle(WebDriver driver, Integer itemId) {
        return findElementByCss(driver, "tr.note-item-row[data-id='" + itemId.toString() + "'] th.note-item-title");
    }

    public WebElement getItemDescription(WebDriver driver, Integer itemId) {
        return findElementByCss(driver, "tr.note-item-row[data-id='" + itemId.toString() + "'] td.note-item-description");
    }

    public WebElement getTitleTextBox() {
        return titleTextBox;
    }

    public WebElement getDescriptionTextBox() {
        return descriptionTextBox;
    }
}
