package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmailPage{
    WebDriver driver;
    private static final String PAGE_URL = "https://yopmail.com/ru/email-generator";
    @FindBy(xpath = "//button[@id='refresh']")
    WebElement refreshEmailsButton;

    @FindBy(xpath = "//iframe[@id='ifinbox']")
    WebElement inboxFrame;

    @FindBy(xpath = "//iframe[@id='ifmail']")
    WebElement emailFrame;

    @FindBy(xpath = "//div[@id='mail']/descendant::h3[2]")
    WebElement estimateEmailCost;

    @FindBy(xpath = "//button[@id='cprnd']")
    WebElement copyEmailButton;

    @FindBy(xpath = "//button/span[text()='Проверить почту']")
    WebElement checkEmailButton;

    public void copyEmailButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(copyEmailButton));
        copyEmailButton.click();
    }

    public EmailPage checkEmailButtonClick() {
        checkEmailButton.click();
        return new EmailPage(driver);
    }

    protected EmailPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public EmailPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void openEmail() {
        while (!inboxFrame.isDisplayed()) {
            new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(refreshEmailsButton));
            refreshEmailsButton.click();
        }
    }

    public String getEstimateEmailCost() {
        driver.switchTo().frame(emailFrame);
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(estimateEmailCost));
        return estimateEmailCost.getText();
    }
}