package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PastebinPage {

    private WebDriver driver;

    public PastebinPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get("https://pastebin.com");
    }

    public void createNewPaste(String code, String expiration, String title, String syntaxHighlighting) {
        WebElement codeTextArea = driver.findElement(By.id("postform-text"));
        codeTextArea.sendKeys(code);

        driver.findElement(By.id("select2-postform-expiration-container")).click();
        String expirationOptionXPath = "//li[text()='" + expiration + "']";
        driver.findElement(By.xpath(expirationOptionXPath)).click();

        WebElement selectSyntaxHighlighting = driver.findElement(By.id("select2-postform-format-container"));
        selectSyntaxHighlighting.click();

        String xpathExpression = "//li[text()='" + syntaxHighlighting + "']";
        WebElement syntaxOption = driver.findElement(By.xpath(xpathExpression));
        syntaxOption.click();

        WebElement pasteNameField = driver.findElement(By.id("postform-name"));
        pasteNameField.sendKeys(title);

        driver.findElement(By.xpath("//button[text()='Create New Paste']")).click();
    }
}
